package systemLogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {

	private static final long serialVersionUID = 10L;
	
	private int messageId;
	private User sender;
	private User receiver;
	private String text;
	private String sendingTime;
	private MessageStatus status = MessageStatus.UNREAD;
	
	{
		Database.counterMessageId++;
		messageId = Database.counterMessageId;
	}

	public Message() {}
	public Message (User sender, User receiver, String text) {
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		this.sendingTime = formattedDate;
	}
	
	// Getting message id
	public int getMessageId() {
		return messageId;
	}
	
	// Receiving the recipient of the message
	public User getReceiver() {
		return receiver;
	}
	
	// Receiving the sender of a message
	public User getSender() {
		return sender;
	}
	
	// Retrieving message text
	public String getText() {
		return text;
	}
	
	// Retrieving message sending time
	public String getSendingTime() {
		return sendingTime;
	}
	
	// Get message status
	public MessageStatus getStatus() {
		return status;
	}
	
	// Setting message status
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", sender=" + sender + ", receiver=" + receiver + ", text=" + text
				+ ", sendingTime=" + sendingTime + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + messageId;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((sendingTime == null) ? 0 : sendingTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (messageId != other.messageId)
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (sendingTime == null) {
			if (other.sendingTime != null)
				return false;
		} else if (!sendingTime.equals(other.sendingTime))
			return false;
		if (status != other.status)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
