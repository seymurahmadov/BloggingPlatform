package company.bloggingplatform.dto.request;

import company.bloggingplatform.annotation.ValidPassword;
import company.bloggingplatform.enumuration.Role;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class JwtSignInRequestDto {


    @NotEmpty(message = "The username should not be empty!")
    private String mail;

    @NotEmpty(message = "The password should not be empty!")
    @ValidPassword
    private String password;

    @NotEmpty(message = "The role should not be empty!")
    private Role role;

}
