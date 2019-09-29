package domain;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class ConflictException extends ClientErrorException {

    public ConflictException() {
        super(Response.Status.CONFLICT);
    }
}
