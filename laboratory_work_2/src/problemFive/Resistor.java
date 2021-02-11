package problemFive;

public class Resistor extends Circuit {
	private double resistance;
	private double pottentialDifference;
	
	public Resistor(double resistance) {
		this.resistance = resistance;
	}
	
	public double getResistance() {
		return this.resistance;
	}
	
	public void applyPottentialDiff(double pottentialDifference) {
		this.pottentialDifference = pottentialDifference;
	}
	
	public double getPotentialDiff() {
		return this.pottentialDifference;
	}
}
