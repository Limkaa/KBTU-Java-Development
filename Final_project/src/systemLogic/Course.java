package systemLogic;

import java.io.Serializable;
import java.util.Vector;
/**
 * Represents a Course info
 */
public class Course implements Comparable<Course>, Serializable {

	private static final long serialVersionUID = 4L;
	/**
	 * Id of the Course.
	 */
	private int id;
	/**
	 * Title of the Course.
	 */
	private String title;
	/**
	 * Cost in credits.
	 */
	private int credits;
	/**
	 * Course Teacher.
	 */
	private Teacher teacher;
	/**
	 * Minimum study year to register for a course.
	 */
	private int studyYear;
	/**
	 * Registration limit for the Course.
	 */
	private int studentsLimit;
	/**
	 * Vector of specialties available for registration.
	 */
	private Vector<Speciality> speciality = new Vector<Speciality>();
	/**
	 * Vector of Files of this Course.
	 */
	private Vector<CourseFile> courseFiles = new Vector<CourseFile>();
	/**
	 * Vector of students registered for this Course.
	 */
	private Vector<Student> students = new Vector<Student>();
	
	{
		Database.counterCourseId++;
		id = Database.counterCourseId;
	}
	
	public Course() {}
	/** 
	 * Creates and initializes Course object with given parameters.
	 * @param title Course title.
	 * @param credits Cost in credits.
	 * @param teacher Course Teacher.
	 * @param studyYear Minimum study year needed to register for this Course.
	 * @param studentsLimit Maximum number of registered Students for this Course.
	 * @param speciality Specialties available for registration.
	 */
	public Course(String title, int credits, Teacher teacher, int studyYear, int studentsLimit, Vector<Speciality> speciality) {
		this.title = title;
		this.credits = credits;
		this.teacher = teacher;
		this.studentsLimit = studentsLimit;
		this.studyYear = studyYear;
		this.speciality = speciality;
	}
	
	/**
	 * Gets Course Id.
	 * @return This Course ID.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets Course title.
	 * @return Title of this Course.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets Course credits
	 * @return This Course cost in credits.
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Gets Course student limit.
	 * @return This Course student limit. 
	 */
	public int getStudentsLimit() {
		return studentsLimit;
	}
	
	/**
	 * Sets new student limit for the Course.
	 * @param studentsLimit new students limit.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean setStudentsLimit(int studentsLimit) {
		if (getStudents().size() < studentsLimit) {
			this.studentsLimit = studentsLimit;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets Course Teacher.
	 * @return This Course Teacher.
	 */
	public Teacher getTeacher() {
		return teacher;
	}
	
	/**
	 * Gets Course minimum study year to register.
	 * @return This Course minimum study year.
	 */
	public int getStudyYear() {
		return studyYear;
	}

	/**
	 * Gets all speciailties available for registration.
	 * @return Vector of available specialities.
	 */
	public Vector<Speciality> getSpeciality() {
		return speciality;
	}
	
	/**
	 * Gets all speciailties not available for registration.
	 * @return Vector of not available specialities.
	 */
	public Vector<Speciality> getNotIncludedSpeciality() {
		Vector<Speciality> specialities = new Vector<Speciality>();
		for (Speciality specialityTitle: Speciality.values())
			if (!speciality.contains(specialityTitle))
				specialities.add(specialityTitle);
		return specialities;
	}
	
	/**
	 * Adds speciality to available for registration specialities.
	 * @param speciality New speciality.
	 */
	public void addSpeciality(Speciality speciality) {
		this.speciality.add(speciality);
	}
	
	/**
	 * Adds Course File to this Course.
	 * @param file CourseFile to add.
	 */
	public void addCourseFile(CourseFile file) {
		courseFiles.add(file);
	}
	
	/**
	 * Removes Course File to this Course.
	 * @param file CourseFile to be removed.
	 * @return boolean true if file remove, else false
	 */
	public boolean deleteCourseFile(CourseFile file) {
		if (courseFiles.contains(file)) {
			courseFiles.remove(file);
			return true;
		}
		return false;
	}
	
	/**
	 * Get Course Files.
	 * @return Vector of All course Files of this Course.
	 */
	public Vector<CourseFile> getCourseFiles() {
		return courseFiles;
	}
	
	/**
	 * Search and gets Course File by ID.
	 * @param courseFileId ID of Course File.
	 * @return CourseFile if exists, otherwise null value.
	 */
	public CourseFile getCourseFile(int courseFileId) {
		for (CourseFile file: getCourseFiles())
			if (file.getId() == courseFileId)
				return file;
		return null;
	}
	
	/**
	 * Adds Student to this course.
	 * @param student Student.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean addStudent(Student student) {
		if (!students.contains(student)) {
			students.add(student);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes Student from this course.
	 * @param student Student.
	 * @return True if operation was successful, otherwise false.
	 */
	public boolean deleteStudent(Student student) {
		if (students.contains(student)) {
			students.remove(student);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets all Course students.
	 * @return Vector of all registered Students for this Course.
	 */
	public Vector<Student> getStudents() {
		return students;
	}
	

	public int compareTo(Course course) {
		if (title.compareTo(course.getTitle())==1)
			return 1;
		return -1;
	}
	/**
	 * Returns hashcode of an Object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + id;
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + studentsLimit;
		result = prime * result + studyYear;
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (!(obj instanceof Course))
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		if (studentsLimit != other.studentsLimit)
			return false;
		if (studyYear != other.studyYear)
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	/**
	 * Converts information about an object to a string.
	 * @return Information about an object.
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", credits=" + credits + ", teacher=" + teacher
				+ ", studyYear=" + studyYear + ", studentsLimit=" + studentsLimit + ", speciality=" + speciality
				+ ", courseFiles=" + courseFiles + ", students=" + students + "]";
	}
	
	
}
