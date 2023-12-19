package company.bloggingplatform.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }

}
