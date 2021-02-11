package problemFour;
import java.util.Scanner;
import java.util.Vector;
import studentPackage.Student;

public class GradeBook {
	Course course; 
	Vector<Student> students = new Vector<Student>();
	
	public GradeBook() {
		
	}
	public GradeBook(Course course) {
		this.course = course;
	}
	public void addStudent(String name) {
		Student student = new Student(name);
		this.students.add(student);
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setGradeValues() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please, input grades for students:\n");
		for (Student i: students) {
			System.out.print(i.getName()+": ");
			double grade = input.nextInt();
			i.setGrade(grade);
		} 
		input.close();
	}
	public double determineClassAverage() {
		double sum = 0;
		for (Student i: students) {
			sum += i.getGrade();
		} 
		return sum/(students.size());
	}
	public String determineClassMaximum() {
		double maxi = -1;
		String result = "";
		for (Student i: students) {
			if (i.getGrade()>maxi) {
				maxi = i.getGrade();
				result = i.toString();
			} 
		} 
		return result;
	}
	public String determineClassMinimum() {
		double mini = 101;
		String result = "";
		for (Student i: students) {
			if (i.getGrade()<mini) {
				mini = i.getGrade();
				result = i.toString();
			} 
		} 
		return result;
	}
	public String outputBarChart() {
		String result = "";
		for (int i = 0; i<=10; i++) {
			if (i==10) {
				result += "  100: ";
			} else {
				result += i+"0-"+i+"9: ";
			}
			for (Student j: students) {
				if ((i*10)<=j.getGrade() && j.getGrade()<((i+1)*10)) {
					result += "* ";
				}
			}
			result += "\n";
		}
		return result;
	}
	public String toString() {
		String result = "\nAverage grade: " + String.format("%.2f",determineClassAverage())
				+ "\nMaximum grade: " + determineClassMaximum() + "\nMinimum grade: " 
				+ determineClassMinimum() + "\n\nGrade distribution:\n" + outputBarChart();
		return result;
	}
	public String displayMessage() {
		return course.toString();
	}
	public String displayGradeReport() {
		return toString();
	}
}
