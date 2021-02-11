package problemFour;

public class GradeBookTest {
	public static void main(String[] args) {
		
		Course prerequisite = new Course("PP1", "Programming principles", 4);
		Course currentCourse = new Course("OOP", "Basics of object-oriented programming", 3, prerequisite);
		
		GradeBook gradebook = new GradeBook(currentCourse);
		
		gradebook.addStudent("Alex");
		gradebook.addStudent("Mark");
		gradebook.addStudent("Katy");
		gradebook.addStudent("Harry");
		gradebook.addStudent("Nick");
		gradebook.addStudent("Tom");
		gradebook.addStudent("Hanna");
		gradebook.addStudent("Mary");
		gradebook.addStudent("Clark");
		gradebook.addStudent("Rick");
		
		System.out.print(gradebook.displayMessage());
		gradebook.setGradeValues();
		System.out.print(gradebook.displayGradeReport());
	}
}
