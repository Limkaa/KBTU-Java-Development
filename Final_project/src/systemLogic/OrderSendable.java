package systemLogic;

public interface OrderSendable {
	
	// Sending order
	public default void sendOrder(Order order) {
		Database.orders.add(order);
		Database.logFiles.add(new LogFile(LogType.NEW_ORDER_TO_SUPPORT));
	}
}
