package company.bloggingplatform.dto.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PostRequestDto {
    @Size(max = 20)
    @NotNull(message = "Title name not be null!")
    @NotEmpty(message = "Title name not be empty!")
    private String title;

    @Size(max = 20)
    @NotNull(message = "Content name not be null!")
    @NotEmpty(message = "Content name not be empty!")
    private String content;

    @NotNull(message = "Publication date name not be null!")
    private LocalDate publicationDate;

    @NotNull(message = "User id not be null!")
    private Long userId;
}
