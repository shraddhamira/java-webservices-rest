package messanger.messanger.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import messanger.messanger.model.ErrorMessage;

//@Provider commented For Tutorial 28
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	private Logger logger = Logger.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		logger.debug("Error occurred", exception);
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "Exception Occurred");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
