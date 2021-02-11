package systemLogic;
/**
 * ORManagaer represents Manager's account.
 *
 */
public class ORManager extends Employee implements MessageSendable, OrderSendable{

	private static final long serialVersionUID = 11L;
	
	public ORManager() {}
	/**
	 * Creates and initialize ORManager object with given parameters.
	 * @param name Manager name.
	 * @param surname Manager surname.
	 * @param department Manager department.
	 */
	public ORManager(String name, String surname,  Departments department) {
		super(name, surname, department);
	}
	
	/**
	 * Adds new Course for registration.
	 * @param newCourse New course.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean addNewCourse(Course newCourse) {
		if (newCourse != null && newCourse.getTeacher() != null && 
				newCourse.getStudentsLimit() >= 0 && newCourse.getCredits() > 0 &&
				newCourse.getStudyYear() > 0) {
			for (Course course: Database.courses)
				if (course.getTitle().equals(newCourse.getTitle()) && course.getTeacher().equals(newCourse.getTeacher()))
					return false;
			Database.courses.add(newCourse);
			Database.logFiles.add(new LogFile(LogType.COURSE_CREATED));
		}
		return true; 
	}
	
	/**
	 * Removes Course.
	 * @param course Course to remove.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean deleteCourse(Course course) {
		if (course != null)
			if (course.getStudents().size() == 0) {
				Database.courses.remove(course);
				Database.logFiles.add(new LogFile(LogType.COURSE_DELETED));
				return true;
			}
		return false;
	}
	
	/**
	 * Changes student limit for the Course.
	 * @param course Course to change.
	 * @param newLimit New limit.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean changeCourseStudentsLimit(Course course, int newLimit) {
		if (course != null) {
			if (course.getStudents().size() > newLimit)
				return false;
			course.setStudentsLimit(newLimit);
		}
		return true;
	}
	
	/**
	 * Adds new available speciality to the Course.
	 * @param course Course to update.
	 * @param speciality New Speciality.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean addCourseSpeciality(Course course, Speciality speciality) {
		if (course != null) {
			if (course.getSpeciality().contains(speciality))
				return false;
			course.addSpeciality(speciality);
		}
		return true;
	}
	
	/**
	 * Open registration for Students.
	 */
	public void openRegistrationForStudents() {
		Database.registrationIsOpen = true;
	}
	
	/**
	 * Close registration for Students.
	 */
	public void closeRegistrationForStudents() {
		Database.registrationIsOpen = false;
	}
	/**
	 * Returns hashcode of an Object.
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	/**
	 * Method for checking objects for identity.
	 * @return True, if Student equals to object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof ORManager))
			return false;
		return true;
	}
	/**
	 * Converts information about an object to a string.
	 * @return Information about an object.
	 */
	@Override
	public String toString() {
		return "ORManager []";
	}	
}
