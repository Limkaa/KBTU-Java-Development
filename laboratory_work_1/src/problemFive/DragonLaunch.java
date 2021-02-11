package problemFive;
import java.util.Vector;

public class DragonLaunch {
	Vector<Person> students = new Vector<Person>();
	
	public DragonLaunch() {
		
	}
	public void kidnap(Person person) {
		this.students.add(person);
	}
	public String willDragonEatOrNot() {
		int previousFreeBoys = 0;
		for (Person i: students) {
				if (i.gender == Gender.BOY) {
					previousFreeBoys ++;
				} else if (i.gender == Gender.GIRL && previousFreeBoys > 0) {
					previousFreeBoys --;
				} else {
					return "Yes, dragon will eat";
				}
		} 
		if (previousFreeBoys > 0)
			return "Yes, dragon will eat";
		else
			return "No, dragon won't eat";
			
	}
}
