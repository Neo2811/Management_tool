package am.hitech.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IncorrectDataException extends Exception{

    public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }
}
