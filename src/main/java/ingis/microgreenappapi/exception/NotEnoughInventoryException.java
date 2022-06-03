package ingis.microgreenappapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED)
public class NotEnoughInventoryException extends RuntimeException {

    public NotEnoughInventoryException(String message) {
        super(message);
    }

}
