package mk.finki.ukim.mealsmack.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super("Invalid username or password!");
    }
}
