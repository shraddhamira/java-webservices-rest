package messanger.messanger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import messanger.messanger.database.DatabaseClass;
import messanger.messanger.model.Comment;
import messanger.messanger.model.ErrorMessage;
import messanger.messanger.model.Message;

public class CommentService {
	private Logger logger = Logger.getLogger(CommentService.class);
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getComments(long messageId) {
		Map<Long, Comment> comments = null;
		Message message = messages.get(messageId);
		if (null == message.getComments()) {
			comments = new HashMap<>();
			messages.get(messageId).setComments(comments);
		}
		return new ArrayList<Comment>(message.getComments().values());
	}

	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = null;
		Message message = messages.get(messageId);
		if(null == message){
			ErrorMessage errorMessage = new ErrorMessage("Resource not found", 500, "Exception Occurred");
			Response response = Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
		if (null == message.getComments()) {
			comments = new HashMap<>();
			
			messages.get(messageId).setComments(comments);
		}
		return message.getComments().get(commentId);
	}

	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = null;
		if (null == messages.get(messageId).getComments()) {
			comments = new HashMap<>();
			messages.get(messageId).setComments(comments);
		} else {
			comments = messages.get(messageId).getComments();
		}
		logger.debug("Size of Comments : " + messages.get(messageId).getComments().size());
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		messages.get(messageId).setComments(comments);
		logger.debug(messages.get(messageId));
		return comment;
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		Map<Long, Comment> comments = null;
		Message message = messages.get(messageId);
		comment.setId(commentId);
		if (null == message.getComments()) {
			comments = new HashMap<>();
			messages.get(messageId).setComments(comments);
		}
		message.getComments().put(commentId, comment);
		return comment;
	}

	public void removeComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		message.getComments().remove(commentId);
	}
}
