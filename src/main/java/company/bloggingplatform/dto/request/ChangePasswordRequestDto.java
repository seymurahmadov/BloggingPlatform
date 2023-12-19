package company.bloggingplatform.dto.request;

import company.bloggingplatform.annotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequestDto {

    private String mail;

    @NotEmpty(message = "The password should not be empty!")
    @Size(min = 8,message = "The password must be at least 8 digits")
    @ValidPassword
    private String oldPassword;

    @NotEmpty(message = "The password should not be empty!")
    @Size(min = 8,message = "The password must be at least 8 digits")
    @ValidPassword
    private String newPassword;
}
