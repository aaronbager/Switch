import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/***************************************
 * Board Class for the Minesweeper game
 ***************************************/
public class Board {
	// Create the 2D array for the cells
    private Cell[][] cells;
    // Give each cell a unique ID
    private int cellID = 0;
    // How big the board is
    private int side = 8;
    // The limit for the scanning methods (so there's no
    // Out of Bounds exception)   
    private int limit = side-2;

    /************************
     * Create the game board
     ***********************/
    public void setBoard(){
        JFrame frame = new JFrame();
        frame.add(placeCells());
        plantBombs();
        setCellValues();
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /************************************
     * Put the cells onto the game board
     ***********************************/
    public JPanel placeCells(){
        JPanel panel = new JPanel(new GridLayout(side,side));
        cells = new Cell[side][side];
        for(int i = 0; i< side; i++){
            for(int j = 0; j<side; j++){
                cells[i][j] = new Cell(this);
                cells[i][j].setId(getID());
                panel.add(cells[i][j].getButton());
            }
        }
        return panel;
    }

    /************************************
     * Put the mines into the game board
     ***********************************/
    public void plantBombs(){
        ArrayList<Integer> field = createMineField(10);
        for(int i : field){
            getCell(i).setValue(-1);
        }
    }
    
    /**********************************
     * Choose random places for mines
     **********************************/
    public ArrayList<Integer> createMineField(int i){
        ArrayList<Integer> field = new ArrayList<Integer>();
        int r;
        for(int j = 0; j<i;){
            r = (int)(Math.random()* (side*side));
            if(!field.contains(r)){
                field.add(r);
                j++;
            }
        }
        return field;
    }
    
    /*****************************************************************************
     * This method counts number of mines around particular cell and set its value
     *****************************************************************************/
    public void setCellValues(){
        for(int i = 0; i<side; i++){
            for(int j = 0; j<side; j++){
                 if(cells[i][j].getValue() != -1){
                     if(j>=1 && cells[i][j-1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(j<= limit && cells[i][j+1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i>=1 && cells[i-1][j].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i<= limit && cells[i+1][j].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i>=1 && j>= 1 && cells[i-1][j-1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i<= limit && j<= limit && cells[i+1][j+1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i>=1 && j<= limit && cells[i-1][j+1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                     
                     if(i<= limit && j>= 1 && cells[i+1][j-1].getValue() == -1) 
                    	 cells[i][j].incrementValue();
                 }
            }
        }
    }

    /*********************************************************************************
     * If the cell that was clicked on is empty, this method will look for more empty 
     * cells around the current cell
     *******************************************************************************/
    public void scanForEmptyCells(){
        for(int i = 0; i<side; i++){
            for(int j = 0; j<side; j++){
                if(!cells[i][j].isNotChecked()){
                	
                    if(j>=1 && cells[i][j-1].isEmpty()) 
                    	cells[i][j-1].checkCell();
                    
                    if(j<= limit && cells[i][j+1].isEmpty()) 
                    	cells[i][j+1].checkCell();
                    
                    if(i>=1 && cells[i-1][j].isEmpty()) 
                    	cells[i-1][j].checkCell();
                    
                    if(i<= limit && cells[i+1][j].isEmpty()) 
                    	cells[i+1][j].checkCell();
                    
                    if(i>=1 && j>= 1 && cells[i-1][j-1].isEmpty()) 
                    	cells[i-1][j-1].checkCell();
                    
                    if(i<= limit && j<= limit && cells[i+1][j+1].isEmpty()) 
                    	cells[i+1][j+1].checkCell();
                    
                    if(i>=1 && j<= limit && cells[i-1][j+1].isEmpty()) 
                    	cells[i-1][j+1].checkCell();
                    
                    if(i<= limit && j>= 1 && cells[i+1][j-1].isEmpty()) 
                    	cells[i+1][j-1].checkCell();
                }
            }
        }
    }

    /********************************
     * Getter for the ID of the Cell
     ********************************/
    public int getID(){
        int id = cellID;
        cellID++;
        return id;
    }


    /*********************************
     * Get a specific cell by it's ID
     ********************************/
    public Cell getCell(int id){
        for(Cell[] a : cells){
            for(Cell b : a){
                if(b.getId() == id) return b;

            }
        }
        return null;
    }


    /*************************************************************
     * Reveal all of the cells on the board when a game is failed
     ************************************************************/
    public void fail(){
        for(Cell[] a : cells){
            for(Cell b : a){
                b.reveal();
            }
        }
    }
}