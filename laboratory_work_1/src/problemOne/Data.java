package problemOne;

public class Data {
	
	private double numbersSum = 0;
	private double maxNumber = -10000;
	private int numCount = 0;
	
	public Data() {
		
	}
	public void addData (int num) {
		numbersSum += num;
		if (num > maxNumber) {
			maxNumber = num;
		}
		numCount++;	
	}
	public double getAverageValue () {
		if (numCount == 0) {
			return 0;
		}
		return numbersSum/numCount;
	}
	public double getMaxValue () {
		return maxNumber;
	}
}
