package coetzee.hendrik.cic.err;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY, reason= "Unabled to create cic with presented data")
public class CicRegistrationException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 8545546765533100548L;

    public CicRegistrationException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CicRegistrationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public CicRegistrationException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public CicRegistrationException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public CicRegistrationException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    
}
