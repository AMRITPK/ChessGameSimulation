
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
	public void moveRook(String Movetext,char color){
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
