package problemThree;

public class Main {
	public static void main(String args[]) {
		
		Pawn pawn = new Pawn(new Position("B3"));
		System.out.println(pawn.isLegalMove(new Position("A5")));
		
		System.out.println(Piece.isLegalMove(new Position("E1"), new Position("E8")));
		System.out.println(Pawn.isLegalMove(new Position("A2"), new Position("B3")));
		System.out.println(Knight.isLegalMove(new Position("A2"), new Position("C1")));
		System.out.println(Bishop.isLegalMove(new Position("B3"), new Position("D5")));
		System.out.println(Rook.isLegalMove(new Position("B3"), new Position("C2")));
		System.out.println(Queen.isLegalMove(new Position("B3"), new Position("C2")));
		System.out.println(King.isLegalMove(new Position("B3"), new Position("A2")));
	}
}
