package problemThree;

public class Position {
	//index1 - letter
	//index2 - number
	int index1;
	int index2;
	
	public Position(String position) {
		
		char a = position.charAt(0);
		int numberEquivalent = 0;
		
		if (a == 'A')
			numberEquivalent = 1;
		else if (a == 'B')
			numberEquivalent = 2;
		else if (a == 'C')
			numberEquivalent = 3;
		else if (a == 'D')
			numberEquivalent = 4;
		else if (a == 'E')
			numberEquivalent = 5;
		else if (a == 'F')
			numberEquivalent = 6;
		else if (a == 'G')
			numberEquivalent = 7;
		else if (a == 'H')
			numberEquivalent = 8;
		
		this.index1 = numberEquivalent;
		this.index2 = Character.getNumericValue(position.charAt(1));
	}
	
	public boolean isOutOfBorders() {
		if (index1 < 1 || index1 > 8)
			return true;
		else if (index2 < 1 || index2 > 8)
			return true;
		return false;
	}
	
}
