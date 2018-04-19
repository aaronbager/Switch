package gameset;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
*/
/*****************************************************************

 *****************************************************************/
public class Dice extends JPanel implements MouseListener, Comparable {
    /** current value of the die. */
    private int myValue, displayValue; 

    /** is the dice currently on hold? */
    private boolean held, frozen;

    /** current size in pixels. */
    private int mySize;

    /** dot size in pixels defined by overall die size. */ 
    private int dotSize;

    /** offset in pixels for the left row of dots. */ 
    private int left;

    /** offset in pixels for the right row of dots. */ 
    private int right;

    /** offset in pixels for the middle dot. */ 
    private int middle;

    /** color of the dice when held. */
    private Color heldColor = Color.pink;

    /** default color of dice. */
    private Color background = Color.white;

    /** repeats for animation. */
    private int numRolls;

    /** Timer for animation. */
    private javax.swing.Timer myTimer;

    /*****************************************************************
    constructor creates a die of specified size X size pixels.

    @param size the length of each side in pixels.
     *****************************************************************/
    public Dice(final int size) {
        // initialize the die and determine display characteristics 
        mySize = size;
        held = false; 
        frozen = false;
        dotSize = mySize / 5;
        int spacer = (mySize - (3 * dotSize)) / 4; 
        left = spacer; 
        right = mySize - spacer - dotSize; 
        middle = (mySize - dotSize) / 2;
        setBackground(background);
        setForeground(Color.black);
        setSize(size, size); 
        setPreferredSize(new Dimension(size, size));
        setMinimumSize(new Dimension(size, size));
        setMaximumSize(new Dimension(size, size));

        // create the fancy border
        Border raised = BorderFactory.createRaisedBevelBorder();
        Border lowered = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raised, lowered);
        setBorder(compound);

        // set default values
        myValue = (int) (Math.random() * 6) + 1;
        displayValue = myValue;
        setNumRolls(6);
        myTimer = new javax.swing.Timer(250, new Animator());
        addMouseListener(this);
    }

    /*****************************************************************
     * default constructor creates a die of size 100 X 100 pixels.
     *****************************************************************/
    public Dice() {
        this(100);
    }

    /*****************************************************************
    Checks if dice is currently held.
    @return true if the die is held. Otherise, false.
     *****************************************************************/
    public boolean isHeld() {
        return held;
    }

    /*****************************************************************
    Set the die face to blank.
     *****************************************************************/
    public void setBlank() {
        displayValue = 0;
        repaint();
    }

    /*****************************************************************
    Set whether the die is held or not.
    @param h true if die is currently held.
     *****************************************************************/
    public void setHeld(final boolean h) {
        held = h;
        if (held) {
            setBackground(heldColor);
        } else {
            setBackground(background);
        }
        repaint();    
    }

    /*****************************************************************
    Sets the frozen state.
    @param f true to freeze the die.
     *****************************************************************/
    public void setFreeze(final boolean f) {
        frozen = f;   
    }

    /*****************************************************************
    Is the die frozen?
    @return current frozen state
     *****************************************************************/
    public boolean isFrozen() {
        return frozen;   
    }

    /*****************************************************************
    Sets the color of the dots.
    @param c a Java Color object such as Color.red
     *****************************************************************/
    public void setForeground(final Color c) {
        super.setForeground(c);
    }

    /*****************************************************************
    Updates the image after obtaining a random value in the range 1 - 6.
     *****************************************************************/
    public void roll() {
        myValue = (int) (Math.random() * 6) + 1; 

        // start the animated roll
        myTimer.restart();

    }

    /*****************************************************************
    Set the delay in milliseconds between frames of the animation.
    Default value is 250.
    @param msec milliseconds to delay
     *****************************************************************/
    public void setDelay(final int msec) {
        if (msec > 0) {
            myTimer = new javax.swing.Timer(msec, new Animator());
        }
    }

    /*****************************************************************
    Set the number of rolls before stopping the animation. 
    Default value is 6.
    @param num number of rolls before stopping
     *****************************************************************/
    public void setNumRolls(final int num) {
        numRolls = 0;
        if (num > 0) {
            numRolls = num;
        }
    }

    /*****************************************************************
    gets the current value of the die (1 - 6).

    @return the current value of the die.
     *****************************************************************/
    public int getValue() {
        return myValue;
    }
    /**
     * Sets the value of the die.
     * @param v Value of the die to set.
     */
    public void setValue(final int v) {
    	myValue = v;
    }

    /*****************************************************************
    Display the current value of the die.  Called automatically
    after rolling.  There is no need to call this method directly.
    @param g the graphics context for the panel
     *****************************************************************/
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        // paint dots    
        switch (displayValue) {   
            case 1:
            g.fillOval(middle, middle, dotSize, dotSize); 
            break;
            case 2:
            g.fillOval(left, left, dotSize, dotSize); 
            g.fillOval(right, right, dotSize, dotSize); 
            break;
            case 3:
            g.fillOval(middle, left, dotSize, dotSize); 
            g.fillOval(middle, middle, dotSize, dotSize); 
            g.fillOval(middle, right, dotSize, dotSize); 
            break;
            case 5:     g.fillOval(middle, middle, dotSize, dotSize);
            // fall throught and paint four more dots
            case 4:
            g.fillOval(left, left, dotSize, dotSize); 
            g.fillOval(left, right, dotSize, dotSize); 
            g.fillOval(right, left, dotSize, dotSize); 
            g.fillOval(right, right, dotSize, dotSize); 
            break;
            case 6:
            g.fillOval(left, left, dotSize, dotSize); 
            g.fillOval(left, middle, dotSize, dotSize); 
            g.fillOval(left, right, dotSize, dotSize); 
            g.fillOval(right, left, dotSize, dotSize); 
            g.fillOval(right, middle, dotSize, dotSize); 
            g.fillOval(right, right, dotSize, dotSize); 
            break;
		default:
			break;
        }   

    }

    /*****************************************************************
    respond to the dice being clicked.

    @param e the mouse event.
     *****************************************************************/
    public void mouseClicked(MouseEvent e) {
        if (frozen) {
            return;
        }
        if (held) {
            held = false;
            setBackground(background);
        } else {
            held = true;
            setBackground(heldColor);
        }
        repaint();

    }
    /** Empty.
     * @param e mouse event */
    public void mousePressed(final MouseEvent e) { }
    /** Empty.
     * @param e mouse event */
    public void mouseReleased(final MouseEvent e) { }
    /** Empty.
     * @param e mouse event */
    public void mouseExited(final MouseEvent e) { }
    /** Empty.
     * @param e mouse event */
    public void mouseEntered(final MouseEvent e) { }

    /*****************************************************************
    allows dice to be compared if necessary.

    @param o compare the dice with this object
    @return -1 if dice is less than passed object
     *****************************************************************/
    public int compareTo(final Object o) {
        Dice d = (Dice) o;
        return getValue() - d.getValue();
    } 

    /******************************************************
    INNER class to roll the dice as an animation.
     ******************************************************/
    private class Animator implements ActionListener {
        /**
         * Counter.
         */
    	private int count = 0;
    	/**
    	 * actionListener for animation. 
    	 * @param e ActionEvent 
    	 */
        public void actionPerformed(ActionEvent e) {
            displayValue = (int) (Math.random() * 6) + 1; 
            repaint();
            count++;
            // Should we stop rolling?
            if (count == numRolls) {
                count = 0;
                myTimer.stop();
                displayValue = myValue;
                repaint();
            }
        }
    }    

}
