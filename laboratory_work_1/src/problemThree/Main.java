package problemThree;

public class Main {
	public static void main(String[] args) {
		Temperature temp = new Temperature(78, 'F');
		
		System.out.println(temp.fahrenheitDegree());
		System.out.println(temp.celciusDegree());
		
		temp.setTempScaleValue(32, 'C');
		System.out.println(temp.celciusDegree());
		System.out.println(temp.fahrenheitDegree());
		
			
	}
}
