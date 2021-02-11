package systemLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
/**
 * Student represents Student's account.
 *
 */
public class Student extends User {

	private static final long serialVersionUID = 12L;
	/** Student's speciality */
	private Speciality speciality;
	/** Student's study year */
	private int studyYear = 1;
	/** Student's marks */
	private Map<Course, Mark> courses = new HashMap<Course, Mark>();
	public Student() {}
	/**
	 * Construct and initialize Student with given name, surname, speciality and study year.
	 * @param name Student name.
	 * @param surname Student surname.
	 * @param speciality Student speciality.
	 * @param studyYear Student study year.
	 */
	public Student(String name, String surname, Speciality speciality, int studyYear) {
		super(name, surname);
		this.speciality = speciality;
		this.studyYear = studyYear;
	}

	/**
	 * Gets Student speciality.
	 * @return This Student speciality.
	 */
	public Speciality getSpeciality() {
		return speciality;
	}

	/**
	 * Gets Student study year.
	 * @return This Student study year.
	 */
	public int getStudyYear() {
		return studyYear;
	}

	/**
	 * Increasing Student study year.
	 */
	public void increaseStudyYear() {
		this.studyYear++;
	}

	/**
	 * Gets Student transcript.
	 * @return This Student transcript.
	 */
	public Map<Course, Mark> getTranscript() {
		return courses;
	}
	
	/**
	 * Gets Student mark for discipline.
	 * @param course Course, the grade of which you want to know.
	 * @return Mark value. If student not enrolled in this course return null value.
	 */
	public Mark getCourseMark(Course course) {
		for (Map.Entry<Course, Mark> courseMark: courses.entrySet())
			if (courseMark.getKey().equals(course))
				return courseMark.getValue();
		return null;
	}
	
	/**
	 * Gets all student courses.
	 * @return Vector of courses.
	 */
	public Vector<Course> getCourses() {
		Vector<Course> coursesList = new Vector<Course>(); 
		for (Course course: courses.keySet())
			coursesList.add(course);
		return coursesList;
	}
	
	/**
	 * Gets all courses available for registration.
	 * @return Vector of courses. Returns null if there are no courses available for registration.
	 */
	public Vector<Course> getCoursesForRegistration() {
		Vector<Course> allowedCourses = new Vector<Course>();
		if (Database.registrationIsOpen) {
			for (Course course: Database.courses) 
				if (course.getSpeciality().contains(this.getSpeciality()) &&
						course.getStudyYear() == this.getStudyYear())
					allowedCourses.add(course);
			return allowedCourses;
		}
		return null;
	}

	/**
	 * Register Student for a course.
	 * @param course Course for registration.
	 * @return True if registration was successful, otherwise false.
	 */
	public boolean registerForCourse(Course course) {
		if (!isHavingCourse(course) && getCoursesForRegistration().contains(course) && course != null)
			if (course.getStudents().size() < course.getStudentsLimit()) {
				courses.put(course, new Mark());
				course.addStudent(this);
				return true;
			}
		return false;	
	}

	/**
	 * Unregister Student from course.
	 * @param course Course for unregistration.
	 * @return True if unregistration was successful, otherwise false.
	 */
	public boolean unregisterFromCourse(Course course) {
		if (isHavingCourse(course) && getCoursesForRegistration().contains(course) && course != null) {
			courses.remove(course);
			course.deleteStudent(this);
			return true;
		}
		return false;	
	}

	/**
	 * Checks if a student is enrolled in a specific course
	 * @param course Course to check.
	 * @return True if the student is studying this course, otherwise false.
	 */
	public boolean isHavingCourse(Course course) {
		return courses.containsKey(course);	
	}

	/**
	 * Gets all course files.
	 * @param course The course from which to get the files.
	 * @return Vector, which contains course files.
	 * 		   If course does not have any file, it returns null value.
	 */
	public Vector<CourseFile> getCourseFiles(Course course) {
		if (isHavingCourse(course))
			return course.getCourseFiles();
		return null;
	}

	/**
	 * Gets Student's overall GPA.
	 * @return returns GPA if it don't equal zero, otherwise return 0.
	 */
	public double getOverallGpa() {
		double gpa = 0;
		for (Map.Entry<Course, Mark> course: courses.entrySet())
			gpa += course.getValue().getDigitMark();
		if (gpa != 0) 
			return gpa/courses.size();
		return 0;
	}

	/**
	 * Gets overall Student credits
	 * @return amount of credits
	 */
	public int getOverallCredits() {
		int credits = 0;
		for (Map.Entry<Course, Mark> course: courses.entrySet())
			credits += course.getKey().getCredits();
		return credits;

	}
	/**
	 * Returns hashcode of an Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + studyYear;
		return result;
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
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (speciality != other.speciality)
			return false;
		if (studyYear != other.studyYear)
			return false;
		return true;
	}
	/**
	 * Converts information about an object to a string.
	 * @return Information about an object.
	 */
	@Override
	public String toString() {
		return "Student [speciality=" + speciality + ", studyYear=" + studyYear + ", courses=" + courses + "]";
	}



}
