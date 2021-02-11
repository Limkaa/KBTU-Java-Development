package problemOne;
import java.util.Vector;

public class Main {
	public static void main(String args[]) {
		Vector<Shape> shapes = new Vector<Shape>();
		shapes.add(new Cylinder(5, 3));
		shapes.add(new Sphere(7));
		shapes.add(new Cube(10));
		for (int i = 0; i < shapes.size(); i++) {
			System.out.println(shapes.get(i).toString());
		}
	}
}
