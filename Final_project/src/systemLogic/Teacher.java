package systemLogic;

import java.util.Vector;
/**
 *Teacher represents Teacher's intranet account.
 *
 */
public class Teacher extends Employee implements MessageSendable, OrderSendable {
	
	private static final long serialVersionUID = 13L;
	/** Teacher's role */
	private Role role;
	
	public Teacher() {}
	
	/**
	 * Constructs and initialize Teacher with given name, surname, department and role.
	 * @param name Teacher's name.
	 * @param surname Teacher's surname.
	 * @param department Teacher's department.
	 * @param role Teacher's role.
	 */
	public Teacher(String name, String surname, Departments department, Role role) {
		super(name, surname, department);
		this.role = role;
	}
	

	/**
	 * Gets role of the Teacher.
	 * @return This Teacher's role.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Change the role of this Teacher.
	 * @param role This Teacher's new role.
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Gets courses taught by the Teacher.
	 * @return Vector containing the courses taught by the Teacher.
	 */
	public Vector<Course> getTeachingCourses() {
		Vector<Course> courses = new Vector<Course>();
		for (Course course: Database.courses)
			if (course.getTeacher().equals(this))
				courses.add(course);
		return courses;
	}
	
	/**
	 * Lets us know if a teacher is teaching a specific course
	 * @param course Course to be checked to see if the teacher is teaching it.
	 * @return True if the teacher is teaching this course, otherwise false.
	 */
	public boolean isTeachingCourse(Course course) {
		if (course != null)
			if (course.getTeacher().equals(this))
				return true;
		return false;
	}

	/**
	 * Adds file to the course.
	 * @param course Course where to add a file.
	 * @param file File to add.
	 * @return True if the operation was successful, otherwise false.
	 */
	public boolean addCourseFile(Course course, CourseFile file) {
		if (isTeachingCourse(course)) {
			course.addCourseFile(file);
			return true;
		}
		return false;
	}

	/**
	 * Removes file from the course.
	 * @param course Course where to delete a file.
	 * @param file File to remove.
	 * @return True if the operation was successful, otherwise false.
	 */
	public boolean deleteCourseFile(Course course, CourseFile file) {
		if (isTeachingCourse(course))
			if (course.deleteCourseFile(file))
				return true;
		return false;
	}

	/**
	 * Gets all course files.
	 * @param course The course from which to get the files.
	 * @return Vector, which contains course files.
	 * 		   If course does not have any file, it returns null value.
	 */
	public Vector<CourseFile> getCourseFiles(Course course) {
		if (isTeachingCourse(course))	
			return course.getCourseFiles();
		return null;
	}

	/**
	 * Gets a list of students enrolled in this course.
	 * @param course Course to choose from.
	 * @return Vector, which contains Students of this course.
	 * 	  	   If no one is enrolled in the course, it returns null value.
	 */
	public Vector<Student> getCourseStudents(Course course) {
		if (isTeachingCourse(course))	
			return course.getStudents();
		return null;
	}

	/**
	 * Assigns a grade to a specific student in a specific discipline.
	 * @param course The course, which put an assessment.
	 * @param student A Student who needs to put a mark
	 * @param mark Mark
	 * @return True if the operation was successful, otherwise false.
	 */
	public boolean putMark(Course course, Student student, Mark mark) {
		if (isTeachingCourse(course)) 
			if (course.getStudents().contains(student)) {
				if (mark.getScore() <= 100)
					student.getCourseMark(course).setScore(mark.getScore());;
				return true;
			}
		return false;
	}
	/**
	 * Returns hashcode of an Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	/**
	 * Method for checking objects for identity
	 * @return True, if Student equals to object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Teacher))
			return false;
		Teacher other = (Teacher) obj;
		if (role != other.role)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Teacher [role=" + role + "]";
	}	
}
