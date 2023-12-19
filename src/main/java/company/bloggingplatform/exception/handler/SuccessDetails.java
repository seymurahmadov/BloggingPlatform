package company.bloggingplatform.exception.handler;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class SuccessDetails<T> {

   private T data;

   private int statusCode;

   boolean isSuccess;

}
