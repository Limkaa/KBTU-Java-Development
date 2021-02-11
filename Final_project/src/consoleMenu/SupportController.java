package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;

import systemLogic.Database;
import systemLogic.Order;
import systemLogic.TechSupportGuy;
import systemLogic.User;

public class SupportController {

	static TechSupportGuy support = null;
	static BufferedReader reader = null;

	// Tech support menu
	public static void menu(User user, BufferedReader bufReader) throws IOException {

		support = (TechSupportGuy) user;
		reader = bufReader; 

		String supportHomePage  = "\n[TechSupport: " + support.getFullName() + "]"
								+ "\n---------------------------------"
								+ "\n1. View new orders" 
								+ "\n2. Manage accepted orders"
								+ "\n3. View done and rejected orders"
								+ "\n4. Change password"
								+ "\n0. Logout";

		while (support.isLoginned()) {

			System.out.println(supportHomePage);
			String choice = reader.readLine();

			// Option: log out of account
			if (choice.equals("0")) {
				support.logout();
				System.out.println("\n[You logged out]");
			}

			// 1 main menu option: view all new orders (without rejected)
			else if (choice.equals("1"))

				if (support.getNewOrders().size() > 0) {
					while(true) {
	
						Views.showOrders(support.getNewOrders());
	
						String newOrdersMenu =  "1. Accept order"
											+ "\n2. Reject order"
											+ "\n0. Exit to main menu";
	
						System.out.println(newOrdersMenu);	
						choice = reader.readLine();
	
						if (choice.equals("0"))
							break;
						else if (choice.equals("1") || choice.equals("2"))
							SupportController.processOrder(choice);
						else 
							System.out.println("\n[Incorrect input format. Please choose available option]\n");
					}
				}
				else {
					System.out.println("[There are no any new orders yet]");
					Controller.exitMessage(reader);
				}
		
			
			// 2 main menu option: manage accepted orders
			else if (choice.equals("2"))
				
				if (support.getAcceptedOrders().size() > 0) {
					while(true) {
	
						Views.showOrders(support.getAcceptedOrders());
	
						String orderManageMenu =    "1. Select order to execute"
												+ "\n0. Exit to main menu";
	
						System.out.println(orderManageMenu);	
						choice = reader.readLine();
	
						if (choice.equals("0")) 
							break;
						else if (choice.equals("1")) 
							SupportController.orderExecution();
						else 
							System.out.println("\n[Incorrect input format. Please choose available option]\n");
						} 
				}
				else {
					System.out.println("[There are no any accepted orders yet]");
					Controller.exitMessage(reader);
				}

			
			// 3 main menu option: view done and rejected orders
			else if (choice.equals("3")) 
				
				while (true) {
					
					String orderManageMenu =  "\n1. Done orders"
											+ "\n2. Rejected orders"
											+ "\n0. Exit to main menu";

					System.out.println(orderManageMenu);	
					choice = reader.readLine();

					if (choice.equals("0")) 
						break;
					else if (choice.equals("1")) {
						Views.showOrders(support.getDoneOrders());
						Controller.exitMessage(bufReader);
					}
					else if (choice.equals("2")) {
						Views.showOrders(support.getRejectedOrders());
						Controller.exitMessage(bufReader);
					}
					else 
						System.out.println("\n[Incorrect input format. Please choose available option]\n");
				} 
			
			else if (choice.equals("4")) 
				Controller.showMenuForChangePassword(support, reader);
		
			else 
				System.out.println("\n[Incorrect input format. Please choose available option]");

		}
	}

	// ----------------------------------------------------------------------------
	// Subcontrollers of individual stages of information input and processing
	// Made to visually relieve the main controller
	//-----------------------------------------------------------------------------

	// Subcontroller: accepting or rejecting order
	public static void processOrder(String option) throws IOException {

		System.out.print("Please enter ID of order from the list (0 to exit): ");
		String choice = reader.readLine();
		
		try {
			int orderId = Integer.parseInt(choice);
			Order order = Database.getOrder(orderId);

			if (!choice.equals("0") && !support.isRejectedOrder(order) && order != null) {
			
				if (option.equals("1")) {

					if (support.acceptNewOrder(order)) 
						System.out.println("\n[You accepted this order]");
					else 
						System.out.println("[Probably somebody else has already took this order]");
		
				} 
				else if (option.equals("2")) {

					if (support.rejectNewOrder(order)) 
						System.out.println("[You rejected this order]");
					else 
						System.out.println("[Probably somebody else has already took this order]");
				}
				
				Controller.exitMessage(reader);
			}	
			else if (!choice.equals("0"))
				System.out.println("[This order doesn't exist or you rejected it]");

		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter number]\n");
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number. Please try again]\n");
		}
	}
	
	
	// Subcontroller: order execution
	public static void orderExecution() throws IOException {
		
		System.out.print("\nPlease enter ID of order from the list (0 to exit): ");
		String choice = reader.readLine();

		if (!choice.equals("0"))
			
			try {
				int orderId = Integer.parseInt(choice);
				Order order= Database.getOrder(orderId);

				if (support.executeAcceptedOrder(order))
					System.out.println("[You successfully executed this order]");
				else 
					System.out.print("[Incorrect ID number]");
				
				Controller.exitMessage(reader);
				
			} catch (NumberFormatException exception) {
				System.out.println("[Incorrect input format. Please enter number]\n");
			}  catch (NullPointerException exception) {
				System.out.println("[Incorrect ID number. Please try again]\n");
			}
	}
}
