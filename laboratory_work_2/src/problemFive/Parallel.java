package problemFive;

public class Parallel extends Circuit {
	private Circuit resistorOne;
	private Circuit resistorTwo;

	public Parallel (Circuit resistorOne, Circuit resistorTwo) {
		this.resistorOne = resistorOne;
		this.resistorTwo = resistorTwo;
	}
	
	public double getResistance() {
		return Math.pow((1/resistorOne.getResistance() + 1/resistorTwo.getResistance()), -1);
	}
	
	public void applyPottentialDiff(double pottentialDifference) {
		resistorOne.applyPottentialDiff(pottentialDifference);
		resistorTwo.applyPottentialDiff(pottentialDifference);
	}
	
	public double getPotentialDiff() {
		return resistorOne.getPotentialDiff();
	}
}
