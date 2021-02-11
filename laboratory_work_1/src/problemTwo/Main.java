package problemTwo;

public class Main {
		public static void main(String[] args) {
			
			AlmatyLibrary Dave = new AlmatyLibrary("Dave", "Kings", BookGenres.CLASSIC);
			System.out.println(Dave.getActiveReadersNum());
			System.out.println(Dave.getPersonInfo());
			
			AlmatyLibrary Mary = new AlmatyLibrary("Mary", "Rich father.Poor father");
			System.out.println(Mary.getActiveReadersNum());	
			System.out.println(Mary.getPersonInfo());
			
			Mary.setInfo("Galaxy", BookGenres.FANTASTIC);
			System.out.println(Mary.getPersonInfo());
			
			Dave.setInfo("Fallen warrior");
			System.out.println(Dave.getPersonInfo());
		
		}			
}
