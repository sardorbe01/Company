package uz.es.company.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
@NotBlank(message = "Phone number cannot be empty")
private String phoneNumber;
@NotBlank(message = " Password cannot be empty")
private String password;
}
