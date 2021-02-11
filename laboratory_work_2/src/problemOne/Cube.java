package problemOne;

public class Cube extends Shape{
	double side;
	
	public Cube(double side) {
		this.side = side;
	}
	
	public double volume() {
		return Math.pow(side, 3);
	}
	
	public double surfaceArea() {
		return 6 * side * side; 
	}
	
	public String toString() {
		return "Cube:\nVolume = " + volume() + "\nArea = " + surfaceArea() + "\n"; 
	}
}
