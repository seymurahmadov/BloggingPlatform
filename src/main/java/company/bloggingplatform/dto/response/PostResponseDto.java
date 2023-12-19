package company.bloggingplatform.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import company.bloggingplatform.entity.CommentEntity;
import company.bloggingplatform.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long id;

    private String title;

    private String content;

    private LocalDate publicationDate;

    private UserEntity userEntity;

    private List<CommentEntity> commentEntities;
}
