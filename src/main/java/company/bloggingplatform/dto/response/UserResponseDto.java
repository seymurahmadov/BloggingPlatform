package company.bloggingplatform.dto.response;

import company.bloggingplatform.entity.PostEntity;
import company.bloggingplatform.enumuration.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;

    private String username;

    private String mail;

    private Role role;

}
