/**
 * @author test
 *
 */
public class PGNReader {
	private static final char CAPTURE = 'x';

	String [][] board;
	
	final static char BLACK = 'B';
	final static char WHITE = 'w';
	
	final static char PAWNS = 'P';	
	final static char KING = 'K';
	final static char QUEEN = 'Q';
	final static char ROOK = 'R';
	final static char BISHOP = 'B';
	final static char KNIGHT = 'K';
	
	public PGNReader() {
		board = new String[8][8];
	}
	
	public void move(String moveNotation){
		
	}
	
	private void movePawn(String notation, char color) {
		boolean  capture = notation.indexOf(CAPTURE) != -1;
	}
	
}
