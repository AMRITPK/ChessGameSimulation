import java.util.HashMap;
import java.util.Map;

public class PGNReader {

	private static final int BOARDSIZE = 8;

	private static final char CAPTURE = 'x';
	private static final String SEPARATOR = "";

	final static String BLANK = " ";
	final static char BLACK = 'b';
	final static char WHITE = 'w';

	final static char PAWN = 'P';
	final static char KING = 'K';
	final static char QUEEN = 'Q';
	final static char ROOK = 'R';
	final static char BISHOP = 'B';
	final static char KNIGHT = 'N';

	String[][] board;

	public PGNReader() {
		board = new String[BOARDSIZE][BOARDSIZE];
		initBoard();

	}

	/**
	 * Initialize the board
	 */
	void initBoard() {

		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				board[i][j] = BLANK;
				// board[i][j] = i + SEPARATOR + j;
			}
		}

		for (int i = 0; i < BOARDSIZE; i++) {
			board[1][i] = WHITE + SEPARATOR + PAWN;
			board[6][i] = BLACK + SEPARATOR + PAWN;
		}

		board[0][0] = WHITE + SEPARATOR + ROOK;
		board[0][7] = WHITE + SEPARATOR + ROOK;
		board[7][0] = BLACK + SEPARATOR + ROOK;
		board[7][7] = BLACK + SEPARATOR + ROOK;

		board[0][1] = WHITE + SEPARATOR + KNIGHT;
		board[0][6] = WHITE + SEPARATOR + KNIGHT;
		board[7][1] = BLACK + SEPARATOR + KNIGHT;
		board[7][6] = BLACK + SEPARATOR + KNIGHT;

		board[0][2] = WHITE + SEPARATOR + BISHOP;
		board[0][5] = WHITE + SEPARATOR + BISHOP;
		board[7][2] = BLACK + SEPARATOR + BISHOP;
		board[7][5] = BLACK + SEPARATOR + BISHOP;

		board[0][4] = WHITE + SEPARATOR + KING;
		board[7][4] = BLACK + SEPARATOR + KING;

		board[0][3] = WHITE + SEPARATOR + QUEEN;
		board[7][3] = BLACK + SEPARATOR + QUEEN;
	}

	/**
	 * Move a pair of Pieces.
	 * 
	 * @param moveNotation
	 *            (WhiteMove BlackMove)
	 */
	public void move(String moveNotation) {

		String[] s = moveNotation.trim().split("\\s+");

		System.out.println(s[0].trim() + "-" + s[1].trim());

		move(s[0].trim(), WHITE);
		move(s[1].trim(), BLACK);
	}

	/**
	 * Move a single Piece
	 * 
	 * @param pos
	 *            final position in Algebraic notation (eg: Nxg4)
	 * @param color
	 *            color of piece.
	 */
	public void move(String pos, char color) {

		boolean checkMate = pos.contains("++");
		boolean check = pos.contains("+");
		if (checkMate) {
			return;
		}
		if (check) {
			pos = pos.replace("+", "");
		}

		String finalPos = pos.substring(1, pos.length());

		switch (pos.charAt(0)) {
		case 'B':
			moveBishop(finalPos, color);
			break;
		case 'N':
			moveKnight(finalPos, color);
			break;
		case 'K':
			moveKing(finalPos, color);
			break;
		case 'Q':
			moveQueen(finalPos, color);
			break;
		case 'R':
			moveRook(finalPos, color);
			break;
		case 'O':
			if (pos.equals("O-O-O"))
				queenSideCastling(color);
			else if (pos.equals("O-O"))
				kingSideCastling(color);
			break;
		default:
			if (pos.matches("\\d.*"))
				break;
			movePawn(pos, color);
		}
	}

	/**
	 * Move King and Rook in case of Queen-side Castling
	 * 
	 * @param color
	 *            color of piece.
	 */
	void queenSideCastling(char color) {
		if (color == WHITE) {
			board[0][4] = BLANK;
			board[0][2] = WHITE + SEPARATOR + KING;
			board[0][0] = BLANK;
			board[0][3] = WHITE + SEPARATOR + ROOK;
		} else {
			board[7][4] = BLANK;
			board[7][2] = BLACK + SEPARATOR + KING;
			board[7][0] = BLANK;
			board[7][3] = BLACK + SEPARATOR + ROOK;
		}
	}

	/**
	 * Move King and Rook in case of Queen-side Castling
	 * 
	 * @param color
	 *            color of piece.
	 */
	void kingSideCastling(char color) {
		if (color == WHITE) {
			board[0][4] = BLANK;
			board[0][6] = WHITE + SEPARATOR + KING;
			board[0][7] = BLANK;
			board[0][5] = WHITE + SEPARATOR + ROOK;
		} else {
			board[7][4] = BLANK;
			board[7][6] = BLACK + SEPARATOR + KING;
			board[7][7] = BLANK;
			board[7][5] = BLACK + SEPARATOR + ROOK;
		}
	}

	/**
	 * Move King
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void moveKing(String finalPos, char color) {
		int kingYPositions[] = { 1, -1, 0, 0, 1, -1, -1, 1 };
		int kingXPositions[] = { 0, 0, 1, -1, 1, 1, -1, -1 };

		int strlen = finalPos.length();
		int yCoord = finalPos.charAt(strlen - 2) - 'a';
		int xCoord = finalPos.charAt(strlen - 1) - '1';

		int finalX = 0;
		int finalY = 0;

		String piece = color + SEPARATOR + KING;

		for (int i = 0; i < 8; i++) {
			int possibleX = xCoord + kingXPositions[i];
			int possibleY = yCoord + kingYPositions[i];

			if (withinBoard(possibleX) && withinBoard(possibleY)
					&& board[possibleX][possibleY].contains(piece)) {
				finalX = possibleX;
				finalY = possibleY;
				break;
			}
		}
		board[xCoord][yCoord] = piece;
		board[finalX][finalY] = BLANK;

	}

	/**
	 * Move Knight
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void moveKnight(String finalPos, char color) {

		boolean capture = finalPos.indexOf(CAPTURE) != -1;

		if (capture) {
			finalPos = finalPos.replace(Character.toString(CAPTURE), "");
		}

		boolean finalPosGiven = (finalPos.length() == 3);

		int knightXPositions[] = { 1, 2, -2, 2, -1, -2, 1, -1 };
		int knightYPositions[] = { -2, 1, -1, -1, -2, 1, 2, 2 };

		int strlen = finalPos.length();
		int yCoord = finalPos.charAt(strlen - 2) - 'a';
		int xCoord = finalPos.charAt(strlen - 1) - '1';

		int possibleX, possibleY;
		int finalX = 0;
		int finalY = 0;

		for (int i = 0; i < 8; i++) {

			possibleX = xCoord + knightXPositions[i];
			possibleY = yCoord + knightYPositions[i];

			if (withinBoard(possibleX)
					&& withinBoard(possibleY)
					&& board[possibleX][possibleY].contains(color + SEPARATOR
							+ KNIGHT)) {
				finalX = possibleX;
				finalY = possibleY;

				if (finalPosGiven && finalY == (finalPos.charAt(0) - 'a')) {
					break;
				}
			}
		}

		board[xCoord][yCoord] = color + SEPARATOR + KNIGHT;
		board[finalX][finalY] = BLANK;

	}

	/**
	 * Move Pawn
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void movePawn(String finalPos, char color) {
		boolean capture = finalPos.indexOf(CAPTURE) != -1;

		if (capture) {
			finalPos = finalPos.replace(Character.toString(CAPTURE), "");
		}

		String piece = color + SEPARATOR + PAWN;
		if (finalPos.length() == 3) {
			int oldpos = finalPos.charAt(0) - 'a';
			int x = finalPos.charAt(2) - '1';
			int y = finalPos.charAt(1) - 'a';

			if (color == WHITE)
				board[x - 1][oldpos] = BLANK;
			else
				board[x + 1][oldpos] = BLANK;

			board[x][y] = piece;
			return;
		}

		int x = finalPos.charAt(1) - '1';
		int y = finalPos.charAt(0) - 'a';

		if (capture) {
			if (color == WHITE && x > 0 && y > 0
					&& board[x - 1][y - 1].equals(piece)) {
				board[x - 1][y - 1] = BLANK;

			} else if (color == WHITE && x > 0 && y < 7
					&& board[x - 1][y + 1].equals(piece)) {
				board[x - 1][y + 1] = BLANK;

			} else if (color == BLACK && x < 7 && y > 0
					&& board[x + 1][y - 1].equals(piece)) {
				board[x + 1][y - 1] = BLANK;

			} else if (color == BLACK && x < 7 && y < 7
					&& board[x + 1][y + 1].equals(piece)) {
				board[x + 1][y + 1] = BLANK;
			}
			board[x][y] = piece;
			return;
		}

		if (color == WHITE) {
			for (int i = x - 1; i > Math.max(0, x - 3); i--) {
				if (board[i][y].equals(piece)) {
					board[i][y] = BLANK;
					break;
				}
			}
		} else {
			for (int i = x + 1; i < Math.min(x + 3, BOARDSIZE); i++) {
				if (board[i][y].equals(piece)) {
					board[i][y] = BLANK;
					break;
				}
			}
		}
		board[x][y] = piece;

	}

	/**
	 * Move Rook
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void moveRook(String finalPos, char color) {

		boolean capture = finalPos.indexOf(CAPTURE) != -1;

		if (capture) {
			finalPos = finalPos.replace(Character.toString(CAPTURE), "");
		}

		boolean finalPosGiven = (finalPos.length() == 3);

		String position = finalPos.substring(finalPos.length() - 2,
				finalPos.length());

		int x = position.charAt(1) - '1';
		int y = position.charAt(0) - 'a';

		int givenFromPosX = -1;
		int givenFromPosY = -1;

		if (finalPosGiven && Character.isAlphabetic(finalPos.charAt(0))) {
			givenFromPosY = finalPos.charAt(0) - 'a';
		}

		if (finalPosGiven && Character.isDigit(finalPos.charAt(0))) {
			givenFromPosX = finalPos.charAt(0) - '1';
		}

		String piece = color + SEPARATOR + ROOK;

		if (givenFromPosY == -1 || givenFromPosY == y) {

			for (int i = x + 1; i < 8; ++i) {

				if (board[i][y].startsWith(piece)) {
					if (givenFromPosX != -1 && givenFromPosX != i) {
						continue;
					}
					board[i][y] = BLANK;
					break;
				} else if (board[i][y] != BLANK)
					break;
			}
			for (int i = x - 1; i >= 0; --i) {

				if (board[i][y].startsWith(piece)) {

					if (givenFromPosX != -1 && givenFromPosX != i) {
						continue;
					}

					board[i][y] = BLANK;
					break;
				} else if (board[i][y] != BLANK)
					break;
			}
		}
		if (givenFromPosX == -1 || givenFromPosX == x) {
			for (int j = y + 1; j < 8; ++j) {

				if (board[x][j].startsWith(piece)) {

					if (givenFromPosY != -1 && givenFromPosY != j) {
						continue;
					}

					board[x][j] = BLANK;
					break;
				} else if (board[x][j] != BLANK)
					break;
			}
			for (int j = y - 1; j >= 0; --j) {

				if (board[x][j].startsWith(piece)) {

					if (givenFromPosY != -1 && givenFromPosY != j) {
						continue;
					}
					board[x][j] = BLANK;
					break;

				} else if (board[x][j] != BLANK)
					break;
			}
		}

		board[x][y] = piece;
	}

	/**
	 * Move Bishop
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void moveBishop(String finalPos, char color) {

		boolean capture = finalPos.indexOf(CAPTURE) != -1;

		if (capture) {
			finalPos = finalPos.replace(Character.toString(CAPTURE), "");
		}

		boolean finalPosGiven = (finalPos.length() == 3);

		String position = finalPos.substring(finalPos.length() - 2,
				finalPos.length());
		int y = position.charAt(0) - 'a';
		int x = position.charAt(1) - '1';

		String piece = color + SEPARATOR + BISHOP;

		for (int i = x + 1, j = y + 1; i < 8 && j < 8; ++i, ++j) {

			if (board[i][j].startsWith(piece)) {

				if (finalPosGiven && j != (finalPos.charAt(0) - 'a')) {
					break;
				}

				board[i][j] = BLANK;
				board[x][y] = piece;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}

		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; --i, --j) {

			if (board[i][j].startsWith(piece)) {

				if (finalPosGiven && j != (finalPos.charAt(0) - 'a')) {
					break;
				}
				board[i][j] = BLANK;
				board[x][y] = piece;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}

		for (int i = x + 1, j = y - 1; i < 8 && j >= 0; ++i, --j) {

			if (board[i][j].startsWith(piece)) {

				if (finalPosGiven && j != (finalPos.charAt(0) - 'a')) {
					break;
				}
				board[i][j] = BLANK;
				board[x][y] = piece;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}

		for (int i = x - 1, j = y + 1; i >= 0 && j < 8; --i, j++) {

			if (board[i][j].startsWith(piece)) {

				if (finalPosGiven && j != (finalPos.charAt(0) - 'a')) {
					break;
				}
				board[i][j] = BLANK;
				board[x][y] = piece;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}

	}

	/**
	 * Move Queen
	 * 
	 * @param pos
	 *            final position in Algebraic notation
	 * @param color
	 *            color of piece.
	 */
	void moveQueen(String Movetext, char color) {
		Movetext = Movetext.replaceAll(Character.toString(CAPTURE), SEPARATOR);

		String position = Movetext.substring(Movetext.length() - 2,
				Movetext.length());
		int y = position.charAt(0) - 'a';
		int x = Integer.parseInt(position.charAt(1) + SEPARATOR) - 1;

		for (int i = x + 1; i < 8; ++i) {

			if (board[i][y].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][y] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][y] != BLANK)
				break;
		}
		for (int i = x - 1; i >= 0; --i) {

			if (board[i][y].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][y] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][y] != BLANK)
				break;
		}
		for (int j = y + 1; j < 8; ++j) {

			if (board[x][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[x][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;

				return;
			} else if (board[x][j] != BLANK)
				break;
		}
		for (int j = y - 1; j >= 0; --j) {

			if (board[x][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[x][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[x][j] != BLANK)
				break;
		}

		for (int i = x + 1, j = y + 1; i < 8 && j < 8; ++i, ++j) {

			if (board[i][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}
		for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; --i, --j) {

			if (board[i][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}
		for (int i = x + 1, j = y - 1; i < 8 && j >= 0; ++i, --j) {

			if (board[i][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}
		for (int i = x - 1, j = y + 1; i >= 0 && j < 8; --i, j++) {

			if (board[i][j].startsWith(color + SEPARATOR + QUEEN)) {
				board[i][j] = BLANK;
				board[x][y] = color + SEPARATOR + QUEEN;
				return;
			} else if (board[i][j] != BLANK)
				break;
		}

	}

	/**
	 * Print Board.
	 */
	public void printBoard() {
		
		Map<String,String> unicodeChar = new HashMap<String,String>();
		unicodeChar.put("wK","\u2654 "); 
		unicodeChar.put("wQ","\u2655 "); 
		unicodeChar.put("wR","\u2656 "); 
		unicodeChar.put("wB","\u2657 "); 
		unicodeChar.put("wN","\u2658 "); 
		unicodeChar.put("wP","\u2659 "); 
		
		
		unicodeChar.put("bK","\u265A "); 
		unicodeChar.put("bQ","\u265B "); 
		unicodeChar.put("bR","\u265C "); 
		unicodeChar.put("bB","\u265D "); 
		unicodeChar.put("bN","\u265E "); 
		unicodeChar.put("bP","\u265F "); 
		
		
		System.out.println("  --------------------------");

		for (int i = 7; i >= 0; --i) {
			System.out.print((i + 1) + "||");

			for (int j = 0; j < 8; ++j) {
				if (board[i][j].equals(BLANK)) {
					System.out.print("  " + "|");
				} else {
					System.out.print(unicodeChar.get(board[i][j]) + "|");
				}
			}

			System.out.println("|");
			System.out.println("  --------------------------");
		}

		System.out.println("    a  b  c  d  e  f  g  h  ");
	}

	private boolean withinBoard(int x) {
		return (0 <= x && x < BOARDSIZE);
	}
}
