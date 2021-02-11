package problemFive;

public class Main {
	public static void main(String[] args) {
		DragonLaunch dragon = new DragonLaunch();
		dragon.kidnap(new Person(Gender.BOY));
		dragon.kidnap(new Person(Gender.BOY));
		dragon.kidnap(new Person(Gender.GIRL));
		dragon.kidnap(new Person(Gender.BOY));
		dragon.kidnap(new Person(Gender.GIRL));
		dragon.kidnap(new Person(Gender.GIRL));
		System.out.print(dragon.willDragonEatOrNot());
		
		
	}
}
