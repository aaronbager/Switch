
public class Rules {
	int[][] board = new int[8][8]; //represents the board, 0 is empty, 1 is black, 2 is white
	int turn = 1; //1 for black, 2 for white, 3 for black wins, 4 for white wins
	
	int[][] start(){ // initializes the rules class
		for(int i = 0; i < 8; i++) {
			for(int j = 0; i < 8; j++) {
				//set all the values in board to 0, showing all spaces are empty
				board[i][j] = 0;
			}
		}
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2; 
		return board;
	}

	boolean isMoveLegal(int row, int column, int value)
	{
		return true; 
	}
	int[][] placeTile(int row, int column){
		//check left
		//check right
		//check up
		//check down
		//check diagonal up left
		//check diagonal down left
		//check diagonal up right
		//check diagonal down right
		return board;
	}
}
