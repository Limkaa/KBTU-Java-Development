package problemFive;

public class Main {
	public static void main(String[] args) {
		Chocolate[] chocolates = new Chocolate[5];
		chocolates[0] = new Chocolate(150, "Twix");
		chocolates[1] = new Chocolate(100, "Snikers");
		chocolates[2] = new Chocolate(200, "Mars");
		chocolates[3] = new Chocolate(70, "Albeni");
		chocolates[4] = new Chocolate(40, "Tofifi");
		
		Sort.bubbleSort(chocolates);
		//Sort.quickSort(chocolates);
		
		for (Chocolate choco: chocolates) {
			System.out.println(choco);
		}
		
		
		System.out.println();
		
		Time[] times = new Time[3];
		times[0] = new Time(10, 5, 32);
		times[1] = new Time(15, 48, 10);
		times[2] = new Time(10, 32, 4);
		
		//Sort.bubbleSort(times);
		Sort.quickSort(times);
		
		for (Time time: times) {
			System.out.println(time);
		}
		
		
	}
}
