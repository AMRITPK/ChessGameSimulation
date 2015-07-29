
public class PGNReader {
	String[][] board;

	final static char BLACK = 'b';
	final static char WHITE = 'w';

	private static final char CAPTURE = 'x';


	final static char PAWN = 'P';	
	final static char KING = 'K';
	final static char QUEEN = 'Q';
	final static char ROOK = 'R';
	final static char BISHOP = 'B';
	final static char KNIGHT = 'K';


	public PGNReader() {
		board = new String[8][8];
	}

	public void move(String moveNotation) {
		String[] s = moveNotation.split(" ");
		executeMove(s[0], WHITE);
		executeMove(s[1], BLACK);
	}

	private void executeMove(String pos, char color) {

		String FinalPos = pos.substring(1, pos.length() - 1);

		switch (pos.charAt(0)) {
		case 'B':
			moveBishop(FinalPos, color);
			break;
		case 'N':
			moveKnight(FinalPos, color);
			break;
		case 'K':
			moveKing(FinalPos, color);
			break;
		case 'Q':
			moveQueen(FinalPos, color);
			break;
		case 'R':
			moveRook(FinalPos, color);
			break;
		default:
			moveQueen(FinalPos, color);
		}
	}

	private void moveKing(String finalPos, char color) {
		// TODO Auto-generated method stub
		
	}

	private void moveRook(String finalPos, char color) {
		// TODO Auto-generated method stub

	}

	private void moveQueen(String finalPos, char color) {
		// TODO Auto-generated method stub

	}

	private void moveKnight(String finalPos, char color) {
		int knightXPositions[] ={1,1,2,-1,-1,-1,2,-2};
		int knightYPositions[]={2,-2,1,1,2,-2,-1,-1};
		
		int strlen = finalPos.length();
		int xCoord = finalPos.charAt(strlen-1) - '1';
		int yCoord = finalPos.charAt(strlen-1) - 'a';
		
		if(finalPos.charAt(0)!='x'){
			
		}
		else {
			
		}
	}

	private void moveBishop(String finalPos, char color) {
		// TODO Auto-generated method stub

	}

	
	private void movePawn(String notation, char color) {
		boolean  capture = notation.indexOf(CAPTURE) != -1;
	}
	
}
