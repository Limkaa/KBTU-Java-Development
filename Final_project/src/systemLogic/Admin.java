package systemLogic;

public class Admin extends Employee implements OrderSendable {

	private static final long serialVersionUID = 3L;

	public Admin() {}
	public Admin(String name, String surname, Departments department) {
		super(name, surname, department);
	}
	
	// Adding user
	public boolean addUser(User newUser) {
		for (User user: Database.users)
			if (user.getUsername().equals(newUser.getUsername())) {
				return false;
			}
		Database.users.add(newUser);
		Database.logFiles.add(new LogFile(LogType.USER_ADDED));
		return true;
	}
	
	// Delete user
	public boolean deleteUser(User user) {
		
		if (user instanceof Teacher) 
			if (!((Teacher)user).getTeachingCourses().isEmpty())
				return false;
		
		else if (user instanceof Student)
			for (Course course: Database.courses)
				course.getStudents().remove(user);
		
		else if (user instanceof TechSupportGuy)
			for (Order order: Database.orders) 
				if (order.getExecutor().equals(user) && order.getStatus().equals(OrderStatus.ACCEPTED)) {
					order.setExecutor(null);
					order.setStatus(OrderStatus.NEW);
				}
		
		Database.users.remove(user);
		Database.logFiles.add(new LogFile(LogType.USER_DELETED));
		return true;
	}	
	
	
	// Update students study year
	public void updateStudentsYear() {
		for (Student student: Database.getStudents())
			student.increaseStudyYear();
	}
	
	// Update teacher role
	public void updateTeacherRole(Teacher teacher, Role role) {
		teacher.setRole(role);
	}
	
	
}
