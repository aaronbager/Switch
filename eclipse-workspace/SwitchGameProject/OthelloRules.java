package gameset;
import java.util.Arrays;
/**
 * Represents the rule set to play othello. Checks to see if move is legal, 
 * and will change pieces accordingly as game is played.
 * 
 * @author Aaron Bager 
 * @author Jessica Ricksgers
 * @author Cody Chinn
 * @author Joshua Stuart
 * @version 1.0
 * int[][] board Represents the othello gameboard
 * int turn Keeps track of the turn. 1 for black and 2 for white
 */
public class OthelloRules {
	/** represents the othello gameboard. 
	 * 0 for empty 1 for black 2 for white*/
	private int[][] board = new int[8][8];
	/**
	 * integer keeps track of the turn. 
	 * 1 for black 2 for white.
	 */
	private int turn; //1 for black, 2 for white.
	/**
	 * Used at the beggining to place initial pieces.
	 * @return A gameboard with the initial pieces placed
	 */
	public int[][] start() { // initializes the rules class
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//set all the values in board to 0, 
				//showing all spaces are empty
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
	/**
	 * 
	 * @param row the row where the piece is being plaed
	 * @param column the column where the piece is being placed
	 * @return A boolean that shows whether 
	 * 		   or not the move can be made by the rules
	 */
	boolean isMoveLegal(final int row, final int column) {	
		if (board[row][column] == 0) {
			
			if (checkNeighbors(row, column)) {
			
				return true;
			}
		}
		
		return false; 
		
	}
	/**
	 * used in the isMoveLegal function.
	 * @param row row value being checked
	 * @param column column value being checked
	 * @return A boolean declaring whether place on board has neighbors
	 */
	boolean checkNeighbors(
		               final int row, 
					   final int column) {
		
		int tilecount = 0;
		
		//check center pieces
		//assertTrue(row != 7);
		//assertTrue(row != 0);
		//assertTrue(column != 7);
		//assertTrue(column != 0);
		if (row != 7 && row != 0 && column != 7 && column != 0) {
			
			//check right
			//assertTrue(board[row + 1][column] != 0);
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;
			}
			//check top
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;	
			}
			//check left
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;
			}
			//check bottom
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;	
			}
		}
		
		//check bottom
		if (row == 7 && column != 0 && column != 7) {
			
			//check right
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;
			}
			//check left
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;
			}
			//check top
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;	
			}
			
		}
		
		//check top
		if (row == 0 && column != 0 && column != 7) {
			
			//check right
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;
			}
			//check bottom
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;	
			}
			//check left
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;
			}
		}
		
		//check left
		if (column == 0 && row != 0 && row != 7) {
			
			//check right
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;
			}
			//check top
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;	
			}
			//check bottom
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;	
			
		}
		}
			
		//check right
		if (column == 7 && row != 0 && row != 7) {
			
			//check top
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;	
			}
			//check left
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;
			}
			//check bottom
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;	
			}
			
		}
		
		//check top left
		if (column == 0 && row == 0) {
			
			//check bottom
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;	
			}
			//check right
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;
			}
			
		}
		
		//check top right
		if (column == 7 && row == 0) {
			
			//check left
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;
			}
			//check bottom
			if (board[row + 1][column] != 0) {
				
				tilecount += 1;	
			}
			
		}
		
		//check bootom left
		if (column == 0 && row == 7) {
			
			//check right
			if (board[row][column + 1] != 0) {
				
				tilecount += 1;
			}
			//check top
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;	
			}
		}
		
		//check bottom right
		if (column == 7 && row == 7) {
			
			//check top
			if (board[row - 1][column] != 0) {
				
				tilecount += 1;	
			}
			//check left
			if (board[row][column - 1] != 0) {
				
				tilecount += 1;
			}
			
			
		}	
			
		
		
		if (tilecount > 0) {
			
			return true;
		}
		
		return false;
		
	}	
/**
 * 
 * @param row row on gameboard where piece will be placed
 * @param column column on on gameboard where 
 * @return updated gameboard, with newly placed piece and flipped pieces
 */
	public int[][] placeTile(final int row, final int column) {
		
		int[][] tempBoard = new int[8][8];
		tempBoard = board; 
		System.out.println(Arrays.deepToString(tempBoard).replace("],"
				+ " ", "]\n"));
		board[row][column] = turn; 
		int oppositeTurn = (turn == 1) ? 2 : 1;
		System.out.println();
		int tempColumn = column;
		int tempRow = row; 
		//check left
	       OUTER: for (int i = column; i > 0; i--) { 
	           if (board[row][i - 1] == turn) {
	               for (int j = i - 1; j < column; j++) {
	                   tempBoard[row][j] = turn;
	               }
	               if (board[row][i] == 0) {
		               break OUTER;
		           }
	                break;
	           }
	           
	           if (board[row][i - 1] == 0) {
	               break;
	           }
	       }
	    //check right
		OUTER: for (int i = column; i < 7; i++) {
			if (board[row][i + 1] == turn) {
				for (int j = i + 1; j > column; j--) {
					tempBoard[row][j] = turn; 
				}
				if (board[row][i + 1] == 0) {
					break;
				}
				break; 
			}
		}
		//check up
	       OUTER: for (int i = row; i > 0; i--) { 
	           if (board[i - 1][column] == turn) {
	               for (int j = i - 1; j < row; j++) {
	                   tempBoard[j][column] = turn;
	               }
	               if (board[i - 1][column] == 0) {
		               break OUTER;
		           }
	                break;
	           }
	           
	           if (board[i][column] == 0) {
	               break;
	           }
	       }
		//check down
		OUTER: for (int i = row; i < 7; i++) {
			if (board[ i + 1][column] == turn) {
				for (int j = i + 1; j > row; j--) {
					tempBoard[j][column] = turn; 
				}
				if (board[i][column] == 0) {
					break OUTER;
				}
				break; 
			}
		}
		//check diagonal up left
	    for (int i = column; i > 0; i--) {
	    	for (int j = row; j > 0; j--) {
	    		if (board[j - 1][i - 1] == turn) {
	    			tempBoard[j][i] = turn;
	    		}
	    	}
	    }
		//check diagonal down left
	    for (int i = column; i > 0; i--) {
	    	for (int j = row; j < 7; j++) {
	    		if (board[j + 1][i - 1] == turn) {
	    			tempBoard[j][i] = turn; 
	    		}
	    	}
	    }
		//check diagonal up right
	    for (int i = column; i < 7; i++) {
	    	for (int j = row; j < 7; j++) {
	    		if (board[j + 1][i + 1] == turn) {
	    			tempBoard[j][i] = turn; 
	    		}
	    	}
	    }
		//check diagonal down right
	    for (int i = column; i < 7; i++) {
	    	for (int j = row; j > 0; j--) {
	    		if (board[j - 1][i + 1] == turn) {
	    			tempBoard[j][i] = turn; 
	    		}
	    	}
	    }
		if (turn == 1) {
			turn = 2;
		} else {
			turn = 1; 
		} //changes turn
		return tempBoard;
	}
	/**
	 * used at end of game to declare winner.
	 * @return int value that shows winner
	 */
	public int declareWinner() {
		int blackPieces = 0;
		int whitePieces = 0; 
		for (int i = 0; i < 7; i++) {			
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == 1) {
					blackPieces++;
				} else if (board[i][j] == 2) {
					whitePieces++; 
				}
			}
		}
		if (blackPieces > whitePieces) {
			return 1; 
		} else if (blackPieces < whitePieces) {
			return 2; 
		} else {
			return 3; 
		}
	}
	/**
	 * shows who's turn it is.
	 * @return integer representing player
	 */
	public int getTurn() {
		return turn; 
	}
	/**
	 * calculates score based on pieces on the board.
	 * @param color declares which color pieces you want the score of
	 * @return score of player color passed through
	 */
	public int getScore(final int color) {
		int score = 0; 
		for (int i = 0; i < 7; i++) {			
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == color) {
					score++;
				}
			}
		}
		return score; 
	}
}
