package problemFour;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		ArrayList<Employee> workersOne = new ArrayList<Employee>();
		TreeSet<Employee> workersTwo = new TreeSet<Employee>();
		
		Employee w1 = new Employee("Alex", 100, new Date(1200000), "ID1");
		Employee w2 = new Employee("Mark", 500, new Date(100000), "ID2");
		Employee w3 = new Employee("Rob", 300, new Date(30000000), "ID3");
		Manager w4 = new Manager("Kate", 1000, new Date(2500000), "ID4");
		w4.addBonus(500);
		Manager w5 = new Manager("Dave", 1000, new Date(2500000), "ID4");
		w5.addBonus(2000);
		
		//-------------------------------------------
		// Сортировка с использованием компараторов
		workersOne.add(w1);
		workersOne.add(w2);
		workersOne.add(w3);
		workersOne.add(w4);
		workersOne.add(w5);
		
		NameComparator myNameComparator = new NameComparator();
		workersOne.sort(myNameComparator);
		
		for (Person w: workersOne) {
            System.out.println(w);
        }
		
		System.out.println();
		HireDateComparator myDateComparator = new HireDateComparator();
		workersOne.sort(myDateComparator);
		
		for (Person w: workersOne) {
            System.out.println(w);
        }
		
		//-------------------------------------------
		// Сортировка по зарплате
		System.out.println();
		workersTwo.add(w1);
		workersTwo.add(w2);
		workersTwo.add(w3);
		workersTwo.add(w4);
		workersTwo.add(w5);
		
		for (Person w: workersTwo) {
            System.out.println(w);
        }
		
	}
}
