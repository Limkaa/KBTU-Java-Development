package problemOne;

public class Car implements Moveable {
	private String brand;

	public void move() {
		System.out.println("Car is moving");
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String toString() {
		return "It is a car. Brand:" + getBrand();
	}
}
