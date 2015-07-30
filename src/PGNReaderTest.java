import static org.junit.Assert.*;

import org.junit.Test;

public class PGNReaderTest {

	private static final int BOARDSIZE = 8;

	@Test
	public void amritTest() {
		PGNReader reader = new PGNReader();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveBishop("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveBishop("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveRook("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveRook("a3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveQueen("d3", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();
		for (int i = 0; i < BOARDSIZE; i++) {
			reader.board[1][i] = reader.BLANK;
			reader.board[6][i] = reader.BLANK;
		}

		reader.printBoard();
		reader.moveQueen("ee2", PGNReader.WHITE);
		reader.printBoard();
		reader.initBoard();

	}

	@Test
	public void testMoveKnight() {
		PGNReader reader = new PGNReader();
		reader.move("Nf3 Nc6");
		assertEquals("wN", reader.board[2][5]);
		assertEquals(" ", reader.board[0][6]);

		assertEquals("bN", reader.board[5][2]);
		assertEquals(" ", reader.board[7][1]);

		reader.move("Nc3 Nf6");
		assertEquals("wN", reader.board[2][2]);
		assertEquals(" ", reader.board[0][1]);
		assertEquals("bN", reader.board[5][5]);
		assertEquals(" ", reader.board[7][6]);

	}

	@Test
	public void testMovePawn() {
		PGNReader reader = new PGNReader();
		reader.move("f4 b6");
		assertEquals("bP", reader.board[5][1]);
		assertEquals(" ", reader.board[6][1]);
		assertEquals("wP", reader.board[3][5]);
		assertEquals(" ", reader.board[1][5]);

	}

}
