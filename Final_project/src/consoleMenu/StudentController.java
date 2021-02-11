package consoleMenu;

import java.io.BufferedReader;
import java.io.IOException;

import systemLogic.Course;
import systemLogic.Database;
import systemLogic.Student;
import systemLogic.User;

public class StudentController {

	static Student student = null;
	static BufferedReader reader = null;
	
	// Student menu
	public static void menu(User user, BufferedReader bufReader) throws IOException {

		student = (Student)user;
		reader = bufReader;
		
		String studentHomePage  = "\n[Student: " + student.getFullName() + "]"
								+ "\n---------------------------------------"
								+ "\n1. View my courses info/files"
								+ "\n2. Manage registration for courses"
								+ "\n3. View transcript"
								+ "\n4. Change password"
								+ "\n0. Logout";

		while (student.isLoginned()) {

			System.out.println(studentHomePage);
			String choice = reader.readLine();

			// Option: log out of account
			if (choice.equals("0")) {
				student.logout();
				System.out.println("\n[You logged out]");
			}

			// 2 main menu option: view registered courses and their files
			else if (choice.equals("1")) 
				
				if (student.getCourses().size() > 0)
					while (true) {
						
						Views.showCourses(student, 7, student.getCourses());
			
						String menuCoursesInfo  = "\n1. View course files "
												+ "\n0. Exit to main menu";
		
						System.out.println(menuCoursesInfo);	
						choice = reader.readLine();
		
						// Back to main menu
						if (choice.equals("0"))
							break;
						
						// View course files
						else if (choice.equals("1")) {
							StudentController.checkCourseFiles();
							Controller.exitMessage(reader);
						}
						
						else 
							System.out.println("\n[Incorrect input format. Please choose available option]\n");
					}
				else {
					System.out.println("[There are no any registered courses yet]");
					Controller.exitMessage(reader);
				}
			

			// 2 main menu option: managing registration for courses
			else if (choice.equals("2")) 

				if (Database.registrationIsOpen) 
					
					while (true) {

						if (student.getCoursesForRegistration().size() > 0) {
							Views.showCourses(student, 8, student.getCoursesForRegistration());
		
							String manageRegistrationMenu =   "\n1. Add course for registration"
															+ "\n2. Delete course from registration"
															+ "\n0. Exit to main menu";
		
							System.out.println(manageRegistrationMenu);	
							choice = reader.readLine();
		
							// Back to main menu
							if (choice.equals("0"))
								break;
							
							// Add/Delete course in registration
							else if (choice.equals("1") || choice.equals("2")) {
								StudentController.processCourseRegistrationOptions(choice);
								Controller.exitMessage(reader);
							}
							
							else 
								System.out.println("\n[Incorrect input format. Please choose available option]\n");
						}
						else {
							System.out.println("[There are no any courses available for registration yet. Please wait, they will be added by manager very soon ]");
							Controller.exitMessage(reader);
						}
					}
				
				else {
					System.out.println("[Registration for courses is closed]");
					Controller.exitMessage(reader);
				}


			// 3 main menu option: view transcript
			else if (choice.equals("3")) {
				Views.showTranscript(student);
				Controller.exitMessage(reader);
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

	// Subcontroller: view course files
	public static void checkCourseFiles() throws IOException {

		try {
			
			System.out.print("\nPlease enter ID of course from the list: ");
			String choice = reader.readLine();
			
			int courseId = Integer.parseInt(choice);
			Course course = Database.getCourse(courseId);
			
			if (student.isHavingCourse(course)) 
				Views.showCourseFiles(course);
			else 
				System.out.println("[Course doesn't exist or you haven't studied it before]\n");
			
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter number]\n");
		}
	}

	// Subcontroller: processing choosed option for course registration
	public static void processCourseRegistrationOptions(String option) throws IOException {

		try {
			System.out.print("\nPlease enter ID of course from the list: ");
			String input = reader.readLine();

			int courseId =  Integer.parseInt(input);

			if (option.equals("1"))
				if (student.registerForCourse(Database.getCourse(courseId)))
					System.out.println("[Successfully added to registration]\n");
				else
					System.out.println("[Incorrect course ID, course is full or you are already registered for this course]\n");

			else if (option.equals("2"))
				if (student.unregisterFromCourse(Database.getCourse(courseId)))
					System.out.println("[Successfully deleted from registration]\n");
				else
					System.out.println("[Incorrect course ID or you weren't registered for the course]\n");
			
		} catch (NumberFormatException exception) {
			System.out.println("[Incorrect input format. Please enter ID number]\n");	
		} catch (NullPointerException exception) {
			System.out.println("[Incorrect ID number]\n");	
		}
	}

	
}
