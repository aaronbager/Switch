import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*************************************
 * Cell class for the Minesweeper game
 ************************************/
public class Cell implements ActionListener{
    private JButton b;
    private Board board;
    private int val;
    private int id;
    private boolean notChecked;

    /***********************************************
     * Constructor for Cell class
     * 
     * @param board - the board to place the cell on
     **********************************************/
    public Cell(Board board){
        b = new JButton();
        b.addActionListener(this);
        b.setPreferredSize(new Dimension(50,50));
        b.setMargin(new Insets(0,0,0,0));
        this.board = board;
        notChecked = true;
    }
    
    /*****************************************
     * Getter for the button instance variable
     *****************************************/
    public JButton getButton() {
        return b;
    }

    /************************************
     *  Getter for the value of the cell
     ************************************/
    public int getValue() {
        return val;
    }

    /********************************
     *  Getter for the ID of the cell
     *******************************/
    public int getId() {
        return id;
    }

    /********************************
     *  Setter for the ID of the cell
     *******************************/
    public void setId(int id) {
        this.id = id;
    }

    /************************************
     * Setter for the value of the cell
     ***********************************/
    public void setValue(int val) {
        this.val = val;
    }

    /****************************************************************
     * Displays the number of mines next to the Cell, or the current
     *  number in that cell
     ***************************************************************/
    public void displayValue(){
        if(val==-1){
        	// Bomb Cells are red
            b.setText("\u2600");
            b.setForeground(Color.BLACK);
            b.setBackground(Color.RED);
        }else if(val == 1){
        	// Cells with a value of 1 are green
            b.setText(String.valueOf(val));
            b.setForeground(Color.BLACK);
            b.setBackground(Color.GREEN);
        }else if(val == 2) {
        	// Cells with a value of 2 are yellow
        	b.setText(String.valueOf(val));
        	b.setForeground(Color.BLACK);
            b.setBackground(Color.YELLOW);
        }else if(val >= 3) {
        	// Cells with a value of 3 or above are orange
        	b.setText(String.valueOf(val));
        	b.setForeground(Color.BLACK);
            b.setBackground(Color.ORANGE);
        }
        // All other cells are empty and without fill
    }

    /***************************************************************
     * Disables the button when you click on it, then determines if 
     * the value causes a loss
     **************************************************************/
    public void checkCell(){
        b.setEnabled(false);
        displayValue();
        notChecked = false;
        if(val == 0) 
        	board.scanForEmptyCells();
        
        if(val == -1) 
        	board.fail();
    }

    /***************************************************
     * Add to the value of a cell with a mine next to it
     ***************************************************/
    public void incrementValue(){
        val++;
    }

    /*************************************
     *  Sees if the cell has been checked 
     ***********************************/
    public boolean isNotChecked(){
        return notChecked;
    }

    /*****************************************
     * Check to see if the cell has no value
     ****************************************/
    public boolean isEmpty(){
        return isNotChecked() && val==0;
    }

    /***************************************
     * Display the number 'under' the button
     **************************************/
    public void reveal(){
        displayValue();
        b.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCell();
    }

}