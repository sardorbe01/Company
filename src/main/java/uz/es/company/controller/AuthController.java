package uz.es.company.controller;
/*
* Bu tizim login va signUp uchun ishlaydi;
* login api orqali hamma user kirishi mumkin bu yo`l hama uchun ochiq
* user signUp bu yo`l login qilgan user uchun
* admin signUp admin roliga ega user uchun signUp qilinganda adminPage ga o`tiladi
*/
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.es.company.dto.request.LoginRequestDto;
import uz.es.company.dto.request.UserRequestDto;
import uz.es.company.dto.response.JwtResponse;
import uz.es.company.entity.user.UserEntity;
import uz.es.company.entity.user.role.UserRole;
import uz.es.company.exception.RequestValidationException;
import uz.es.company.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequestDto login,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.login(login));
    }

    @PostMapping("/user/sign-up")
    public ResponseEntity<UserEntity> userSignUp(
            @RequestBody UserRequestDto userDto,
            BindingResult bindingResult
    ){
//            throws RequestValidationException {
//        if (bindingResult.hasErrors()) {
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            throw new RequestValidationException(allErrors);

        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.USER)));
    }

    @PostMapping("/admin/sign-up")
    public ResponseEntity<UserEntity> adminSignUp(
            @Valid @RequestBody UserRequestDto userDto,
            BindingResult bindingResult
    ) {


        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.ADMIN)));
    }
}
