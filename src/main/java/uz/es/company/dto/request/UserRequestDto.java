package uz.es.company.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Passport can not be empty")
    private String passportNumber;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[a-zA-Z0-9!@#$%^&*()_+]{8,}$",
            message = "password should contain at least 1 big and 1 small letter 1 symbol and 1 digit and length more than 8")
    private String password;

    @NotEmpty(message = "Surname can not be empty")
    private String surname;

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotEmpty(message = "Father name can not be empty")
    private String fatherName;

    @NotEmpty(message = "Position not null")
    private String position;

    @Pattern(
            regexp = "^\\+998\\d{9}$",
            message = "The input does not match the template(+99891234567)")
    private String phoneNumber;

    @NotEmpty(message = "Address can not be empty")
    private String address;

    @NotEmpty(message = "Company name must be entered ")
    private String companyName;
}
