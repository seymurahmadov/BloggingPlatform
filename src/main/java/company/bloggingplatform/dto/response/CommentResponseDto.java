package company.bloggingplatform.dto.response;

import company.bloggingplatform.entity.PostEntity;
import company.bloggingplatform.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;

    private String content;

    private LocalDate creetionDate;

    private UserEntity userEntity;

    private PostEntity postEntity;

}
