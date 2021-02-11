package systemLogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable, Cloneable {

	private static final long serialVersionUID = 2L;
	
	private int orderId;
	private User sender;
	private TechSupportGuy executor;
	private String text;
	private String sendingTime;
	private OrderStatus status = OrderStatus.NEW;

	public Order() {}
	public Order (User sender, String text) {
		this.sender = sender;
		this.text = text;
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		this.sendingTime = formattedDate;
		
		Database.counterOrderId++;
		this.orderId = Database.counterOrderId;
	}
	
	// Getting order id
	public int getOrderId() {
		return orderId;
	}
	
	// Getting sender of order
	public User getSender() {
		return sender;
	}
	
	// Getting executor of order
	public User getExecutor() {
		return executor;
	}
	
	// Setting executor for order
	public void setExecutor(TechSupportGuy executor) {
		this.executor = executor;
	}
	
	// Getting order text
	public String getText() {
		return text;
	}
	
	// Getting order status
	public OrderStatus getStatus() {
		return status;
	}
	
	// Setting order status
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	// Getting sending time
	public String getSendingTime() {
		return sendingTime;
	}
	
	public Order clone() {
		Order order = new Order();
		order.orderId = this.orderId;
		order.sender = this.sender;
		order.sendingTime = this.sendingTime;
		order.status = this.status;
		order.text = this.text;
		order.executor = this.executor;
		return order;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", sender=" + sender + ", executor=" + executor + ", text=" + text
				+ ", sendingTime=" + sendingTime + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executor == null) ? 0 : executor.hashCode());
		result = prime * result + orderId;
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
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		if (orderId != other.orderId)
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
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
}
