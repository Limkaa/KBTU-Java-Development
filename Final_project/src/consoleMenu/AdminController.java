package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import systemLogic.Admin;
import systemLogic.Database;
import systemLogic.Departments;
import systemLogic.ORManager;
import systemLogic.Order;
import systemLogic.Role;
import systemLogic.Speciality;
import systemLogic.Student;
import systemLogic.Teacher;
import systemLogic.TechSupportGuy;
import systemLogic.User;

public class AdminController {

	static Admin admin = null;
	static BufferedReader reader = null;
	
	// Student menu
	public static void menu(User user, BufferedReader bufReader) throws IOException {

		admin = (Admin)user;
		reader = bufReader;
		
		String adminHomePage  = "\n[Admin: " + admin.getFullName() + "]"
								+ "\n---------------------------------------"
								+ "\n1. Manage users"
								+ "\n2. View logs"
								+ "\n3. Send/View orders to support"
								+ "\n4. Change password"
								+ "\n0. Logout";

		while (admin.isLoginned()) {

			System.out.println(adminHomePage);
			String choice = reader.readLine();

			// Option: log out of account
			if (choice.equals("0")) {
				admin.logout();
				System.out.println("\n[You logged out]");
			}

			// 1 main menu option: user management
			else if (choice.equals("1")) 

				while(true) {

					String menuCreationUser = "\n1. Create new user"
											+ "\n2. Delete user"
											+ "\n3. Update users info"
											+ "\n4. View all users"
											+ "\n0. Exit to main menu";

					System.out.println(menuCreationUser);	
					choice = reader.readLine();

					// Back to main menu
					if (choice.equals("0"))
						break;
					
					// Create new user
					else if (choice.equals("1")) {
						
					    String userTypeInfo = "\n1. Student"
											+ "\n2. Teacher"
											+ "\n3. ORManager"
											+ "\n4. Admin"
											+ "\n5. TechSupport"
											+ "\n0. Exit to main menu";

						System.out.println(userTypeInfo);	
						choice = reader.readLine();
						
						if (choice.equals("0"))
							continue;
						
						else if (choice.equals("1") || choice.equals("2") || choice.equals("3")
								|| choice.equals("4") || choice.equals("5"))
							AdminController.addUser(choice);
						
						else
							System.out.println("\n[Incorrect input format. Please choose available option]\n");	
						
					}
					
					// Delete user
					else if (choice.equals("2")) {
						
						Views.showUsers(Database.getUsers());
						
						System.out.print("\n(Enter 0 to exit to main menu)"
								+ "\nPlease enter ID of user, which you need to delete: ");
						choice = reader.readLine();
						
						try {
							int userId = Integer.parseInt(choice);
							
							if (!choice.equals("0") && userId != admin.getId())
								if (admin.deleteUser(Database.getUser(userId)))
									System.out.println("[User deleted]");
							else if (choice.equals("0"))
								continue;
							else
								System.out.println("[You are not able to delete this user]");
						}
						catch (NumberFormatException exception) {
							System.out.println("[Incorrect input format. Please enter number]\n");
						} catch (NullPointerException exception) {
							System.out.println("[Incorrect number]\n");
						}
					}
					
					// Update user info
					else if (choice.equals("3")) {
						
					    String userTypeInfo = "\n1. Increase students study year"
											+ "\n0. Exit to main menu";

						System.out.println(userTypeInfo);	
						choice = reader.readLine();
						
						if (choice.equals("0"))
							break;
						else if (choice.equals("1")) {
							
							System.out.println("This operation can't be disrupted after processing! Are you sure? If you agree, enter 'YES'");
							choice = reader.readLine();
							
							if (choice.equals("YES")) {
								admin.updateStudentsYear();
								System.out.println("[All students study year has been increased]");
							}
							else 
								System.out.println("[Operation has been disrupted]");
						}
						else
							System.out.println("\n[Incorrect input format. Please choose available option]\n");	
					}
					
					// View all users
					else if (choice.equals("4")) {
						Views.showUsers(Database.getUsers());
						Controller.exitMessage(reader);
					}
					
					else 
						System.out.println("\n[Incorrect input format. Please choose available option]\n");
				}
			

			// 2 main menu option: view log files
			else if (choice.equals("2")) {
				Views.showLogs(Database.logFiles);
				Controller.exitMessage(reader);
			}
			
			// 3 main menu option: manage orders to support
			else if (choice.equals("3")) {

				while (true) {

					String detailInfoMenu =   "\n1. Send new order to support"
											+ "\n2. View all sent orders"
											+ "\n0. Exit main";

					System.out.println(detailInfoMenu);
					String option = reader.readLine();

					// Send new order
					if (option.equals("1")) {

						System.out.print("\nPlease describe problem in order to make request to support (0 to exit): ");
						String text = reader.readLine();
						
						if (!text.equals("0")) {
							admin.sendOrder(new Order(admin, text));
							System.out.println("[Your request has been sent to support]\n");
							Controller.exitMessage(reader);
						}
					}

					// View all submitted orders
					else if (option.equals("2")) {
						Views.showOrders(Database.getOrdersFromUser(admin));
						Controller.exitMessage(reader);
					}

					// Back to main menu
					else if (option.equals("0"))
						break;

					else 
						System.out.println("\n[Incorrect input format. Please choose available option]");					

				}
			}

			// 4 main menu option: change password
			else if (choice.equals("4")) 
				Controller.showMenuForChangePassword(user, reader);

			else 
				System.out.println("\n[Incorrect input format. Please choose available option]");
		}
	}

	// ----------------------------------------------------------------------------
	// Subcontrollers of individual stages of information input and processing
	// Made to visually relieve the main controller
	//-----------------------------------------------------------------------------
	
	// Subcontroller of new user creation
	public static boolean addUser(String userType) {
		
		try {
			
			String name, surname;
			User user = null;
			
			System.out.print("\nName: ");
			name = reader.readLine();
			
			System.out.print("Surname: ");
			surname = reader.readLine();
			
			if (userType.equals("1")) {
				
				System.out.print("Specialities: ");
				Vector<Speciality> specialityList = new Vector<Speciality>();
				
				int counter = 0;
				
				for (Speciality specialityTitle: Speciality.values()) {
					System.out.print("\n" + (++counter) + ". " + specialityTitle);
					specialityList.add(specialityTitle);
				}
				
				System.out.print("\nEnter only one option: ");
				String choice = reader.readLine();
				
				Speciality userSpeciality = null;
				int num = Integer.parseInt(choice.strip());
				
				if (num <= Speciality.values().length && num > 0) {
			    	userSpeciality = specialityList.elementAt(num-1);
				
					System.out.print("Study year: ");
					int studyYear = Integer.parseInt(reader.readLine());
					
					if (studyYear > 0) 
						user = new Student(name, surname, userSpeciality, studyYear);
					else {
						System.out.println("[User creation disrupted. Students study year must be more than 0]");
						return false;
					}
				}
				else 
					System.out.println("[Incorrect ID of speciality]");
				    
			} else {
				
				if (userType.equals("3"))
					user = new ORManager(name, surname, Departments.MANAGEMENT);
				
				else if (userType.equals("4"))
					user = new Admin(name, surname, Departments.TECHNICAL);
				
				else if (userType.equals("5"))
					user = new TechSupportGuy(name, surname, Departments.TECHNICAL);
				
				else if (userType.equals("2")) {
					
					System.out.print("Teacher role: ");
					
					int counter = 0;
					Vector<Role> rolesList = new Vector<Role>();
					
					for (Role roleTitle: Role.values()) {
						System.out.print("\n" + (++counter) + ". " + roleTitle);
						rolesList.add(roleTitle);
					}
					
					System.out.print("\nEnter only one option: ");
					String choice = reader.readLine();
					
					Role userRole = null;
					int num = Integer.parseInt(choice.strip());
					
					if (num <= Role.values().length && num > 0) {
						userRole = rolesList.elementAt(num-1);
						user = new Teacher(name, surname, Departments.EDUCATION, userRole);
					}
					
					else {
						System.out.println("[User creation disrupted. Incorrect number of teacher role]");
						Controller.exitMessage(reader);
						return false;
					}
				}
			}
			
			if (admin.addUser(user))
				System.out.println("[User was successfully created]");
			else
				System.out.println("[User creation disrupted. There is a user with the same username]");
			
			Controller.exitMessage(reader);
			return true;
			
		} catch (IOException exception) {
            System.out.println("[Something bad happened. We are trying to fix it now]");
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter number]\n");
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect number]\n");
		}
		
		return false;
	}
	
}
