package problemFive;

public class Person {
	Gender gender;
	
	public Person (){
		
	}
	
	public Person (Gender gender) {
		this.gender = gender;
	}
	
	public String toString() {
		if (gender == Gender.BOY) {
			return "boy";
		} else {
			return "girl";
		}
	}
}
