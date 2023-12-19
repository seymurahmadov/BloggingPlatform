package company.bloggingplatform.dto.request;

import company.bloggingplatform.annotation.ValidPassword;
import company.bloggingplatform.enumuration.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class UserRequestDto {

    @NotEmpty(message = "The username should not be empty!")
    @NotNull(message = "The username should not be null!")
    @Size(max = 15, message = "Username size must be maximum 15 characters")
    private String username;

    @NotEmpty(message = "The mail should not be empty!")
    @NotNull(message = "The mail should not be null!")
    @Email(message = "Email must be a well-formed")
    @Size(max = 30, message = "Email size must be maximum 30 characters")
    private String mail;

    @NotEmpty(message = "The password should not be empty!")
    @NotNull(message = "The password should not be null!")
    @ValidPassword
    @Size(min = 8,message = "The password must be at least 8 digits")
    private String password;

    @NotEmpty(message = "The role should not be empty!")
    private Role role;

}
