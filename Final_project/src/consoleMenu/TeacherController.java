package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;

import systemLogic.Course;
import systemLogic.CourseFile;
import systemLogic.Database;
import systemLogic.Mark;
import systemLogic.Message;
import systemLogic.MessageStatus;
import systemLogic.Order;
import systemLogic.Student;
import systemLogic.User;
import systemLogic.Teacher;

public class TeacherController {

	static Teacher teacher = null;
	static BufferedReader reader = null;
	
	// Teacher menu
	public static void menu(User user, BufferedReader bufReader) throws IOException {

		teacher = (Teacher)user;
		reader = bufReader;

		while (user.isLoginned()) {
				
			String teacherHomePage  = "\n[Teacher: " + user.getFullName() + "]"
									+ "\n---------------------------------------"
									+ "\n1. Manage courses and files"
									+ "\n2. View students info/Put marks"
									+ "\n3. Send/View messages to managers [Unread:" + Database.getUnreadMessagesToUser(teacher).size() + "]"  
									+ "\n4. Send/View orders to support"
									+ "\n5. Change password"
									+ "\n0. Logout";


			System.out.println(teacherHomePage);
			String choice = reader.readLine();

			// Option: log out of account
			if (choice.equals("0")) {
				user.logout();
				System.out.println("\n[You logged out]");
			}

			// 1 main menu option: view teaching courses and manage their course files
			else if (choice.equals("1")) 

				if (teacher.getTeachingCourses().size() > 0) {
					while(true) {
	
						Views.showCourses(teacher, 7, teacher.getTeachingCourses());
						
						String menuCoursesInfo  = "\n1. Manage course files "
												+ "\n0. Exit to main menu";
	
						System.out.println(menuCoursesInfo);	
						choice = reader.readLine();
	
						if (choice.equals("0"))
							break;
						
						else if (choice.equals("1")) 
							TeacherController.manageCourseFiles();
							
						else 
							System.out.println("\n[Incorrect input format. Please choose available option]\n");
					}
				}
				else {
					System.out.println("[There are no any courses, which you teach. Please wait, they will be added by manager very soon]");
					Controller.exitMessage(reader);
				}

			// 2 main menu option: view course students and put their marks
			else if (choice.equals("2")) {
				if (teacher.getTeachingCourses().size() > 0) {
					while (true) {
						
						Views.showCourses(teacher, 7, teacher.getTeachingCourses());
	
						String puttingMarksMenu = "\n1. Select course"
												+ "\n0. Exit to main menu";
	
						System.out.println(puttingMarksMenu);	
						choice = reader.readLine();
	
						if (choice.equals("0"))
							break;
						else if (choice.equals("1"))
							TeacherController.putMarks();
						else 
							System.out.println("\n[Incorrect input format. Please choose available option]\n");
					}
				}
				else {
					System.out.println("[There are no any courses, which you teach. Please wait, they will be added by manager very soon]");
					Controller.exitMessage(reader);
				}

			}
			
			// 3 main menu option: manage messages to managers
			else if (choice.equals("3")) {
				
				while (true) {
					
					String detailInfoMenu =   "\n1. Send new message to manager"
											+ "\n2. View all sent messages"
											+ "\n3. View all incoming messages [Unread:" + Database.getUnreadMessagesToUser(teacher).size() + "]"
											+ "\n0. Exit main";
	
					System.out.println(detailInfoMenu);
					String option = reader.readLine();

					// Send new messages
					if (option.equals("1")) {
						
						if (Database.getManagers().size() > 0) {
							while(true) {
								
								Views.showManagers(Database.getManagers());
								
								String detailSendingMenu ="\n1. Choose manager"
														+ "\n0. Exit back";
								
								System.out.println(detailSendingMenu);
								option = reader.readLine();
										
								if (option.equals("1")) {
									TeacherController.sendMessageToManager();
									Controller.exitMessage(reader);
								}
								else if (option.equals("0"))
									break;
								else 
									System.out.println("\n[Incorrect input format. Please choose available option]");
								
							}
						}
						else {
							System.out.println("[There are no any managers yet]");
							Controller.exitMessage(reader);
						}
					}
					
					// View all sent messages 
					else if (option.equals("2")) {
						
						Views.showSentMessages(Database.getMessagesFromUser(teacher));
						Controller.exitMessage(reader);
						
					}
					
					// View all incoming messages
					else if (option.equals("3")) {
						
						Views.showIncomingMessages(Database.getMessagesToUser(teacher));
						Controller.exitMessage(reader);
						for (Message message: Database.getMessagesToUser(user))
							message.setStatus(MessageStatus.READ);
						
					}
					
					else if (option.equals("0"))
						break;
					else 
						System.out.println("\n[Incorrect input format. Please choose available option]");					
					
				}
			}
			
			// 4 main menu option: manage orders to support
			else if (choice.equals("4")) {
				
				while (true) {

					String detailInfoMenu =   "\n1. Send new order to support"
											+ "\n2. View all sent orders"
											+ "\n0. Exit main";

					System.out.println(detailInfoMenu);
					String option = reader.readLine();

					// Send new order
					if (option.equals("1")) {
						
						System.out.print("\nPlease describe problem in order to make request to support (0 to exit back): ");
						String text = reader.readLine();

						if (!text.equals("0")) {
							teacher.sendOrder(new Order(teacher, text));
							System.out.println("[Your request has been sent to support]\n");
							Controller.exitMessage(reader);
						}
					}

					// View all sent orders
					else if (option.equals("2")) {
						Views.showOrders(Database.getOrdersFromUser(teacher));
						Controller.exitMessage(reader);
					}

					else if (option.equals("0"))
						break;
					
					else 
						System.out.println("\n[Incorrect input format. Please choose available option]");					

				}
			}

			// 5 main menu option: change password
			else if (choice.equals("5")) 
				Controller.showMenuForChangePassword(teacher, reader);

			else 
				System.out.println("\n[Incorrect input format. Please choose available option]");

		}
	}
	
	// ----------------------------------------------------------------------------
	// Subcontrollers of individual stages of information input and processing
	// Made to visually relieve the main controller
	//-----------------------------------------------------------------------------

	
	// Subcontroller: managing course files
	public static void manageCourseFiles() throws IOException {

		try {

			System.out.print("\nPlease enter ID of course from the list: ");
			String choice = reader.readLine();

			int courseId = Integer.parseInt(choice);
			Course course = Database.getCourse(courseId);

			if (teacher.isTeachingCourse(course)) 
				
				while(true) {
					
					Views.showCourseFiles(course);
					
					String managingFilesMenu =   "1. Add new file"
											 + "\n2. Delete file"
											 + "\n0. Exit back";
					
					System.out.println(managingFilesMenu);
					choice = reader.readLine();
					
					if (choice.equals("1")) {
						
						System.out.print("Please enter file name: ");
						String fileName = reader.readLine();
						
						System.out.print("Please enter file description: ");
						String fileDescription = reader.readLine();
						
						teacher.addCourseFile(course, new CourseFile(fileName, fileDescription));
						System.out.println("[File was successfully created]");
					}
					
					else if (choice.equals("2")) {
						
						System.out.print("Please enter file ID: ");
						String input = reader.readLine();
						
						int fileId = Integer.parseInt(input);
						
						if (teacher.deleteCourseFile(course, course.getCourseFile(fileId)))
							System.out.println("[File was successfully deleted]");
						else 
							System.out.println("[File with that ID doesn't exist]");
					}
					
					else if (choice.equals("0"))
						break;
					
					else 
						System.out.println("[Incorrect option. Choose available]");
					
					Controller.exitMessage(reader);
				}
			else 
				System.out.println("[Course doesn't exist or it is not yours]\n");

		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter number]\n");
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]\n");
		}
	}
	
	
	// Subcontroller: view course students and putting their marks
	public static void putMarks() throws IOException {

		try {

			System.out.print("\nPlease enter ID of course from the list: ");
			String choice = reader.readLine();

			int courseId = Integer.parseInt(choice);
			Course course = Database.getCourse(courseId);

			if (teacher.isTeachingCourse(course)) 
				
				while(true) {
					
					Views.showCourseStudentsMarks(course, course.getStudents());
					
					String puttingMarksMenu =  "\n1. Put student mark"
											 + "\n2. Search students by fullname pattern"
											 + "\n0. Exit back";
					
					System.out.println(puttingMarksMenu);
					choice = reader.readLine();
					
					if (choice.equals("1")) {
						
						System.out.print("Please enter student ID: ");
						String input = reader.readLine();
						
						int studentId = Integer.parseInt(input);
						Student student = Database.getStudent(studentId);
						
						if (course.getStudents().contains(student)) {
							
							System.out.println("\n[Student name: " + student.getFullName() + "]");
							System.out.print("Please enter score (must be <= 100): ");
							
							input = reader.readLine();
							int studentScore = Integer.parseInt(input);
							
							if (teacher.putMark(course, student, new Mark(studentScore)))
								System.out.println("\n[Mark was successfully put]");
							else
								System.out.println("\n[Mark was not put. Please check score]");
						}
						else 
							System.out.println("[Incorrect student ID]");
					}
					
					else if (choice.equals("2")) {
						
						System.out.print("\nPlease enter search pattern: ");
						String pattern = reader.readLine();
						Views.showCourseStudentsMarks(course, Database.getUsersByFullnamePattern(pattern, course.getStudents()));
					}
					
					else if (choice.equals("0"))
						break;
					
					else 
						System.out.println("[Incorrect option. Choose available]");
					
					Controller.exitMessage(reader);
						
				}
			else 
				System.out.println("[Course doesn't exist or you it is not yours]\n");

		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter number]\n");
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]\n");
		}
	}
		
	
	// Subcontroller: send message to manager
	public static void sendMessageToManager() throws IOException {

		try {
			System.out.print("\nEnter manager ID: ");
			String input = reader.readLine();
			int managerId = Integer.parseInt(input);

			System.out.println("\n[Receiver: " + Database.getManager(managerId).getFullName() + "]");
			System.out.print("[Your message]: ");

			String text = reader.readLine();

			teacher.sendMessage(new Message(teacher, Database.getManager(managerId), text));

			System.out.println("[Your message was successfully sent]");

		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format]");	
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]");
		} catch (ClassCastException exception) {
			System.out.println("[Teacher with that id doesn't exist]");
		}
	}

}
