
public class PGNReader {
	private static final int BOARDSIZE = 8;

	private static final String BLANK = " ";

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
		board = new String[BOARDSIZE][BOARDSIZE];		
		initBoard();

	}

	private void initBoard() {

		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				board[i][j] = BLANK;
			}
		}
		
		
		for (int i = 0; i < BOARDSIZE; i++) {
			board[1][i] = WHITE+""+PAWN;
			board[6][i] = BLACK+""+PAWN;
		}
		
		board[0][0] = WHITE+""+ROOK;
		board[0][7] = WHITE+""+ROOK;
		board[7][0] = BLACK+""+ROOK;
		board[7][7] = BLACK+""+ROOK;

		board[0][1] = WHITE+""+KNIGHT;
		board[0][6] = WHITE+""+KNIGHT;
		board[7][1] = BLACK+""+KNIGHT;
		board[7][6] = BLACK+""+KNIGHT;
		
		board[0][2] = WHITE+""+BISHOP;
		board[0][5] = WHITE+""+BISHOP;
		board[7][2] = BLACK+""+BISHOP;
		board[7][5] = BLACK+""+BISHOP;
		
		
		board[0][3] = WHITE+""+KING;
		board[7][4] = BLACK+""+KING;
		
		board[0][4] = WHITE+""+QUEEN;
		board[7][3] = BLACK+""+QUEEN;
	}

	public void move(String moveNotation) {
		
		String[] s = moveNotation.trim().split(" ");
		
		System.out.println(s[0].trim()+"-"+s[1].trim());

		executeMove(s[0].trim(), WHITE);
		executeMove(s[1].trim(), BLACK);
	}

	private void executeMove(String pos, char color) {

		
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
			//TODO handle 
			break;
		default:
			movePawn(pos, color);
		}
	}

	private void moveKing(String finalPos, char color) {
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

	
	private void movePawn(String finalPos, char color) {
		boolean  capture = finalPos.indexOf(CAPTURE) != -1;
		System.err.println(finalPos);
		
		if(capture) {
			finalPos = finalPos.replace(Character.toString(CAPTURE), "");
		}
		
		int x = finalPos.charAt(0)-'a';
		int y = finalPos.charAt(1)-'1';
		
		if(capture) {
			
			if(color == WHITE && board[x-1][y-1].equals(color+PAWN) ) {
				board[x-1][y-1] = BLANK;				
			
			} else if (color == WHITE && board[x+1][y-1].equals(color+PAWN) ) {
				board[x+1][y-1] = BLANK;
				
			} else if (color == BLACK && board[x-1][y+1].equals(color+PAWN) ) {
				board[x-1][y+1] = BLANK;
			
			} else {
				board[x+1][y+1] = BLANK;
			
			}
			board[x][y] = color+""+PAWN;
			return;
		}
		
		if(color == WHITE) {
			for (int i = y-1; i >0 ; i--) {
				if (board[x][i].equals(color+PAWN)) {
					board[x+1][i] = BLANK;
					break;
				}
			}
		} else {
			for (int i = y+1; i < BOARDSIZE ; i++) {
				if (board[x][i].equals(color+PAWN)) {
					board[x+1][i] = BLANK;
					break;
				}
			}
		}
		board[x][y] = color+""+PAWN;
		
	}
	
	public void printBoard(){
		for(int i=0;i<8;++i){
			for(int j=7;j>=0;--j){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public void moveRook(String Movetext,char color){
		printBoard();
    	char colorToSearch=BLACK;
    	  if(color==BLACK){
    		  colorToSearch=WHITE;
    	  }
    	  final String ASSUMENULL="";
    	  Movetext="Qa3";
    	  String position=Movetext.substring(Movetext.length()-2, Movetext.length());
    	  int x =position.charAt(0)-'a';
    	  int y =Integer.parseInt(position.charAt(1)+"")-1; 
    	  System.out.println(x+"  "+y);
    	  
    	  for(int i=x+1;i<8;++i){
    		  if(board[i][y]==ASSUMENULL) break;
    		  if(board[i][y].startsWith(colorToSearch+""+ROOK)){
    			  board[i][y]=ASSUMENULL;
    			  board[x][y]=color+""+ROOK+"";
    			  return;
    		  }
    	  }
    	  for(int i=x-1;i>=0;--i){
    		  if(board[i][y]==ASSUMENULL) break;
    		  if(board[i][y].startsWith(colorToSearch+""+ROOK)){
    			  board[i][y]=ASSUMENULL;
    			  board[x][y]=color+""+ROOK+"";
    			  return;
    		  }
    	  }
    	  for(int j=y+1;j<8;++j){
    		  if(board[j][x]==ASSUMENULL) break;
    		  if(board[j][x].startsWith(colorToSearch+""+ROOK)){
    			  board[j][x]=ASSUMENULL;
    			  board[x][y]=color+""+ROOK+"";
    			  return;
    		  }
    	  }
    	  for(int j=y-1;j>=0;--j){
    		  if(board[j][x]==ASSUMENULL) break;
    		  if(board[j][x].startsWith(colorToSearch+""+ROOK)){
    			  board[j][x]=ASSUMENULL;
    			  board[x][y]=color+""+ROOK+"";
    			  return;
    		  }
    	  }
    	  
		
	}
	
}
