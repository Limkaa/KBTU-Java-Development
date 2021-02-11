package problemFour;

import java.util.Date;
import java.util.Vector;

public class Manager extends Employee {
	private Vector<Employee> team;
	private double bonus;
	
	public Manager () {}
	public Manager (String name, double salary, Date hireDate, String insuranceNumber) {
		super(name, salary, hireDate, insuranceNumber);
	}
	
	public void addBonus(double bonus) {
		this.bonus += bonus; 
	}
	
	public double getBonus() {
		return bonus;
	}
	
	public void addEmployeeToTeam(Employee worker) {
		team.add(worker);
	}
	
	public void removeEmployeeFromTeam(Employee worker) {
		team.remove(worker);
	}
	
	public String toString() {
		return super.toString() + ". Bonus: " + this.getBonus();
	}
	
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Manager other = (Manager)o; 
		return (bonus == other.bonus);
	}
	
	@Override
	public int compareTo(Object object) {
		Employee other = (Employee) object;
        if (this.getSalary() > other.getSalary())
            return 1;
        else if (this.getSalary() < other.getSalary())
            return -1;
        else {
        	Manager manager = (Manager) object;
        	if (this.getBonus() > manager.getBonus())
        		return 1;
        	else if (this.getBonus() < manager.getBonus())
        		return -1;
        }
		return 0;
    }
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
