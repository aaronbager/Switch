
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

	boolean isMoveLegal(int row, int column, int value)
	{
		return true; 
	}
	int[][] placeTile(int row, int column){
		
		board[column][row] = turn; 
		
		//check left
		int counter = 0;
		for(int i = row; i > 0; i-- ) {
			if(board[column][i] == board[i - 1][column]) {
				for(int j = i - 1; j < row; j++) {
					board[column][j] = turn; 
				}
			}
			counter ++; 
			if(board[column][i] == 0) {
				break; 
			}
		}
		//check right
		counter = 0;
		for(int i = row; i < 8; i++ ) {
			if(board[i][column] == board[i + 1][column]) {
				for(int j = i + 1; j < row; j--) {
					board[j][column] = turn; 
				}
			}
			counter ++; 
			if(board[i][column] == 0) {
				break; 
			}
		}
		//check up
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
