package problemOne;
import java.util.Scanner;

public class Analyzer {
	
	public Analyzer () {	
	}
	
	public void start() {
		Data data = new Data();
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.print("Enter number (Q to quit): ");
			String line = input.nextLine();
			
			if (line.equals("Q")) {
				System.out.println("Average = "+data.getAverageValue());
				System.out.println("Maximum = "+data.getMaxValue());
				break;
			} 
			else {
				data.addData(Integer.parseInt(line));
			}
		}
		input.close();
	}
	
}
