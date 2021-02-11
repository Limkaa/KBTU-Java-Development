package problemThree;

public abstract class Piece {
	
	Position pos1;
	
	public static boolean isLegalMove(Position pos1, Position pos2) {
		if (pos1.equals(pos2))
			return false;
		else if (pos1.isOutOfBorders() || pos2.isOutOfBorders())
			return false;
		return true;
	}
}
