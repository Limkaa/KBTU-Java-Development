package problemFour;

import java.util.Date;

public class Employee extends Person implements Comparable<Object>, Cloneable {
	private double salary;
	private Date hireDate;
	private String insuranceNumber;
	
	public Employee () {}
	public Employee (String name, double salary, Date hireDate, String insuranceNumber) {
		super(name);
		this.salary = salary;
		this.hireDate = hireDate;
		this.insuranceNumber = insuranceNumber;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Date getHireDate() {
		return hireDate;
	}
	
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	
	public String toString() {
		return "Employee's name is " + super.getName() + 
				". Salary: " + this.getSalary() +
				". Started work: " + this.getHireDate() + 
				". Insurance number: " + this.getInsuranceNumber();
	}
	
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Employee other = (Employee)o; 
		return (salary == other.salary) && (hireDate.equals(hireDate)) && insuranceNumber.equals(other.insuranceNumber);
	}
	
	public int compareTo(Object object) {
		Employee other = (Employee) object;
        if (this.salary > other.salary)
            return 1;
        else if (this.salary < other.salary)
            return -1;
		return 0;
    }
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
