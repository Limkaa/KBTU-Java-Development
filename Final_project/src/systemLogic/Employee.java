package systemLogic;

public abstract class Employee extends User {

	private static final long serialVersionUID = 7L;
	
	private Departments department;
	
	public Employee() {}
	public Employee(String name, String surname, Departments department) {
		super(name, surname);
		this.department = department;
	}
	
	// Getting the employee's department
	public Departments getDepartment() {
		return department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (department != other.department)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [department=" + department + "]";
	}
	
}
