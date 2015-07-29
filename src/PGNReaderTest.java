import static org.junit.Assert.*;

import org.junit.Test;

public class PGNReaderTest {
    
	@Test
	public void amritTest() {
		PGNReader reader=new PGNReader();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveBishop("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveBishop("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveRook("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveRook("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveQueen("d3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < reader.BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}
		
		reader.printBoard();
		reader.moveQueen("ee2", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		
	}
	@Test
	public void testMove() {
		
	}
	
}
