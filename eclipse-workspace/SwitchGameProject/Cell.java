package gameSet; 

import javax.swing.*;
import java.awt.*;

/***********************************************************************
 * Cell class for the Minesweeper game. Each Cell is placed on the board
 * with a value, if the value is -1 then it is a bomb and the game ends.
 * Otherwise, the cell contains a number representing the number of 
 * bombs around it.
 * 
 * Credit goes to h.j.k. for the tutorial on 
 * https://codereview.stackexchange.com/questions/88636/beginner-
 * 			minesweeper-game
 * 
 * @author Cody Chinn
 * @version 1.2
 **********************************************************************/
public class Cell {
	/** The button to make the cell operate.*/
    private JButton b;
    /** The board for the minesweeper game.*/
    private Board board;
    /** Number of bombs around a given cell.*/
    private int val;
    /** Determine whether or not the cell has been clicked on.*/
    private boolean checked;

    /*************************************************
     * Constructor for Cell class.
     * 
     * @param board - the board to place the cell on
     ************************************************/
    public Cell(Board board) {
    	this.board = board;
        b = new JButton();
        b.addActionListener(listener -> {
        	this.checkCell(); 
        	}
        );
        b.setPreferredSize(new Dimension(50, 50));
        b.setMargin(new Insets(0, 0, 0, 0));
        checked = false;
    }
    
    /***************************************************
     * Getter for the button instance variable.
     * 
     * @return JButton - The button on the on the board
     **************************************************/
    public JButton getButton() {
        return b;
    }

    /***************************************
     *  Getter for the value of the cell.
     *  
     *  @return int - The value of the cell
     ***************************************/
    public int getValue() {
        return val;
    }

    /**************************************
     * Setter for the value of the cell.
     * 
     * @param val - The value of the cell
     *************************************/
    public void setValue(final int val) {
        this.val = val;
    }

    /***************************************************************
     * Disables the button when you click on it, then determines if 
     * the value causes a loss.
     * 
     **************************************************************/
    public void checkCell() {
    	reveal(null);
        if (isBomb() || board.isDone()) {
            board.reveal(isBomb() ? Color.RED : Color.GREEN);
        } else if (val == 0) {
            board.scanForEmptyCells();
        }
    }
    
    /****************************************************************
     * If the cells value is -1, then it's a bomb.
     * 
     * @return boolean - True if the cell is a bomb
     ***************************************************************/
    public boolean isBomb() {
    	return val == -1;
    }
    
    /****************************************************************
     * Determine the value of the checked instance variable.
     * 
     * @return boolean - True if the cell has already been checked
     ***************************************************************/
    public boolean isChecked() {
    	return checked;
    }
    
    /****************************************************************
     * If the cells value is -1, then it's a bomb.
     * 
     * @return boolean - True if the cell is a bomb
     ***************************************************************/
    public boolean isEmpty() {
    	return !isChecked() && val == 0;
    }
    
    /****************************************************************
     * Set the cells value to -1 making it a bomb.
     * 
     * @return int - 0 if the bomb was placed, 1 if it was not
     ***************************************************************/
    public int setBomb() {
    	/** Make sure it's not already a bomb*/
    	if (!isBomb()) {
    		setValue(-1);
    		return 0;
    	}
    	return 1;
    }
    
    /****************************************************************
     * Displays the color of the cell, disables the button, and sets
     * the cell to checked.
     * 
     * @param color - the colors of the cells being revealed
     ***************************************************************/
    public void reveal(Color color) {
    	displayValue(color);
    	checked = true;
    	b.setEnabled(!checked);
    }
    
    /****************************************************************
     * Displays the value of the cell "under" the button.
     * 
     * @param color - The color the text in the cell
     ***************************************************************/
    public void displayValue(Color color) {
    	if (isBomb()) {
    		b.setText("\u2600");
    		b.setBackground(color);   		
    	} else if (val != 0) {
    		b.setText(String.valueOf(val));
    	}
    }
    
    /****************************************************
     * Add to the value of a cell with a mine next to it.
     * 
     ****************************************************/
    public void incrementValue() {
        val++;
    }

    /***************************************************
     * Reset the value, background color, and text of a
     * cell.
     *
     ***************************************************/
	public void reset() {
		setValue(0);
		b.setText("");
		b.setBackground(null);
		checked = false;
		b.setEnabled(!checked);
	}
}