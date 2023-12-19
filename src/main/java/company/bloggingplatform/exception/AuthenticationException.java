package company.bloggingplatform.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class AuthenticationException extends BadCredentialsException {
    public AuthenticationException(String message) {
        super(message);
    }
}
