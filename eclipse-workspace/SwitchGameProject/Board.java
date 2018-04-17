package gameSet;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***********************************************************************
 * Board class for the Minesweeper game. The board contains a 2D array
 * of cells from the Cell class.
 * 
 * Credit goes to h.j.k. for the tutorial on 
 * https://codereview.stackexchange.com/questions/88636/beginner-
 * 			minesweeper-game
 * 
 * @author Cody Chinn
 * @version 1.2
 **********************************************************************/
public class Board {
	/** Create the 2D array for the cells. */
    private Cell[][] cells;
    /** How big the board is. */
    static final int SIDE = 8;
    /** Number of bombs. */
    static final int BOMBS = SIDE / 2;
    
    /*****************************************************************
     * Constructor for the board class. Sets up the 2D array of Cells
     * by running through each row times the number of sides specified
     *****************************************************************/
    public Board() {
    	cells = new Cell[SIDE][SIDE];
        IntStream.range(0, SIDE).forEach(i -> {
            IntStream.range(0, SIDE).forEach(j -> 
            cells[i][j] = new Cell(this));
        });
        initialize();
    }
    
    /***************************************************************
     * Initialize the board by inserting the bombs into the 2D
     * array and setting the values of the cells based on the bombs
     * location.
     * 
     **************************************************************/
    private void initialize() {
    	plantBombs();
    	setCellValues();
    }

    /************************************************************
     * Create the frame and window for the board to placed on.
     * 
     * @return JFrame - The visible frame for the game to take 
     * 					place on.
     ***********************************************************/
    public JFrame getBoard() {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.add(placeCells(), BorderLayout.NORTH);
        frame.add(addControlPanel(), BorderLayout.SOUTH);
        plantBombs();
        setCellValues();
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
    
    /*********************************************************
     * Add the controls panel into the game making it easier
     * to restart the game.
     * 
     * @return JPanel - The panel at the button of the window
     * 					containing the reset button
     ********************************************************/
    private JPanel addControlPanel() {
		JPanel panel = new JPanel();
		JButton reset = new JButton("Reset");
		reset.addActionListener(listener -> reset());
		panel.add(reset);
		return panel;
		
	}
    
    /*******************************
     * Reset each cell on the board.
     * 
     ******************************/
    private void reset() {
    	forEach(cell -> cell.reset());
    	initialize();
    }

	/**********************************************************
     * Put the cells onto the game board and organize them. 
     * 
     * @return JPanel - The panel for the game to be played on
     *********************************************************/
    private JPanel placeCells() {
        JPanel panel = new JPanel(new GridLayout(SIDE, SIDE));
        forEach(cell -> panel.add(cell.getButton()));
        return panel;
    }

    /************************************
     * Put the mines into the game board.
     * 
     ***********************************/
    public void plantBombs() {
    	Random random = new Random();
        int counter = 0;
        while (counter != BOMBS) {
            counter += cells[random.nextInt(SIDE)]
            		[random.nextInt(SIDE)].setBomb();
        }
    }
    
    /****************************************************************
     * This method counts number of mines around particular cell and
     * set its value.
     * 
     ***************************************************************/
    public void setCellValues() {
    	IntStream.range(0, SIDE).forEach(x -> {
            IntStream.range(0, SIDE).forEach(y -> {
                if (!cells[x][y].isBomb()) {
                    getSurroundingCells(x, y).stream()
                    .filter(Cell::isBomb)
                        .forEach(z -> cells[x][y].incrementValue());
                }
            });
        });
    }


    /*****************************************************************
     * If the cell that was clicked on is empty, this method will look
     * for more empty cells around the current cell.
     * 
     ****************************************************************/
    public void scanForEmptyCells() {
    	IntStream.range(0, SIDE).forEach(x -> {
            IntStream.range(0, SIDE).forEach(y -> {
                if (cells[x][y].isChecked()) {
                    getSurroundingCells(x, y).stream()
                    .filter(Cell::isEmpty)
                        .forEach(Cell::checkCell);
                }
            });
        });
    }
    
    
    /*************************************************************
     * This method checks the values of the cells around the one
     * that was clicked on. Note that this is just the values, 
     * not the cells themselves.
     * 
     * @param value - The value of the cell that is having it's 
     * 					sides checked for further numbers
     * @return IntStream - The 'list' of values around the current
     * 						cell
     *************************************************************/
    private IntStream sidesOf(final int value) {
        return IntStream.rangeClosed(value - 1, value + 1).filter(
                x -> x >= 0 && x < SIDE);
    }

    /**********************************************************
     * Check all of the cells around the given cell for values.
     * 
     * @param x - the x position of the cell in the 2D array
     * @param y - the y position of the cell in the 2D array
     * @return Set<Cell> - The list of cells and their values
     * 						around the given cell
     *********************************************************/
    private Set<Cell> getSurroundingCells(final int x, final int y) {
        Set<Cell> result = new HashSet<>();
        sidesOf(x).forEach(a -> {
            sidesOf(y).forEach(b -> result.add(cells[a][b]));
        });
        result.remove(cells[x][y]);
        return result;
    }

    /*************************************************************
     * Reveal all of the cells on the board when a game is failed.
     * 
     ************************************************************/
    public void fail() {
        for (Cell[] a : cells) {
            for (Cell b : a) {
                b.reveal(null);
            }
        }
    }
    
    /***********************************************************
     * Display the color of the cell.
     * 
     * @param color - The color of the cell being revealed
     **********************************************************/
    public void reveal(final Color color) {
        forEach(cell -> cell.reveal(color));
    }
    
    /**********************************************************
     * When the game is finished, this method is called to 
     * reveal all of the bombs on the board.
     * 
     * @return boolean - The bombs value checked against the
     * 						value of the cell being checked 
     * 						for a bomb.
     *********************************************************/
    public boolean isDone() {
    	int[] result = new int[1];
    	forEach(cell -> {
    		if (cell.isEmpty()) {
    			result[0]++; 
    			}
    		});
    	return result[0] == BOMBS;
    }
    
    /**********************************************************
     * This is a helper method to iterate through the 2D array
     * of cells using Consumer as a lambda expression.
     * 
     * @param consumer lambda expression
     *********************************************************/
    private void forEach(final Consumer<Cell> consumer) {
        Stream.of(cells).forEach(row -> Stream.of(row)
        		.forEach(consumer));
    }
}
