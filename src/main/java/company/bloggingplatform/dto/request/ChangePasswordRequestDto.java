package company.bloggingplatform.dto.request;

import company.bloggingplatform.annotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequestDto {

    @Email

    private String mail;

    @NotEmpty(message = "The old password should not be empty!")
    @NotNull(message = "The old password should not be null!")
    @Size(min = 8,message = "The password must be at least 8 digits")
    @ValidPassword
    private String oldPassword;

    @NotEmpty(message = "The new password should not be empty!")
    @NotNull(message = "The new password should not be null!")
    @Size(min = 8,message = "The password must be at least 8 digits")
    @ValidPassword
    private String newPassword;
}
