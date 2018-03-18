package messanger.messanger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import messanger.messanger.exceptions.DataNotFoundException;
import messanger.messanger.model.Message;
import messanger.messanger.service.MessageService;

@Path("/messages")
public class MessageResource {
	private Logger logger = Logger.getLogger(MessageResource.class);
	MessageService service = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year, @QueryParam("start") int start,
			@QueryParam("size") int size) {
		logger.debug("Inside getMessages : year: "+year+", start : "+start +", size: "+size);
		if (year > 0)
			return service.getMessagesByYear(year);
		if (start >= 0 && size >= 0) {
			return service.getAllMessagePagination(start, size);
		}
		return service.getAllMessages();
	}

	@GET
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") Long messageId) {
		Message message = service.getMessage(messageId);
		if(null==message){
			throw new DataNotFoundException("Not FOund");
		}
		return message;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uri){
		Message newMessage = service.addMessaage(message);
//		return Response.status(Status.CREATED)
//		.entity(newMessage)
//		.build();
		//return Response.created(new URI("messanger/webpi/messages/"+newMessage.getId()))
		//		.entity(newMessage)
		//		.build();
		return Response.created(uri.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build())
				.entity(newMessage)
				.build();
		
	}

	@PUT
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message update(@PathParam("messageId") String messageId, Message message) {
		message.setId(Long.parseLong(messageId));
		service.updateMessage(message);
		return message;
	}

	@DELETE
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") String messageId) {
		service.removeMessage(Long.parseLong(messageId));
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
