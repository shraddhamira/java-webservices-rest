package messanger.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import messanger.messanger.model.Comment;
import messanger.messanger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	private Logger logger = Logger.getLogger(CommentResource.class);
	private CommentService service = new CommentService();

	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId) {
		return service.getComments(messageId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return service.getComment(messageId, commentId);
	}

	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		logger.debug("Inside addMessage" + comment);
		return service.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId,
			Comment comment) {
		logger.debug("Inside updateComment" + comment);
		return service.updateComment(messageId, commentId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		logger.debug("Inside removeComment" + commentId);
		service.removeComment(messageId, commentId);
		
	}
}
