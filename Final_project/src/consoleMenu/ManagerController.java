package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import systemLogic.Course;
import systemLogic.Database;
import systemLogic.Message;
import systemLogic.MessageStatus;
import systemLogic.ORManager;
import systemLogic.Order;
import systemLogic.Speciality;
import systemLogic.Teacher;
import systemLogic.User;

public class ManagerController {

	static ORManager manager = null;
	static BufferedReader reader = null;
	
	// Manager menu
	public static void menu (User user, BufferedReader bufReader) throws IOException {
		
		manager = (ORManager)user;
		reader = bufReader;

		while (user.isLoginned()) {
			
			String managerHomePage  = "\n[Manager: " + user.getFullName() + "]"
									+ "\n----------------------------------"
									+ "\n1. Manage courses and registration"
									+ "\n2. View students and teachers info"
									+ "\n3. Send/View messages to teachers [Unread:" + Database.getUnreadMessagesToUser(manager).size() + "]"   
									+ "\n4. Send/View orders to support"
									+ "\n5. Change password"
									+ "\n0. Logout";
			
			
			System.out.println(managerHomePage);
			String choice = reader.readLine();

			
			// Option: log out of account
			if (choice.equals("0")) {
				user.logout();
				System.out.println("\n[You logged out]");
			}

			
			// 2 main menu option: manage courses and registration statuc
			else if (choice.equals("1"))
				while(true) {
					
					String menuManageCourses= "\nManaging courses and registration"
											+ "\n---------------------------------"
											+ "\n1. Create new course"
											+ "\n2. Delete course"
											+ "\n3. Update course info"
											+ "\n4. View all courses"
											+ "\n5. Open/close registration for students"
											+ "\n0. Exit to main menu";

					System.out.println(menuManageCourses);	
					choice = reader.readLine();

					// Back to main menu
					if (choice.equals("0"))
						break;
					
					// Create new course
					else if (choice.equals("1")) {
						if (Database.getTeachers().size() > 0) {
							ManagerController.createNewCourse();
							Controller.exitMessage(reader);
						}
						else { 
							System.out.println("[There are no any teachers yet, who can teach course]");
							Controller.exitMessage(reader);
						}
					}

					// Delete course
					else if (choice.equals("2")) {
						if (Database.courses.size() > 0) {
							ManagerController.deleteCourse();
							Controller.exitMessage(reader);
						}
						else {
							System.out.println("[There are no any courses yet]");
							Controller.exitMessage(reader);
						}
					}
					
					// Update course info
					else if (choice.equals("3")) 
						
						if (Database.courses.size() > 0) {
							while(true) {
								
								Views.showCourses(manager, 7, Database.courses);
								
								String detailInfoMenu =   "\n1. Choose course for updating"
														+ "\n0. Exit back";
	
								System.out.println(detailInfoMenu);
								String option = reader.readLine();
								
								if (option.equals("1")) {
									ManagerController.updateCourse();
									Controller.exitMessage(reader);
								}
	
								else if (option.equals("0"))
									break;
								else 
									System.out.println("[Incorrect input format. Please choose available option]");
							}
						}
						else {
							System.out.println("[There are no any courses yet]");
							Controller.exitMessage(reader);
						}

					// View all created courses
					else if (choice.equals("4")) 
						
						if (Database.courses.size() > 0) {
							while(true) {
								
								Views.showCourses(manager, 7, Database.courses);
								
								String detailInfoMenu =   "\n1. View students of the course"
														+ "\n0. Exit back";
	
								System.out.println(detailInfoMenu);
								String option = reader.readLine();
	
								if (option.equals("1")) {
									ManagerController.showStudentsRegisteredForCourse();
									Controller.exitMessage(reader);
								}
								else if (option.equals("0"))
									break;
								else 
									System.out.println("[Incorrect input format. Please choose available option]");
							}
						}
						else {
							System.out.println("[There are no any courses yet]");
							Controller.exitMessage(reader);
						}
									
					// Open/Close registration for courses for students
					else if (choice.equals("5")) 
						while(true) {
							
							String changeStatusMenu = "\nRegistration is open: " + Database.registrationIsOpen
													+ "\n1. Open registration for students"
													+ "\n2. Close registration for students"
													+ "\n0. Exit back";

							System.out.println(changeStatusMenu);
							String option = reader.readLine();

							if (option.equals("1")) 
								manager.openRegistrationForStudents();
							
							else if (option.equals("2"))
								manager.closeRegistrationForStudents();
								
							else if (option.equals("0"))
								break;
							else 
								System.out.println("[Incorrect input format. Please choose available option]");
						}

					else 
						System.out.println("\n[Incorrect input format. Please choose available option]");
				}

			

			// 2 main menu option: view users info
			else if (choice.equals("2")) {
				while(true) {
					
					String menuUserInfo = "\nView users information"
										+ "\n----------------------"
										+ "\n1. Students"
										+ "\n2. Teachers"
										+ "\n0. Exit to main menu";

					System.out.println(menuUserInfo);	
					choice = reader.readLine();

					// View students info
					if (choice.equals("1")) 
						
						if (Database.getStudents().size() > 0) {
							while(true) {
								Views.showStudents(6, Database.getStudents());
								
								String detailInfoMenu   = "\n1. Show transcript of student"
														+ "\n2. Sort students by GPA"
														+ "\n3. Search students by fullname pattern"
														+ "\n0. Exit back";
		
								System.out.println(detailInfoMenu);
								String option = reader.readLine();
										
								if (option.equals("1")) {
									ManagerController.showStudentTranscript();
									Controller.exitMessage(reader);
								}
								else if (option.equals("2")) {
									Views.showStudents(6, Database.getStudentsSortedGPA());
									Controller.exitMessage(reader);
								}
								
								else if (option.equals("3")) {
									System.out.print("\nPlease enter search pattern: ");
									String pattern = reader.readLine();
									Views.showStudents(6, Database.getUsersByFullnamePattern(pattern, Database.getStudents()));
									Controller.exitMessage(reader);
								}
								else if (option.equals("0"))
									break;
								else 
									System.out.println("\n[Incorrect input format. Please choose available option]");
							}
						}
						else {
							System.out.println("[There are no any students yet]");
							Controller.exitMessage(reader);
						}
						
					// View teachers info
					else if (choice.equals("2")) 
						
						if (Database.getTeachers().size()>0) {
							while(true) {
								Views.showTeachers(Database.getTeachers());
								
								String menuDetailInfo   = "\n1. Show courses of teacher"
														+ "\n2. Search teachers by fullname pattern"
														+ "\n0. Exit back";
		
								System.out.println(menuDetailInfo);
								String option = reader.readLine();
										
								if (option.equals("1")) {
									ManagerController.showTeacherCourses();
									Controller.exitMessage(reader);
								}
								else if (option.equals("2")) {
									System.out.print("\nPlease enter search pattern: ");
									String pattern = reader.readLine();
									Views.showTeachers(Database.getUsersByFullnamePattern(pattern, Database.getTeachers()));
									Controller.exitMessage(reader);
								}
								else if (option.equals("0"))
									break;
								else 
									System.out.println("\n[Incorrect input format. Please choose available option]");
							}
						}
						else {
							System.out.println("[There are no any teachers yet]");
							Controller.exitMessage(reader);
						}
					
					// Back to main menu
					else if (choice.equals("0"))
						break;
					else 
						System.out.println("\n[Incorrect input format. Please choose available option]");
				}
			}

			// 3 main menu option: manage messages to teachers
			else if (choice.equals("3")) {
				
				while (true) {
					
					String detailInfoMenu =   "\n1. Send message to teacher"
											+ "\n2. View all sent messages"
											+ "\n3. View all incoming messages [Unread:" + Database.getUnreadMessagesToUser(manager).size() + "]"
											+ "\n0. Exit main";
	
					System.out.println(detailInfoMenu);
					String option = reader.readLine();

					if (option.equals("1")) {
						
						if (Database.getTeachers().size() > 0) {
							while(true) {
								
								Views.showTeachers(Database.getTeachers());
								
								String detailSendingMenu ="\n1. Choose teacher"
														+ "\n0. Exit back";
								
								System.out.println(detailSendingMenu);
								option = reader.readLine();
										
								if (option.equals("1")) {
									ManagerController.sendMessageToTeacher();
									Controller.exitMessage(reader);
								}
								else if (option.equals("0"))
									break;
								else 
									System.out.println("\n[Incorrect input format. Please choose available option]");
								
							}
						}
						else {
							System.out.println("[There are no any teachers yet]");
							Controller.exitMessage(reader);
						}
					}
					
					else if (option.equals("2")) {
						Views.showSentMessages(Database.getMessagesFromUser(manager));
						Controller.exitMessage(reader);
					}
					
					else if (option.equals("3")) {
						Views.showIncomingMessages(Database.getMessagesToUser(manager));
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
							manager.sendOrder(new Order(manager, text));
							System.out.println("[Your request has been sent to support]\n");
							Controller.exitMessage(reader);
						}
					}

					// View all sent orders
					else if (option.equals("2")) {
						Views.showOrders(Database.getOrdersFromUser(manager));
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
				Controller.showMenuForChangePassword(manager, reader);

			else 
				System.out.println("\n[Incorrect input format. Please choose available option]");
		}
	}

	// ----------------------------------------------------------------------------
	// Subcontrollers of individual stages of information input and processing
	// Made to visually relieve the main controller
	//-----------------------------------------------------------------------------
	
	// Subcontroller: Entering information to create a new course
	public static void createNewCourse() throws IOException {

		try {
			String title;
			int credits, studyYear, studentsLimit;
			Vector<Speciality> speciality = new Vector<Speciality>();

			System.out.print("\nCourse title: ");
			title = reader.readLine();

			System.out.print("Number of credits: ");
			credits = Integer.parseInt(reader.readLine());

			if (credits > 0) {
				System.out.print("Study year: ");
				studyYear = Integer.parseInt(reader.readLine());
	
				if (studyYear > 0) {
					System.out.print("Limit of students: ");
					studentsLimit = Integer.parseInt(reader.readLine());
		
					if (studentsLimit >= 0) {
						System.out.println("\nSpecialities (choose options):");
						for (Speciality specialityTitle: Speciality.values()) {
			
							while(true) {
								System.out.print("[" + specialityTitle + "] Option (+/-): ");
								String choice = reader.readLine();
			
								if (choice.equals("+")) {
									speciality.add(specialityTitle);
									break;
								}
								else if (choice.equals("-"))
									break;
								else
									System.out.println("[Incorrect input format. Please choose option '+' or '-']");
							}
						}
			
						if (Database.getTeachers().size() > 0) {
							System.out.println("\nTeachers:");
							Views.showTeachers(Database.getTeachers());
				
							System.out.print("\nEnter ID of teacher: ");
							int teacherId = Integer.parseInt(reader.readLine());
							Teacher teacher = Database.getTeacher(teacherId);
							
							if (teacher != null) {
								if (manager.addNewCourse(new Course(title, credits, teacher, studyYear, studentsLimit, speciality)))
									System.out.println("\n[Course was successfully created]");
								else
									System.out.println("\n[Course creation disrupted. The similar course is already created]");
							}
							else 
								System.out.println("[Course creation disrupted. Incorrect teacher ID number]");
						}
						else 
							System.out.println("[Course creation disrupted. There are no teachers]");
					}
					else 
						System.out.println("[Course creation disrupted. Students limit number must be 0 or more]");	
				}
				else 
					System.out.println("[Course creation disrupted. Study year must be 1 or more]");	
			}
			else 
				System.out.println("[Course creation disrupted. Cretits number must be 1 or more]");

		} catch (NumberFormatException exception) {
			System.out.println("\n[Course creation disrupted. Incorrect input format. Enter number]");	
		} catch (NullPointerException exception) {
			System.out.println("\n[Course creation disrupted. Incorrect ID number]");
		} catch (ClassCastException exception) {
			System.out.println("\n[Teacher with that id doesn't exist]");
		}
	}
	
	
	// Subcontroller: course deletion
	public static void deleteCourse() throws IOException {
		
		try {
			Views.showCourses(manager, 7, Database.courses);
			
			System.out.print("\nEnter course ID (0 to cancel operation): ");
			String input = reader.readLine();
			int courseId = Integer.parseInt(input);
			
			if (!input.equals("0"))
				if (manager.deleteCourse(Database.getCourse(courseId)))
					System.out.println("\n[Course was successfully deleted]");
				else
					System.out.println("\n[Course can't be deleted. There are students registered for it or course ID is incorrect]");

		} catch (NumberFormatException exception) {
			System.out.println("\n[Course deletion disrupted. Incorrect input format. Please enter course ID]");	
		} catch (NullPointerException exception) {
			System.out.println("\n[Course deletion disrupted. Incorrect ID number]");
		} 
	}
	
	// Subcontroller: course info updating
	public static void updateCourse() throws IOException {
		
		try {
			System.out.print("\nEnter course ID: ");
			String input = reader.readLine();
			int courseId = Integer.parseInt(input);
			
			Course course = Database.getCourse(courseId);
			
			if (course != null) {
				
				String updateMenu =   "\n1. Change limit of students"
									+ "\n2. Add speciality"
									+ "\n0. Exit back";
	
				System.out.println("[Chosen course: " + course.getTitle() + "]");
					
				System.out.println(updateMenu);
				input = reader.readLine();
			
				if (input.equals("1")) {
					System.out.print("\nEnter new limit of students: ");
					input = reader.readLine();
	
					if(!manager.changeCourseStudentsLimit(course, Integer.parseInt(input))) 
						System.out.println("[New limit is less than number of registered students]");
					else 
						System.out.println("[Limit has beed successfully changed]");
				}
				
				else if (input.equals("2")) {
					for (Speciality specialityTitle: course.getNotIncludedSpeciality()) {
	
						while(true) {
							System.out.print("[" + specialityTitle + "] Option (+/-): ");
							String choice = reader.readLine();
	
							if (choice.equals("+")) {
								manager.addCourseSpeciality(course, specialityTitle);
								break;
							}
							else if (choice.equals("-"))
								break;
							else
								System.out.println("[Incorrect input format. Please choose option '+' or '-']");
						}
					}
					System.out.println("[Speciality list was succesfully changed]");
				}
				else if (input.equals("0")) {}
				else
					System.out.println("[Incorrect option]");
			}
			else 
				System.out.println("\n[Incorrect ID number]");
			
		} catch (NumberFormatException exception) {
			System.out.println("\n[Incorrect input format. Please enter number]");	
		} 
	}

	
	// Subcontroller: show students registered for course
	public static void showStudentsRegisteredForCourse() throws IOException {
		
		try {
			System.out.print("\nEnter course ID: ");
			String input = reader.readLine();
			int courseId = Integer.parseInt(input);
			
			Views.showStudents(6, Database.getCourse(courseId).getStudents());
			
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format]");	
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]");
		} 

	}
	
	
	// Subcontroller: show student transcript
	public static void showStudentTranscript() throws IOException {
		
		try {
			System.out.print("\nEnter student ID: ");
			
			String input = reader.readLine();
			int id = Integer.parseInt(input);
			
			Views.showTranscript(Database.getStudent(id));
			
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format]");	
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]");
		} catch (ClassCastException exception) {
			System.out.println("[Student with that id doesn't exist]");
		}
	}

	
	// Subcontroller: show teacher courses
	public static void showTeacherCourses() throws IOException {
		
		try {
			System.out.print("\nEnter teacher ID: ");
			String input = reader.readLine();
			int teacherId = Integer.parseInt(input);
					
			Views.showCourses(manager, 7, Database.getTeacher(teacherId).getTeachingCourses());
		
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter ID number]");	
		} catch (NullPointerException exception) {
			System.out.println("[Teacher doesn't have any courses or ID number isn't correct]");
		}
	}
	
	
	// Subcontroller: send message to teacher
	public static void sendMessageToTeacher() throws IOException {
		
		try {
			System.out.print("\nEnter teacher ID: ");
			String input = reader.readLine();
			int teacherId = Integer.parseInt(input);
					
			System.out.println("\n[Receiver: " + Database.getTeacher(teacherId).getFullName() + "]");
			System.out.print("[Your message]: ");
			
			String text = reader.readLine();
			
			manager.sendMessage(new Message(manager, Database.getTeacher(teacherId), text));
			
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
