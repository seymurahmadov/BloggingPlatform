package company.bloggingplatform.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CommentRequestDto {

    @Size(max = 20)
    @NotNull(message = "Content name not be null!")
    private String content;

    private LocalDate creetionDate;

    @NotNull(message = "User id not be null")
    private Long userId;

    @NotNull(message = "Post id not be null")
    private Long postId;

}
