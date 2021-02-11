package problemThree;

public class Bishop extends Piece {
	
	public Bishop(Position pos1) {
		this.pos1 = pos1;
	}
	
	public boolean isLegalMove(Position pos2) {
		return isLegalMove(this.pos1, pos2);
	}
	
	public static boolean isLegalMove(Position pos1, Position pos2) {
		//index1 - letter
		//index2 - number
		if (!Piece.isLegalMove(pos1, pos2))
			return false;
		else if (Math.abs(pos1.index1 - pos2.index1) == Math.abs(pos1.index2 - pos2.index2))
			return true;
		return false;
		
	}
}
