package problemFive;

public class Series extends Circuit {
	private Circuit resistorOne;
	private Circuit resistorTwo;

	public Series (Circuit resistorOne, Circuit resistorTwo) {
		this.resistorOne = resistorOne;
		this.resistorTwo = resistorTwo;
	}
	
	public double getResistance() {
		return resistorOne.getResistance() + resistorTwo.getResistance();
	}
	
	public void applyPottentialDiff(double pottentialDifference) {
		resistorOne.applyPottentialDiff(pottentialDifference * (resistorOne.getResistance()/getResistance()));
		resistorTwo.applyPottentialDiff(pottentialDifference * (resistorTwo.getResistance()/getResistance()));
	}
	
	public double getPotentialDiff() {
		return resistorOne.getPotentialDiff() + resistorTwo.getPotentialDiff();
	}
}
