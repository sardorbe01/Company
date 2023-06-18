package uz.es.company.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequestDto {
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Director name can not be empty")
    private String directorName;
    @NotBlank(message = "Address can not be empty")
    private String address;
    @Email
    @NotBlank(message = "Email can not be empty")
    private String email;
    @NotBlank(message = "Website must be created")
    private String website;
    @Pattern(
            regexp = "^\\+998\\d{9}$",
            message = "The input does not match the template(+99891234567)")
    private String phoneNumber;


}
