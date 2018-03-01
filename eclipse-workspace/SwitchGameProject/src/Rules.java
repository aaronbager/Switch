import java.util.Arrays;

public class Rules {
	int[][] board = new int[8][8]; //represents the board, 0 is empty, 1 is black, 2 is white
	int turn; //1 for black, 2 for white, 3 for black wins, 4 for white wins
	
	int[][] start(){ // initializes the rules class
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

	boolean isMoveLegal(int row, int column)
	{
		if(board[row][column] == 0)
			return true; 
		
		return false; 
	}
	int[][] placeTile(int row, int column){
		
		
		System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
		board[row][column] = turn; 
		int oppositeTurn = (turn == 1) ? 2 : 1;
		System.out.println();

		//check left
		int counter = 0; 
	       OUTER:for(int i = column; i > 0; i-- ) {
	    	   counter++; 
	           if(board[row][i] == board[row][i-1]) {
	               for(int j = i + 1; j > column; j--) {
	                   board[row][j] = turn;
	               }
	               if(board[row][i] == 0) {
		               break OUTER;
		           }
	                break;
	           }
	           
	           if(board[row][i] == 0) {
	               break;
	           }
	       }
	       		//check right
		
		/*for(int i = row; i < 8; i++ ) {
			System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
			System.out.println();
			if(board[i][column] == board[i + 1][column]) {
				System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
				System.out.println();
				for(int j = i + 1; j < row; j--) {
					System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
					System.out.println();
					board[j][column] = turn; 
				}
			}*/
	       
	       /*for(int i = column + 1; i < 8; i++ ) {
	           if(board[row][i] == board[row][i+1]) {
	               for(int j = i + 1; j < column; j--) {
	                   board[row][j] = turn;
	               }
	                break;
	           }

			if(board[i][column] == 0) {
				break; 
			}
		}
*/		//check up
		//check down
		//check diagonal up left
		//check diagonal down left
		//check diagonal up right
		//check diagonal down right
		if(turn == 1) {
			turn = 2;
		}
		else {
			turn = 1; 
		} //changes turn
		return board;
	}
	int getTurn() {
		return turn; 
	}
}
