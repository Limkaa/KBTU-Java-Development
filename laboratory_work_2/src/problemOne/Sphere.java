package problemOne;

public class Sphere extends Shape {
	double radius;
	final double PI = 3.14;
	
	public Sphere(double radius) {
		this.radius = radius;
	}
	
	public double volume() {
		return (4/3) * PI * Math.pow(radius, 3);
	}
	
	public double surfaceArea() {
		return 4 * PI * radius * radius; 
	}
	
	public String toString() {
		return "Sphere:\nVolume = " + volume() + "\nArea = " + surfaceArea() + "\n"; 
	}
}