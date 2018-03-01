import java.util.ArrayList;
//import java.awt.Color;
import javafx.scene.paint.Color; 
import java.util.*;
import java.util.ArrayList;

public class reversi {
/** The number of rows (height) and columns (width). */
private int rows,cols;
/** The game board */
private Cell[][] board;
/** Enumerated variable to keep track of game status. */
private GameStatus status = GameStatus.IN_PROGRESS;
/** A list of all the non-empty tiles on the board */
private ArrayList<Cell> nonEmpty;

public Cell[][] getGame(){
	return board;
	
}
public int passTurn(){
	int v=0;
	if(status == GameStatus.PlayerOneTurn){
		status = GameStatus.PlayerTwoTurn;
		 v = 2;
	}
	if(status == GameStatus.PlayerTwoTurn){
		status = GameStatus.PlayerOneTurn;
		v=1;
	}
	return v;
}

public int getRows(){
	return rows;
}

public int getCols(){
	return cols;
}

public GameStatus getStatus(){
	return status; 
}
public int getBlackScore(){
	int count=0;
	for(int i = 0; i<=rows; i++){
		for(int j = 0;j<=cols; j++ ){
			if(board[i][j].getColor() == Color.BLACK){
				count++;
			}
				
		}
}
return count;
}

public void start(){
	new Cell((rows/2),(cols/2),1);
	new Cell(((rows/2) +1),((cols/2) +1),1);
	new Cell((rows/2 +1),(cols/2),2);
	new Cell((rows/2),(cols/2 +1),2);
}


public int getWhiteScore(){
	int count=0;
	for(int i = 0; i<=rows; i++){
		for(int j = 0;j<=cols; j++ ){
			if(board[i][j].getColor() == Color.WHITE){
				count++;
			}
				
		}
}
return count;
}
public void placeTile(int r, int c){
	
	if(status == GameStatus.PlayerOneTurn){
		new Cell(r,c,1);
		status = GameStatus.PlayerTwoTurn; 
}
	if(status == GameStatus.PlayerTwoTurn){
		new Cell(r,c,2);
		status = GameStatus.PlayerOneTurn; 
}
	}
		


public void resizeBoard(int size) {
	// Make sure win value a power of 2, row/col valid values.
	// -want at least a 2x2 board
	if( size > 4 && size%2==0) {
		rows = size;
		cols = size;
		
		for(int i = 0; i< size; i++)
			for(int j=0; j < size; j++)
				new Cell(i,j,0);
		// create a blank 2D array (all zeros) for board
		//board = new Cell[rows][cols];
		status = GameStatus.IN_PROGRESS;
	}
	else{
		System.out.println("Please enter an positive even interger that is greater than 4");
		throw new IllegalArgumentException();
}
}

public ArrayList<Cell> getNonEmptyTiles() {
	// create a new ArrayList to store nonempty cells
	nonEmpty = new ArrayList<Cell>();
	
	// search through 2D array to find non-empty cells
	// store each non-empty cell in the ArrayList
	for(int i = 0; i < rows; i++) {
		for(int j = 0; j < cols; j++) {
			// create a cell for each non-empty location on board
			// to store within the ArrayList
			if(board[i][j] != null) {
				int v = board[i][j].getValue();
				// assign the row, col of non-empty cell in cell object
				//c = new Cell(i, j, board[i][j]);
				// add this cell to the list
				nonEmpty.add(new Cell(i, j, v));
			}
		}
	}
	return nonEmpty;
}
public boolean legalMove(int r, int c){
	if(status == GameStatus.PlayerOneTurn){
		Cell move = new Cell(r,c,1);
		checkTiles(move);
		for(int i = move.row - 1; i >= 0; i--){
		if(board[i][c].getValue() == move.getValue())
			
		}
}
	if(status == GameStatus.PlayerTwoTurn){
		new Cell(r,c,2);
}
	}

private boolean movePossible() {
	// check if space available
	getNonEmptyTiles(); // find all non-empty tiles
	if( nonEmpty.size() == (rows * cols) ) {
		// check if 2 adjacent cells have the same value if full
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) {
				if(i < rows - 1 && j < cols - 1) {
					if(board[i][j] == board[i][j + 1] ||
						board[i][j] == board[i + 1][j]) {
						//status = GameStatus.IN_PROGRESS;
						return true;
					}
				}
				else if(i == rows - 1 && j < cols - 1) {
					if(board[i][j] == board[i][j + 1]) {
						//status = GameStatus.IN_PROGRESS;
						return true;
					}
				}
			}
		status = GameStatus.PlayerTwoWins;
		return false;
	}
	status = GameStatus.IN_PROGRESS; // put here?
	return true;
}
public boolean checkTiles(Cell c){
	//
	//for(int i = c.row + 1; i < getRows(); i++){
	//check.add([i][c.getColumn])
	//if( c.getColor() != )
	//
	//
	//
	placeTile(c.getRow(),c.getColumn());
	Stack<Cell[][]> tempBoard = new Stack<Cell[][]>();
	tempBoard.push(board);
	ArrayList<Cell> check = new ArrayList<Cell>();
	//Checking to the right of the placed tile
	Cell temp = new Cell();
		for(int i = c.row + 1; i < getRows(); i++){
			temp.setRow(i);
			temp.setColumn(c.getColumn());
			check.add(temp);
			
			if(board[i][c.column].getValue() == 0){
				placeTile(i, c.getColumn());
				
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
			}
			check.clear();
				//Flip all the tiles in between
				
			}	
		}

		//Checking to the left of the placed tile
		for(int i = c.row - 1; i >= 0; i--){
		//	temp.setRow(i);
			//temp.setColumn(c.getColumn());
			//check.add(temp);
			
			if(0 == board[i][c.column].getValue()){
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
				check.clear();
				//Flip all the tiles in between
			}	
		}

		//Checking above the placed tile
		for(int j = c.column + 1; j < getCols(); j++){
			temp.setColumn(j);
			temp.setRow(c.getRow());
			check.add(temp);
			//adds tiles between selected cells into ArrayList to be changed.
			if(0 == board[c.row][j].getValue()){
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
				check.clear();
				//Flip all the tiles in between
			}	
		}

		//Checking below the placed tile
		for(int j = c.column - 1; j >= 0; j--){
			temp.setColumn(j);
			temp.setRow(c.getRow());
			check.add(temp);
			//adds tiles between selected cells into ArrayList to be changed.
			if(0 == board[c.row][j].getValue()){
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
				check.clear();
				//Flip all the tiles in between and clears the list
			}	
		}

		//Checking the diagonal down-left of the placed tile
		for(int i = c.row - 1; i >= 0; i--){
			int j = c.column - c.row + i;
			temp.setColumn(j);
			temp.setRow(i);
			check.add(temp);
			if(0 == board[i][c.column].getValue()){
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
				check.clear();
				//Flip all the tiles in between
			}	
		}

		//Checking the diagonal up-left of the placed tile
		for(int i = c.row - 1; i >= 0; i--){
			int j = c.column + c.row - i;
			temp.setColumn(j);
			temp.setRow(i);
			check.add(temp);
			if(0 == board[i][c.column].getValue()){
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
				check.clear();
				//Flip all the tiles in between
				
			}	
		}

		//Checking the diagonal down-right of the placed tile
		for(int i = c.row + 1; i < getRows(); i++){
			int j = c.column - c.row + i;
			temp.setColumn(j);
			temp.setRow(i);
			check.add(temp);
			if(c.getValue() == board[i][c.column].getValue()){
				//Flip all the tiles in between
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
			}	
		}

		//Checking the diagonal up-right of the placed tile
		for(int i = c.row + 1; i < getRows(); i++){
			int j = c.column + c.row - i;
			temp.setColumn(j);
			temp.setRow(i);
			check.add(temp);
			if(c.getValue() == board[i][c.column].getValue()){
				//Flip all the tiles in between
				for(Cell d: check){
					d.setValue(c.getValue());
					d.setColor(c);
				}
			}
			
		} 
		if (tempBoard.pop() == board)
			return false;
		else
			return true;
}
}
