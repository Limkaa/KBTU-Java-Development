package systemLogic;

public interface MessageSendable {

	// Send message
	public default void sendMessage(Message message) {
		Database.messages.add(message);
		Database.logFiles.add(new LogFile(LogType.MESSAGE_SENT));
	}
}
