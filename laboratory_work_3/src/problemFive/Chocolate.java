package problemFive;

public class Chocolate implements Comparable<Object> {
	private double weight;
	private String name;
	
	public Chocolate() {}
	public Chocolate(double weight, String name) {
		this.weight = weight;
		this.name = name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
	
	public int compareTo(Object object) {
		Chocolate other = (Chocolate) object;
        if (this.getWeight() > other.getWeight())
            return 1;
        else if (this.getWeight() < other.getWeight())
            return -1;
        return 0;
    }
	
	public String toString() {
		return name + ' ' + weight;
	}
	
}
