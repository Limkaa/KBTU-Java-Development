package systemLogic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Vector;
/**
 * Database represents a storage of all objects: users, courses, etc.
 * Only exists in one copy.
 */
public class Database implements Externalizable {
	
	private static final long serialVersionUID = 6L;
	/**
	 * TreeSet which contains all Users.
	 */
	public static TreeSet<User> users = new TreeSet<User>();
	/**
	 * Vector which contains all existing Courses.
	 */
	public static Vector<Course> courses = new Vector<Course>();
	/**
	 * Vector which contains all existing Orders.
	 */
	public static Vector<Order> orders = new Vector<Order>();
	/**
	 * Vector which contains all Log Files.
	 */
	public static Vector<LogFile> logFiles = new Vector<LogFile>();
	/**
	 * Vector which contains all Messages.
	 */
	public static Vector<Message> messages = new Vector<Message>();
	
	/**
	 * Field responsible for the possibility of registration for the course.
	 */
	public static boolean registrationIsOpen = true;
	/**
	 * Represents the ID of the last created User.
	 */
	public static int counterUserId;
	/**
	 * Represents the ID of the last created Course.
	 */
	public static int counterCourseId;
	/**
	 * Represents the ID of the last created Course File.
	 */
	public static int counterCourseFileId;
	/**
	 * Represents the ID of the last created Order.
	 */
	public static int counterOrderId;
	/**
	 * Represents the ID of the last created Log File.
	 */
	public static int counterLogFileId;
	/**
	 * Represents the ID of the last created Message.
	 */
	public static int counterMessageId;

	
	private static Database INSTANCE = null;

	public Database() {}
	
	/**
	 * Gets the Database object
	 * @return Returns Database if it exists, otherwise return new Database object.
	 */
	public static Database getInstance() { 
		if (INSTANCE == null)
			INSTANCE = new Database();
		return INSTANCE;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(users);
		out.writeObject(courses);
		out.writeObject(orders);
		out.writeObject(messages);
		out.writeObject(logFiles);
		out.writeObject(registrationIsOpen);
		out.writeObject(counterUserId);
		out.writeObject(counterCourseId);
		out.writeObject(counterCourseFileId);
		out.writeObject(counterOrderId);
		out.writeObject(counterMessageId);
		out.writeObject(counterLogFileId);
	}

	@SuppressWarnings("unchecked")
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		users = (TreeSet<User>) in.readObject();
		courses = (Vector<Course>) in.readObject();
		orders = (Vector<Order>) in.readObject();
		messages = (Vector<Message>) in.readObject();
		logFiles = (Vector<LogFile>) in.readObject();
		registrationIsOpen = (Boolean) in.readObject();
		counterUserId = (int) in.readObject();
		counterCourseId = (int) in.readObject();
		counterCourseFileId = (int) in.readObject();
		counterOrderId = (int) in.readObject();
		counterMessageId = (int) in.readObject();
		counterLogFileId = (int) in.readObject();
		
	}
	
	
	/**
	 * Searching and getting User object by username.
	 * @param username user with that username will be searched.
	 * @return Object of class User if exists and null if doesn't.
	 */
	public static User getUser(String username) {
		for (User user: users)
			if (user.getUsername().equals(username))
				return user;
		return null;
	}
	
	/**
	 * Searching and getting User object by ID.
	 * @param id User with that ID will be searched.
	 * @return Object of class User if exists and null if doesn't
	 */
	public static User getUser(int id) {
		for (User user: users)
			if (user.getId() == id)
				return user;
		return null;
	}

	/**
	 * Gets all Users.
	 * @return Vector which contains all existing Users.
	 */
	public static Vector<User> getUsers() {
		Vector<User> usersList = new Vector<User>();
		for (User user: users)
			usersList.add(user);
		return usersList;
	}

	/**
	 * Gets all Users with given name pattern.
	 * @param pattern User's name and surname(separated by ' ') or just part.
	 * @param users Vector of users where to search.
	 * @return Vector of found Users.
	 */
	public static <T> Vector<T> getUsersByFullnamePattern(String pattern, Vector<T> users) {
		Vector<T> usersList = new Vector<T>();
		for (T user: users)
			if (((User)user).getFullName().toLowerCase().startsWith(pattern.toLowerCase()))
				usersList.add((T)user);
		return usersList;
	}

	/**
	 * Search and gets Student by ID.
	 * @param id ID of required Student.
	 * @return Return Student object if it exists, otherwise null.
	 */
	public static Student getStudent(int id) {
		for (Student student: getStudents())
			if (student.getId() == id)
				return student;
		return null;
	}

	/**
	 * Gets all Students.
	 * @return Vector which contains all existing Students.
	 */
	public static Vector<Student> getStudents() {
		Vector<Student> students = new Vector<Student>();
		for (User user: users)
			if (user instanceof Student)
				students.add((Student)user);
		return students;
	}

	/**
	 * Gets all Students sorted by GPA.
	 * @return Vector which contains all existing Students sorted by GPA.
	 */
	public static Vector<Student> getStudentsSortedGPA() {
		Vector<Student> students = new Vector<Student>();
		for (Student student: getStudents())
			students.add(student);
		students.sort(new GpaComparator());
		return students;
	}

	/**
	 * Search and gets Teacher by ID.
	 * @param id ID of required Teacher.
	 * @return Return Teacher object if it exists, otherwise null.
	 */
	public static Teacher getTeacher(int id) {
		for (Teacher teacher: getTeachers())
			if (teacher.getId() == id)
				return teacher;
		return null;
	}

	/**
	 * Gets all Teachers.
	 * @return Vector which contains all existing Teachers.
	 */
	public static Vector<Teacher> getTeachers() {
		Vector<Teacher> teachers = new Vector<Teacher>();
		for (User user: users)
			if (user instanceof Teacher)
				teachers.add((Teacher)user);
		return teachers;
	}

	/**
	 * Search and gets Manager by ID.
	 * @param id ID of required Manager.
	 * @return Return Manager object if it exists, otherwise null.
	 */
	public static ORManager getManager(int id) {
		for (ORManager manager: getManagers())
			if (manager.getId() == id)
				return manager;
		return null;
	}

	/**
	 * Gets all Managers.
	 * @return Vector which contains all existing Managers.
	 */
	public static Vector<ORManager> getManagers() {
		Vector<ORManager> managers = new Vector<ORManager>();
		for (User user: users)
			if (user instanceof ORManager)
				managers.add((ORManager)user);
		return managers;
	}

	/**
	 * Search and gets Course by ID.
	 * @param id ID of required Course.
	 * @return Return Course object if it exists, otherwise null.
	 */
	public static Course getCourse(int id) {
		for (Course course: courses)
			if (course.getId() == id)
				return course;
		return null;
	}

	/**
	 * Gets all messages sent to User
	 * @param user The user whose Messages we want to see.
	 * @return Vector of all Messages sent to it.
	 */
	public static Vector<Message> getMessagesToUser(User user) {
		Vector<Message> messagesList = new Vector<Message>();
		for (Message message: messages)
			if (message.getReceiver().equals(user)) {
				messagesList.add(message);
			}
		Collections.reverse(messagesList);
		return messagesList;
	}
	
	/**
	 * Gets all User's unread messages.
	 * @param user The user whose Messages we want to see.
	 * @return Vector of all User's unread Messages.
	 */
	public static Vector<Message> getUnreadMessagesToUser(User user) {
		Vector<Message> messagesList = new Vector<Message>();
		for (Message message: messages)
			if (message.getReceiver().equals(user) && 
					message.getStatus().equals(MessageStatus.UNREAD)) {
				messagesList.add(message);
			}
		Collections.reverse(messagesList);
		return messagesList;
	}
	
	/**
	 * Gets all messages sent by User.
	 * @param user The user whose Messages we want to see.
	 * @return Vector of messages sent by required User
	 */
	public static Vector<Message> getMessagesFromUser(User user) {
		Vector<Message> messagesList = new Vector<Message>();
		for (Message message: messages)
			if (message.getSender().equals(user)) {
				messagesList.add(message);
			}
		Collections.reverse(messagesList);
		return messagesList;
	}
	
	/**
	 * Search and gets Order by ID.
	 * @param id ID of required Order.
	 * @return Return Order object if it exists, otherwise null.
	 */
	public static Order getOrder(int id) {
		for (Order order: orders)
			if (order.getOrderId() == id)
				return order;
		return null;
	}
	
	/**
	 * Search and gets all Orders sent by User.
	 * @param user The user whose Orders we want to see.
	 * @return Vector of Orders sent by required User.
	 */
	public static Vector<Order> getOrdersFromUser(User user) {
		Vector<Order> userOrders = new Vector<Order>();
		for (Order order: orders)
			if (order.getSender().equals(user))
				userOrders.add(order);
		return userOrders;
	}
	
}
