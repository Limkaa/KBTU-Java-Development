package problemFour;

public class Course {
	private String name;
	private String description;
	private int credits;
	Course prerequisite;
	
	public Course (String name, String description, int credits) {
		this.name = name;
		this.description = description;
		this.credits = credits;
	}
	
	public Course (String name, String description, int credits, Course prerequisite) {
		this(name, description, credits);
		this.prerequisite = prerequisite;
	}
	
	public String toString() {
		return "Welcome to the grade book!\nSelected course: " + name +
				"\nDescription: " + description + "\nCredits: " + credits +
				"\nPrerequisite: " + prerequisite.name + "\n\n";
		
	}
}
