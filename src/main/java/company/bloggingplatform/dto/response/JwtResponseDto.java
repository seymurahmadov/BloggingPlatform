package company.bloggingplatform.dto.response;

import company.bloggingplatform.enumuration.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDto {
    private String mail;

    private String token;

    private Role role;

}
