package uz.es.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import uz.es.company.dto.request.CompanyRequestDto;
import uz.es.company.entity.company.CompanyEntity;
import uz.es.company.exception.RequestValidationException;
import uz.es.company.service.CompanyService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company/v1")
@RequiredArgsConstructor
public class CompanyController {
//    @PreAuthorize(value = "hasRole('ADMIN')")

    @PostMapping("/add")
    public ResponseEntity<CompanyEntity> add(
            @RequestBody CompanyRequestDto companyRequestDto
//            BindingResult bindingResult
    ) {
//        if (bindingResult.hasErrors()) {
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            throw new RequestValidationException(allErrors);
//
//        }
        return ResponseEntity.ok(companyService.save(companyRequestDto));
    }

    private final CompanyService companyService;

    @GetMapping("/get-company")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<CompanyEntity> getAll(
            @RequestParam UUID id
    ) {
        return ResponseEntity.status(200).body(companyService.getById(id));
    }

    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void delete(
            @RequestParam UUID id
    ) {
        companyService.delete(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<CompanyEntity> update(
            @RequestBody CompanyRequestDto companyRequestDto,
            Principal principal
    ) {
        return ResponseEntity.ok(companyService.update(companyRequestDto, principal));
    }


}
