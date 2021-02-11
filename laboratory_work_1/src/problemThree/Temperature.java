package problemThree;

public class Temperature {
	private char scale;
	private double tempValue;
	
	Temperature() {
		scale = 'C';
		tempValue = 0;
	}
	Temperature(double tempValue) {
		this.tempValue = tempValue;
	}
	Temperature(char scale) {
		this.scale = scale;
	}
	Temperature(double tempValue, char scale) {
		this.tempValue = tempValue;
		this.scale = scale;
	}
	public double celciusDegree() {
		if (scale == 'F') {
			return (5*(tempValue-32)/9);
		} else {
			return tempValue;
		}
	}
	public double fahrenheitDegree() {
		if (scale == 'C') {
			return (9*(tempValue/5)+32);
		} else {
			return tempValue;
		}
	}
	public void setTempValue(double tempValue) {
		this.tempValue = tempValue;
	}
	public void setScale(char scale) {
		this.scale = scale;
	}
	public void setTempScaleValue(double tempValue, char scale) {
		setTempValue(tempValue);
		setScale(scale);
	}
	public char getScale() {
		return scale;
	}
	public String toString() {
		return "Temperature: " + tempValue + "Scale:" + scale;
	}
	
}
