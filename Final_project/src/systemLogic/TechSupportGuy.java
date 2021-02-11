package systemLogic;

import java.util.Vector;

public class TechSupportGuy extends Employee {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<Order> rejectedOrders = new Vector<Order>();

	public TechSupportGuy() {}
	public TechSupportGuy(String name, String surname, Departments department) {
		super(name, surname, department);
	}
	
	// Getting new orders not including rejected 
	public Vector<Order> getNewOrders() {
		
		Vector<Order> newOrders=new Vector<Order>();
		for (Order order: Database.orders)
			
			if (order.getStatus().equals(OrderStatus.NEW)) {
				
				newOrders.add(order);
				for (Order rejectedOrder: rejectedOrders)
					if (rejectedOrder.equals(order)) {
						newOrders.remove(order);
						break;
					}
			}
		return newOrders;
	}
	
	// Cheching is order rejected
	public boolean isRejectedOrder(Order order) {
		for (Order rejectedOrder: rejectedOrders)
			if (rejectedOrder.getOrderId() == order.getOrderId())
				return true;
		return false;
	}
	
	// Accept order
	public boolean acceptNewOrder(Order order) {
		if (order.getStatus().equals(OrderStatus.NEW) && !isRejectedOrder(order)) {
			order.setStatus(OrderStatus.ACCEPTED);
			order.setExecutor(this);
			Database.logFiles.add(new LogFile(LogType.ORDER_ACCEPTED));
			return true;
		}
		return false;
	}
	
	// Reject order
	public boolean rejectNewOrder(Order order) {
		if (order.getStatus().equals(OrderStatus.NEW)) {
			Order rejectedOrder = order.clone();
			rejectedOrder.setStatus(OrderStatus.REJECTED);
			rejectedOrders.add(rejectedOrder);
			return true;
		}
		return false;
	}
	
	// Getting accepted orders
	public Vector<Order> getAcceptedOrders() {
		Vector<Order> acceptedOrders=new Vector<Order>();
		for (Order order: Database.orders) 
			if (order.getStatus().equals(OrderStatus.ACCEPTED) && order.getExecutor().equals(this))
				acceptedOrders.add(order);
		return acceptedOrders;
	}
	
	// Getting rejected orders
	public Vector<Order> getRejectedOrders() {
		return rejectedOrders;
	}
	
	// Getting done orders
	public Vector<Order> getDoneOrders() {
		Vector<Order> doneOrders=new Vector<Order>();
		for (Order order: Database.orders) {
			if (order.getStatus().equals(OrderStatus.DONE) && order.getExecutor().equals(this))
				doneOrders.add(order);
		}
		return doneOrders;
	}
	
	// Execution of accepted order
	public boolean executeAcceptedOrder(Order order) {
		if (order.getExecutor().equals(this) && order.getStatus().equals(OrderStatus.ACCEPTED)) {
			order.setStatus(OrderStatus.DONE);
			Database.logFiles.add(new LogFile(LogType.ORDER_DONE));
			return true;
		}
		return false;
	}

}
