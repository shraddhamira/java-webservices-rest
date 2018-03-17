package messanger.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import messanger.messanger.database.DatabaseClass;
import messanger.messanger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		Message m1 = new Message(1L, "Hello World", new Date(), "Shraddha");
		Message m2 = new Message(2L, "Hello Friends", new Date(), "Aaashu");
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(Long messageId) {
		return messages.get(messageId);
	}

	public Message addMessaage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() == 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public void removeMessage(Long id) {
		messages.remove(id);
	}

	public List<Message> getMessagesByYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagePagination(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);

	}

}
