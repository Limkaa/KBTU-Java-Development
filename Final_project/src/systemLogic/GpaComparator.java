package systemLogic;

import java.util.Comparator;

public class GpaComparator implements Comparator<Student>{
	
	// Comparison of students by GPA
	public int compare (Student p1, Student p2) {
		if (p1.getOverallGpa() < p2.getOverallGpa())
			return 1;
		else if (p1.getOverallGpa() > p2.getOverallGpa())
			return -1;
		return p1.compareTo(p2);
	}
} 


