package uz.es.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.es.company.dto.request.UserRequestDto;
import uz.es.company.entity.user.UserEntity;
import uz.es.company.exception.RequestValidationException;
import uz.es.company.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<UserEntity> add(
            @RequestBody UserRequestDto userRequestDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return ResponseEntity.ok(userService.save(userRequestDto));
    }

    @GetMapping("/get-user")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<UserEntity> getAll(
            @RequestParam UUID id
    ) {
        return ResponseEntity.status(200).body(userService.getById(id));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(
            @RequestParam UUID id
    ) {
        userService.deleteById(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserEntity> update(
            @RequestBody UserRequestDto userRequestDto,
            Principal principal
    ) {
        return ResponseEntity.ok(userService.update(userRequestDto, principal));
    }

}
