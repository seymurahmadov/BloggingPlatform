package company.bloggingplatform.dto.request;

import company.bloggingplatform.annotation.ValidPassword;
import company.bloggingplatform.enumuration.Role;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class JwtSignInRequestDto {


    @NotEmpty(message = "The username should not be empty!")
    @NotNull(message = "The username should not be null!")
    @Email(message = "Email must be a well-formed")
    @Size(max = 30)
    private String mail;

    @NotEmpty(message = "The password should not be empty!")
    @NotNull(message = "The password should not be null!")
    @ValidPassword
    @Size(min = 8,message = "The password must be at least 8 digits")
    private String password;

    @NotNull(message = "The password should not be null!")
    private Role role;

}
