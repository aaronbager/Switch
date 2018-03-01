import java.util.Arrays;

public class Rules {
	private int[][] board = new int[8][8]; //represents the board, 0 is empty, 1 is black, 2 is white
	private int turn; //1 for black, 2 for white, 3 for black wins, 4 for white wins
	
	public int[][] start(){ // initializes the rules class
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//set all the values in board to 0, showing all spaces are empty
				board[i][j] = 0;
			}
		}
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2; 
		turn = 1; 
		return board; 
	}

	public boolean isMoveLegal(int row, int column)
	{
		if(board[row][column] == 0)
			return true; 
		
		return false; 
	}
	public int[][] placeTile(int row, int column){
		
		
		System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
		board[row][column] = turn; 
		int oppositeTurn = (turn == 1) ? 2 : 1;
		System.out.println();
		int tempColumn = column;
		int tempRow = row; 
		//check left
	       OUTER:for(int i = column; i > 0; i-- ) { 
	           if(board[row][i-1] == turn) {
	               for(int j = i - 1; j < column; j++) {
	                   board[row][j] = turn;
	               }
	               if(board[row][i] == 0) {
		               break OUTER;
		           }
	                break;
	           }
	           
	           if(board[row][i-1] == 0) {
	               break;
	           }
	       }
	    //check right
		OUTER:for(int i = column; i < 7; i++) {
			if(board[row][i+1] == turn) {
				for(int j = i + 1; j > column; j--) {
					board[row][j] = turn; 
				}
				if(board[row][i+1] == 0) {
					break;
				}
				break; 
			}
		}
		//check up
	       OUTER:for(int i = row; i > 0; i-- ) { 
	           if(board[i-1][column] == turn) {
	               for(int j = i - 1; j < row; j++) {
	                   board[j][column] = turn;
	               }
	               if(board[i-1][column] == 0) {
		               break OUTER;
		           }
	                break;
	           }
	           
	           if(board[i][column] == 0) {
	               break;
	           }
	       }
		//check down
		OUTER:for(int i = row; i < 7; i++) {
			if(board[i+1][column] == turn) {
				for(int j = i + 1; j > row; j--) {
					board[j][column] = turn; 
				}
				if(board[i][column] == 0) {
					break OUTER;
				}
				break; 
			}
		}
		//check diagonal up left
	    for(int i = column; i > 0; i--) {
	    	for(int j = row; j > 0; j--) {
	    		if(board[j-1][i-1] == turn) {
	    			board[j][i] = turn;
	    		}
	    	}
	    }
		//check diagonal down left
	    for(int i = column; i > 0; i--) {
	    	for(int j = row; j < 7; j++) {
	    		if(board[j+1][i-1] == turn) {
	    			board[j][i] = turn; 
	    		}
	    	}
	    }
		//check diagonal up right
	    for(int i = column; i < 7; i++) {
	    	for(int j = row; j < 7; j++) {
	    		if(board[j+1][i+1] == turn) {
	    			board[j][i] = turn; 
	    		}
	    	}
	    }
		//check diagonal down right
	    for(int i = column; i < 7; i++) {
	    	for(int j = row; j > 0; j--) {
	    		if(board[j-1][i+1] == turn) {
	    			board[j][i] = turn; 
	    		}
	    	}
	    }
		if(turn == 1) {
			turn = 2;
		}
		else {
			turn = 1; 
		} //changes turn
		return board;
	}
	public int declareWinner() {
		int blackPieces = 0;
		int whitePieces = 0; 
		for(int i = 0; i < 7; i++) {			
			for(int j = 0; j < 7; j++) {
				if(board[i][j] == 1) {
					blackPieces++;
				}
				else if(board[i][j] == 2) {
					whitePieces++; 
				}
			}
		}
		if(blackPieces > whitePieces) {
			return 1; 
		}
		else if(blackPieces < whitePieces) {
			return 2; 
		}
		else {
			return 3; 
		}
	}
	public int getTurn() {
		return turn; 
	}
	public int getScore(int color) {
		int score = 0; 
		for(int i = 0; i < 7; i++) {			
			for(int j = 0; j < 7; j++) {
				if(board[i][j] == color) {
					score++;
				}
			}
		}
		return score; 
	}
}
