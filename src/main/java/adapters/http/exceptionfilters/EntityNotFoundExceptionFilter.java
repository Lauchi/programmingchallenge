package adapters.http.exceptionfilters;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionFilter
        implements ExceptionMapper<NotFoundException> {

    public Response toResponse(NotFoundException e) {
        Response.ResponseBuilder status = Response.status(Response.Status.NOT_FOUND);
        return status.build();
    }
}
