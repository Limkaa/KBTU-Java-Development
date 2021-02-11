package problemOne;

public class Cylinder extends Shape{
	double radius;
	double height;
	final double PI = 3.14;
	
	public Cylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}
	
	public double volume() {
		return PI * radius * radius * height;
	}
	
	public double surfaceArea() {
		return 2* PI * radius * radius + 2 * PI * radius * height; 
	}
	
	public String toString() {
		return "Cylinder:\nVolume = " + volume() + "\nArea = " + surfaceArea() + "\n"; 
	}
}
