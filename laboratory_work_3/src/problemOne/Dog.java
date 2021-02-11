package problemOne;

public class Dog extends Animal implements Moveable, Propagable {
	private String species;
	
	public void move() {
		System.out.println("Dog is running");
	}
	
	public String getSpecies() {
		return species;
	}
	
	public String toString() {
		return "This is a dog. Family:" + getFamily() + "Species: " + getSpecies(); 
	}
}
