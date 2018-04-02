
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
public class LiarGUI extends JFrame implements ActionListener{

    /** visual representation of the dice */
	 Dice d1 = new Dice();
     Dice d2 = new Dice();
     Dice d3 = new Dice();
     Dice d4 = new Dice();
     Dice d5 = new Dice();
    int activeValue, activeNumDice,p1handSize;
    /** buttons and labels */
    JButton newRound, Bull, Bid;
    JLabel round, player, computer;
    Rules game;
    Player p = new Player();
    Player c1 = new Player();
//    Player c2 = new Player();
//    Player c3 = new Player();
    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem playItem;
    JMenuItem restartItem;      

   
    public void actionPerformed (ActionEvent e){
    JComponent buttonPressed = (JComponent) e.getSource();

    // quit the game
    if (buttonPressed == quitItem){
        System.exit(1);
    }

    // start a new game    
    if (buttonPressed == Bull){
        game.bullShit(p, c1);// this is where the AI needs to be added
        newRound.setEnabled(true);
    }
    if (buttonPressed == newRound){
    	ArrayList<Integer> hand = new ArrayList<Integer>();
    	ArrayList<Integer> chand = new ArrayList<Integer>();
    	newRound.setEnabled(false);
    	//Setting the computers hand
    	d1.roll();
    	chand.add(d1.getValue());
    	d2.roll();
    	chand.add(d2.getValue());
    	d3.roll();
    	chand.add(d3.getValue());
    	d4.roll();
    	chand.add(d4.getValue());
    	d5.roll();
    	chand.add(d5.getValue());
    	c1.setHand(chand);
    	
    	d1.roll();
    	hand.add(d1.getValue());
    	d2.roll();
    	hand.add(d2.getValue());
    	d3.roll();
    	hand.add(d3.getValue());
    	d4.roll();
    	hand.add(d4.getValue());
    	d5.roll();
    	hand.add(d5.getValue());
    	p.setHand(hand);
    	System.out.println("Your hand contains " +hand);
    	
    	
    	String inputValue = JOptionPane.showInputDialog("Please enter the number of pips");
    	String numdie = JOptionPane.showInputDialog("Please enter the number of dice");
    	int numDice= Integer.parseInt(numdie);
    	int value = Integer.parseInt(inputValue);
    	activeValue = value;
    	activeNumDice = numDice;
    	game.newBid(activeValue, activeNumDice);
    	game.compTurn(c1);
    	newRound.setEnabled(true);
    }
    if(buttonPressed == Bid){
    	String inputValue = JOptionPane.showInputDialog("Please enter the number of pips");
    	String numdie = JOptionPane.showInputDialog("Please enter the number of dice");
    	int numDice= Integer.parseInt(numdie);
    	int value = Integer.parseInt(inputValue);
    	activeValue = value;
    	activeNumDice = numDice;
    	game.newBid(activeValue, activeNumDice);
    	game.compTurn(c1);
    }
    }
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        playItem = new JMenuItem("Auto Play");    
        restartItem = new JMenuItem("Restart");
        quitItem.addActionListener(this);
        restartItem.addActionListener(this);
        playItem.addActionListener(this);
        fileMenu.add(restartItem);
        fileMenu.add(playItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        menus.add(fileMenu);  
        setJMenuBar(menus);
}
    /****************************************************************
    Create all elements and display within the GUI
     ****************************************************************/    
    public static void main(String args[]){
        LiarGUI gui = new LiarGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Liars Dice");
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
    GUI constructor
     ****************************************************************/        
    public LiarGUI(){ 
        // create the game object as well as the GUI Frame   
        game = new Rules();
        d1 = new Dice();
        d2 = new Dice();
        d3 = new Dice();
        d4 = new Dice();
        d5 = new Dice();
//        Player p = new Player();
        //FIX ME: set background color of the JPanel
        setBackground(Color.PINK);
        // Use a GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints panelPosition = new GridBagConstraints();

        // create the buttons 
        newRound = new JButton("New Round");
        Bull = new JButton("Bull");
        Bid = new JButton("Bid");
        

        // FIX ME: register the listeners for the three buttons
        newRound.addActionListener(this);
        Bull.addActionListener(this);
        Bid.addActionListener(this);

        // place both dice in the middle row
        d1 = p.getDice(1);
        panelPosition.gridx = 0;
        panelPosition.gridy = 1;
        add(d1, panelPosition);  

        d2 =p.getDice(2);
        panelPosition.gridx = 2;
        panelPosition.gridy = 1;
        add(d2, panelPosition);  
        
        d3 = p.getDice(3);
        panelPosition.gridx = 3;
        panelPosition.gridy = 1;
        add(d3, panelPosition);
        
        d4 = p.getDice(4);
        panelPosition.gridx = 4;
        panelPosition.gridy = 1;
        add(d4, panelPosition);   

        d5 = p.getDice(5);
        panelPosition.gridx = 5;
        panelPosition.gridy = 1;
        add(d5, panelPosition);   


        // create the labels
        round = new JLabel ("Round: 0");
        player = new JLabel ("Player Dice: " + p1handSize);  
        computer = new JLabel ("Computer Dice: " + c1.getHandSize());
        player.setForeground(Color.red);

        // place labels along the top
        panelPosition.gridx = 0;
        panelPosition.gridy = 0;
        add(player, panelPosition);
        panelPosition.gridx = 1;
        panelPosition.gridy = 0;    
        add(round,panelPosition);
        panelPosition.gridx = 2;
        panelPosition.gridy = 0;
        add(computer, panelPosition);

        // place buttons along the bottom
        panelPosition.gridx = 0;
        panelPosition.gridy = 2;
        add(newRound,panelPosition);
        panelPosition.gridx = 1;
        panelPosition.gridy = 2;
        add(Bull, panelPosition); 

        // FIX ME: place computer button below second die
        panelPosition.gridx = 2;
        panelPosition.gridy = 2;
        add(Bid,panelPosition);

   
        // set up file menus
        setupMenus();
    } 
} 
