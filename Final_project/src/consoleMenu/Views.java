package consoleMenu;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import systemLogic.Course;
import systemLogic.CourseFile;
import systemLogic.LogFile;
import systemLogic.Mark;
import systemLogic.Message;
import systemLogic.ORManager;
import systemLogic.Order;
import systemLogic.Student;
import systemLogic.Teacher;
import systemLogic.User;

public class Views {

	// Visualization element: Formatting the table row template to display data
	public static String createVisualTableRow(int numOfColumn, int[] columnSizes) {
		String result = "|";
		for (int i = 0; i < numOfColumn; i++) {
			result += " %-" + (columnSizes[i]-2) + "s |";
		}

		result += "\n+";
		for (int i = 0; i < numOfColumn; i++) {
			for (int j = 0; j < columnSizes[i]; j++) {
				result += "-";
			}
			result += "+";
		}
		return result;
	}

	// View for displaying info of some students
	public static void showStudents(int columnsNum, Vector<Student> students) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(columnsNum, new int[] {5, 50, 12, 12, 9, 8})
				.formatted("ID", "Student name", "Speciality", "Study year", "Credits", "GPA"));


		for (Student student: students)
			System.out.println(createVisualTableRow(columnsNum, new int[]{5, 50, 12, 12, 9, 8})
					.formatted(
							student.getId(), 
							student.getFullName(),
							student.getSpeciality(),
							student.getStudyYear(),
							student.getOverallCredits(),
							"%.2f".formatted(student.getOverallGpa())
							));
	}

	// View for displaying info of some teachers 
	public static void showTeachers(Vector<Teacher> teachers) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(3, new int[] {5, 50, 20})
				.formatted("ID", "Teacher name", "Role"));

		for (Teacher teacher: teachers)
			System.out.println(createVisualTableRow(3, new int[] {5, 50, 20})
					.formatted(
							teacher.getId(), 
							teacher.getFullName(),
							teacher.getRole()));
	}
	
	// View for displaying info of some managers
	public static void showManagers(Vector<ORManager> managers) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(2, new int[] {5, 50})
				.formatted("ID", "Manager name"));

		for (ORManager manager: managers)
			System.out.println(createVisualTableRow(2, new int[] {5, 50})
					.formatted(
							manager.getId(), 
							manager.getFullName()));
	}
	
	// View for displaying info of some users
	public static void showUsers(Vector<User> users) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(3, new int[] {5, 50, 25})
				.formatted("ID", "Name", "User type"));

		for (User user: users)
			System.out.println(createVisualTableRow(3, new int[] {5, 50, 25})
					.formatted(
							user.getId(), 
							user.getFullName(),
							user.getClass().getSimpleName()));
	}

	// View for displaying info of some courses
	public static void showCourses(User user, int columnsNum, Vector<Course> courses) {

		System.out.println();
		
		System.out.println(createVisualTableRow(columnsNum, new int[] {5, 40, 50, 9, 12, 40, 10, 12})
				.formatted("ID", "Discipline title", "Speciality" ,"Credits", "Study year", "Teacher", "Students", "Registered"));

		for (Course course: courses) 
			System.out.println(createVisualTableRow(columnsNum, new int[] {5, 40, 50, 9, 12, 40, 10, 12})
					.formatted(
							course.getId(), 
							course.getTitle(), 
							course.getSpeciality(),
							course.getCredits(),
							course.getStudyYear(),
							(course.getTeacher().getFullName()) + " [" + course.getTeacher().getRole() + "]",
							(course.getStudents().size() + "/" + course.getStudentsLimit()),
							course.getStudents().contains(user)
							)); 

	}

	// View for displaying info of some course files
	public static void showCourseFiles(Course course) {

		System.out.println();

		System.out.println(createVisualTableRow(1, new int[] {97})
				.formatted("Discipline: " + course.getTitle()));

		System.out.println(createVisualTableRow(3, new int[] {5, 30, 60})
				.formatted("ID", "File name", "Description"));

		for (CourseFile file: course.getCourseFiles())
			System.out.println(createVisualTableRow(3, new int[] {5, 30, 60})
					.formatted(
							file.getId(), 
							file.getName(), 
							file.getDescription()
							));

		System.out.println();

	}
	
	// View for displaying info of some students marks of some course
	public static void showCourseStudentsMarks(Course course, Vector<Student> students) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(7, new int[] {5, 50, 12, 12, 7, 12, 13})
				.formatted("ID", "Student name", "Speciality", "Study year", "Score", "Digit mark", "Letter mark"));


		for (Student student: students)
			System.out.println(createVisualTableRow(7, new int[]{5, 50, 12, 12, 7, 12, 13})
					.formatted(
							student.getId(), 
							student.getFullName(),
							student.getSpeciality(),
							student.getStudyYear(),
							student.getCourseMark(course).getScore(),
							student.getCourseMark(course).getDigitMark(),
							student.getCourseMark(course).getTextMark()
							));
	}

	// View for displaying info of some student transcript
	public static void showTranscript(Student student) throws IOException {

		System.out.println();

		System.out.println(createVisualTableRow(1, new int[] {114})
				.formatted(("Transcript of student: " + student.getFullName() + " [ID:" + student.getId() + "]"
						+ " [Speciality: " + student.getSpeciality() + "]")));

		System.out.println(createVisualTableRow(7, new int[] {5, 50, 9, 12, 7, 12, 13})
				.formatted("ID", "Discipline title", "Credits", "Study year", "Score", "Digit mark", "Letter mark"));

		for (Map.Entry<Course, Mark> course: student.getTranscript().entrySet())
			System.out.println(createVisualTableRow(7, new int[] {5, 50, 9, 12, 7, 12, 13})
					.formatted(
							course.getKey().getId(), 
							course.getKey().getTitle(), 
							course.getKey().getCredits(),
							course.getKey().getStudyYear(),
							course.getValue().getScore(),
							course.getValue().getDigitMark(),
							course.getValue().getTextMark()
							));

		System.out.println(
				"\nNumber of registered disciplines: " + student.getCourses().size()
				+ "\nOverall Credits: " + student.getOverallCredits()
				+ "\nOverall GPA: %.2f".formatted(student.getOverallGpa()));

		System.out.println();

	}
	
	// View for displaying info of incoming messages to user
	public static void showIncomingMessages(Vector<Message> messages) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(4, new int[] {21, 30, 70, 8})
				.formatted("Sending time", "Sender", "Message text", "Status"));

		for (Message message: messages)
			System.out.println(createVisualTableRow(4, new int[] {21, 30, 70, 8})
					.formatted(
							message.getSendingTime(), 
							message.getSender().getFullName(), 
							message.getText(),
							message.getStatus()
							));
		
		System.out.println();
	}
	
	// View for displaying info of messages sent by user
	public static void showSentMessages( Vector<Message> messages) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(4, new int[] {21, 30, 70, 8})
				.formatted("Sending time", "Receiver", "Message text", "Status"));

		for (Message message: messages)
			System.out.println(createVisualTableRow(4, new int[] {21, 30, 70, 8})
					.formatted(
							message.getSendingTime(), 
							message.getReceiver().getFullName(), 
							message.getText(),
							message.getStatus()
							));
		
		System.out.println();
	}
	
	// View for displaying info of some orders
	public static void showOrders(Vector<Order> orders) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(5, new int[] {5, 21, 30, 70, 15})
				.formatted("ID", "Sending time", "Sender", "Order message", "Status"));

		for (Order order: orders)
			System.out.println(createVisualTableRow(5, new int[] {5, 21, 30, 70, 15})
					.formatted(
							order.getOrderId(),
							order.getSendingTime(),
							order.getSender().getFullName(),
							order.getText(),
							order.getStatus()
							));
		
		System.out.println();
	}
	
	// View for displaying info of some log files
	public static void showLogs(Vector<LogFile> logs) throws IOException {

		System.out.println();
		
		System.out.println(createVisualTableRow(3, new int[] {5, 21, 25})
				.formatted("ID", "Action time", "Log type"));

		for (LogFile log: logs)
			System.out.println(createVisualTableRow(3, new int[] {5, 21, 25})
					.formatted(
							log.getLogId(),
							log.getActionTime(),
							log.getType()
							));
		
		System.out.println();
	}

}
