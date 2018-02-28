import javafx.scene.paint.Color;
//import java.awt.Color;

public class Cell implements Comparable<Cell> {
    public int row, column, value;
    public Color color = null;
    

    public Cell()
    {
        this(0,0,0);
    }
    public Cell (int r, int c, int v)
    {
        row = r;
        column = c;
        value = v;
    }

    /* must override equals to ensure List::contains() works
     * correctly
     */
    @Override
    public boolean equals(Object c) {
        if(compareTo((Cell)c) == 0)
        	return true;
        return false;  	
    }
    
    @Override
    public int compareTo (Cell other) {
        if (this.row < other.row) return -1;
        if (this.row > other.row) return +1;

        /* break the tie using column */
        if (this.column < other.column) return -1;
        if (this.column > other.column) return +1;

        return this.value - other.value;
    }
    
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getValue() {
		return value; 
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void setColor(Cell c){
		switch(c.value){
		case 1: c.color =color.BLACK;
		break;
		//if value contained in cell is 1, then change color of the tile to black.
		case 2: c.color = color.WHITE;
		break; 
		//if value contained in cell is 1, then change color of the tile to white. 
		default: c.color = null;
		// if no value is contained within the cell that is passed cell is unclaimed.
		}
		
			//returns the new color of the cell if changes are made. 
	}
	public Color getColor()
	{
		if(value == 1) {
			return Color.BLACK;
		}
		else {
			return Color.WHITE; 
		}
	}
	
	
}
