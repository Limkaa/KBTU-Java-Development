package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import systemLogic.Admin;
import systemLogic.Database;
import systemLogic.Departments;
import systemLogic.ORManager;
import systemLogic.SerializationOfDatabase;
import systemLogic.Student;
import systemLogic.Teacher;
import systemLogic.TechSupportGuy;
import systemLogic.User;

public class Controller {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
				
		// Checking if we can get data from file with information
		// If yes, then just continue session with already known data
		// name_surname - Format of all standart usernames and passwords 
		//
		// If no, then system will create new user with type Admin
		// Username: admin_ 
		// Password: admin_		
		
		if (!SerializationOfDatabase.deserializeData()) 
			Database.users.add(new Admin("Admin", "", Departments.TECHNICAL));

		startSystem();
	}


	public static void startSystem() throws IOException, ClassNotFoundException {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			while (true) {
	
				String welcomePage  = "\nWelcome to KBTU network!"
									+ "\n1. Login"
									+ "\n2. Exit";
				
				System.out.println(welcomePage);
				String input = reader.readLine();
	
				// Choosing Option 1 - Authorization
				if (input.equals("1")) {
	
					// If the user object is found, then we let into the system
					User user = menuLogin(reader);
					
					if (user != null) 
	
						while (user.isLoginned()) 
	
							if (user instanceof Student) 
								StudentController.menu(user, reader);
	
							else if (user instanceof Admin) 
								AdminController.menu(user, reader);
							
							else if (user instanceof Teacher) 
								TeacherController.menu(user, reader);
							
							else if (user instanceof ORManager) 
								ManagerController.menu(user, reader);
	
							else if (user instanceof TechSupportGuy)  
								SupportController.menu(user, reader);
							
							else 
								System.out.println("\n[Unknown type of user]");
						
					else 
						System.out.println("\n[Username or password incorrect. Please try again]");
				}
				else if (input.equals("2")) {
					System.out.println("\n[System closed]");
					break;
				}
				else 
					System.out.println("\n[Incorrect input format. Please choose available option]");
			} 
				
		} 
		
		finally {

			SerializationOfDatabase.serializeData();
			System.exit(0);
		}
	}

	
	// ----------------------------------------------------------------------------
	// Subcontrollers of individual stages of information input and processing
	// Made to visually relieve the main controller
	//-----------------------------------------------------------------------------


	// Subcontroller: User Authorization
	public static User menuLogin(BufferedReader reader) throws IOException {

		System.out.print("\nUsername: ");
		String username = reader.readLine();
		System.out.print("Password: ");
		String password = reader.readLine();

		User user = Database.getUser(username);
		if (user != null)
			if (user.login(password))
				return user;
		return null;
	}


	// Subcontroller: Change user password
	public static void showMenuForChangePassword(User user, BufferedReader reader) throws IOException {

		System.out.print("\nChanging password. It must:"
						+"\n- Be more than 4 symbols"
						+"\n- Have some special symbols (. , & % * _)"
						+"\n- Not contain space symbols"
						+"\n- Not repeat current password"
						+"\nPlease enter new password: ");
		String password = reader.readLine();
		System.out.print("Please repeat your new password: ");
		String repeatedPassword = reader.readLine();

		System.out.println(Facade.checkAndChangePassword(user, password, repeatedPassword));
		Controller.exitMessage(reader);
	}
	
	// Subcontroller: Notifies the user about which button to press to go back
	public static void exitMessage(BufferedReader reader) throws IOException {
		String note = "(Enter 0 to go back): ";
		System.out.print(note);
		while (!reader.readLine().equals("0")) 
			System.out.print(note);
	}

}
