package adapters.http;

import domain.ConflictException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionFilter
        implements ExceptionMapper<ConflictException> {

    public Response toResponse(ConflictException e) {
        Response.ResponseBuilder status = Response.status(Response.Status.CONFLICT);
        return status.build();
    }
}
