package problemFour;

public abstract class Person {
	private String name;
	
	public Person() {}
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "Person's name is " + getName();
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Person person = (Person)o;
		return this.name.equals(person.name);
	}
	
}
