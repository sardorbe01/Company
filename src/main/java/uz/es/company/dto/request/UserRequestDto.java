package uz.es.company.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    @Pattern(
            regexp = "^[A-Z]{2}\\d{7}$",
            message = "does not match the template(AA1234567)")
    @NotBlank(message = "Passport can not be empty")
    private String passportNumber;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,}$",
            message = "password should contain at least 1 big and 1 small letter 1 symbol and 1 digit and length more than 8")
    private String password;

    @NotBlank(message = "Surname can not be empty")
    private String surname;

    @NotBlank(message = "Name can not be empty")
    private String name;

    @NotBlank(message = "Father name can not be empty")
    private String fatherName;

    @NotBlank(message = "Position not null")
    private String position;

    @Pattern(
            regexp = "^\\+998\\d{9}$",
            message = "The input does not match the template(+99891234567)")
    private String phoneNumber;

    @NotBlank(message = "Address can not be empty")
    private String address;

    @NotBlank(message = "Company name must be entered ")
    private String companyName;
}
