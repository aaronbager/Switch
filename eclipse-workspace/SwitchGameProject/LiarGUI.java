package gameset;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * 
 * @author Joshua Stuart
 * @version 2.0
 * Runs the GUI part of Liars Dice
 */
public class LiarGUI extends JFrame implements ActionListener {

	/** visual representation of the dice. */
	private Dice d1 = new Dice();
	/** visual representation of the dice. */
	private Dice d2 = new Dice();
	/** visual representation of the dice. */
	private Dice d3 = new Dice();
	/** visual representation of the dice. */
	private Dice d4 = new Dice();
	/** visual representation of the dice. */
	private Dice d5 = new Dice();
	/** ArrayList holding dice objects. */
	private ArrayList<Dice> dieList = new ArrayList<Dice>();
	/** ArrayList holding opponents dice objects. */
	private ArrayList<Dice> opponentDieList = new ArrayList<Dice>();
	/** Variable that indicates whether the game has started or not. */
	private int start = 0;
	/** active number of dice. */
	private int activeValue, activeNumDice, p1handSize;
	/** Button to allow user to interact with game. */
	private JButton newRound, bull, bid;
	/** Label displaying important information about game. */
	private JLabel round, player, computer;
	/** Class that handles the logic of the game. */
	private Rules game;
	/** Player class. */
	private Player p = new Player();
	/** Player class. */
	private Player c1 = new Player();
	/** menu items. */
	private JMenuBar menus;
	/** JMenu to hold menu items. */
	private JMenu fileMenu;
	/** menu item that lets player quit. */
	private JMenuItem quitItem;
	/** play menu item. */
	private JMenuItem playItem;
	/** Menu item that lets player restart game. */
	private JMenuItem restartItem;
	/**
	 * Class to run buttons.
	 * @param e ActionEvent variable
	 */
	public void actionPerformed(
			ActionEvent e) {
		JComponent buttonPressed = (JComponent) e.getSource();
		dieList.add(d1);
		dieList.add(d2);
		dieList.add(d3);
		dieList.add(d4);
		dieList.add(d5);
		opponentDieList.add(d1);
		opponentDieList.add(d2);
		opponentDieList.add(d3);
		opponentDieList.add(d4);
		opponentDieList.add(d5);
		// quit the game
		if (buttonPressed == quitItem) {
			System.exit(1);
		}

		// start a new game
		if (buttonPressed == bull) {
			game.bullShit(p, c1);
			// this is where the AI needs to be added
			newRound.setEnabled(true);
		}
		if (buttonPressed == newRound) {
			game.resetBid();
			ArrayList<Integer> hand = new ArrayList<Integer>();
			ArrayList<Integer> chand = new ArrayList<Integer>();
			newRound.setEnabled(false);
			hand.clear();
			chand.clear();
			// Setting the computers hand
			if (start == 0) {
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
				System.out.println("Your hand contains " 
				+ hand);
				start++;
			}
			if (start == 2) {
				switch (p.getHandSize()) {
				case 1:
					d1.roll();
					hand.add(d1.getValue());
					d2.setValue(0);
					hand.add(d2.getValue());
					d3.setValue(0);
					hand.add(d3.getValue());
					d4.setValue(0);
					hand.add(d4.getValue());
					d5.setValue(0);
					hand.add(d5.getValue());
					break;
				case 2:
					d1.roll();
					hand.add(d1.getValue());
					d2.roll();
					hand.add(d2.getValue());
					d3.setValue(0);
					hand.add(d3.getValue());
					d4.setValue(0);
					hand.add(d4.getValue());
					d5.setValue(0);
					hand.add(d5.getValue());
					break;
				case 3:
					d1.roll();
					hand.add(d1.getValue());
					d2.roll();
					hand.add(d2.getValue());
					d3.roll();
					hand.add(d3.getValue());
					d4.setValue(0);
					hand.add(d4.getValue());
					d5.setValue(0);
					hand.add(d5.getValue());

					break;
				case 4:
					d1.roll();
					hand.add(d1.getValue());
					d2.roll();
					hand.add(d2.getValue());
					d3.roll();
					hand.add(d3.getValue());
					d4.roll();
					hand.add(d4.getValue());
					d5.setValue(0);
					hand.add(d5.getValue());
					break;
				case 5:
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
					break;
				default:
					break;
				}
				switch (c1.getHandSize()) {
				case 1:
					d1.roll();
					chand.add(d1.getValue());
					break;
				case 2:
					d1.roll();
					chand.add(d1.getValue());
					d2.roll();
					chand.add(d2.getValue());
					break;
				case 3:
					d1.roll();
					chand.add(d1.getValue());
					d2.roll();
					chand.add(d2.getValue());
					d3.roll();
					chand.add(d3.getValue());
					break;
				case 4:
					d1.roll();
					chand.add(d1.getValue());
					d2.roll();
					chand.add(d2.getValue());
					d3.roll();
					chand.add(d3.getValue());
					d4.roll();
					chand.add(d4.getValue());
					break;
				case 5:
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
					break;
				default:
					break;
				}
			}
			start = 2;
			String inputValue = JOptionPane.showInputDialog(
					"Please enter the number of pips");
			String numdie = JOptionPane.showInputDialog(
					"Please enter the number of dice");
			int numDice = Integer.parseInt(numdie);
			int value = Integer.parseInt(inputValue);
			activeValue = value;
			activeNumDice = numDice;
			game.newBid(activeValue, activeNumDice);
			game.compTurn(c1);
			newRound.setEnabled(true);
		}
		if (buttonPressed == bid) {
			String inputValue = JOptionPane.showInputDialog(
					"Please enter the number of pips");
			String numdie = JOptionPane.showInputDialog(
					"Please enter the number of dice");
			int numDice = Integer.parseInt(numdie);
			int value = Integer.parseInt(inputValue);
			activeValue = value;
			activeNumDice = numDice;
			game.newBid(activeValue, activeNumDice);
			game.compTurn(c1);
		}
	}
/**
 * Sets up Menus for GUI.
 */
	private void setupMenus() {
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
	 * GUI constructor.
	 ****************************************************************/
	public LiarGUI() {
		// create the game object as well as the GUI Frame
		game = new Rules();
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();
		d4 = new Dice();
		d5 = new Dice();
		// Player p = new Player();
		// FIX ME: set background color of the JPanel
		setBackground(Color.PINK);
		// Use a GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints panelPosition = new GridBagConstraints();

		// create the buttons
		newRound = new JButton("New Round");
		bull = new JButton("bull");
		bid = new JButton("bid");

		// FIX ME: register the listeners for the three buttons
		newRound.addActionListener(this);
		bull.addActionListener(this);
		bid.addActionListener(this);

		// place both dice in the middle row
		d1 = p.getDice(1);
		panelPosition.gridx = 0;
		panelPosition.gridy = 1;
		add(d1, panelPosition);

		d2 = p.getDice(2);
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
		round = new JLabel("Round: 0");
		player = new JLabel("Player Dice: " + p1handSize);
		computer = new JLabel("Computer Dice: " + c1.getHandSize());
		player.setForeground(Color.red);

		// place labels along the top
		panelPosition.gridx = 0;
		panelPosition.gridy = 0;
		add(player, panelPosition);
		panelPosition.gridx = 1;
		panelPosition.gridy = 0;
		add(round, panelPosition);
		panelPosition.gridx = 2;
		panelPosition.gridy = 0;
		add(computer, panelPosition);

		// place buttons along the bottom
		panelPosition.gridx = 0;
		panelPosition.gridy = 2;
		add(newRound, panelPosition);
		panelPosition.gridx = 1;
		panelPosition.gridy = 2;
		add(bull, panelPosition);

		// FIX ME: place computer button below second die
		panelPosition.gridx = 2;
		panelPosition.gridy = 2;
		add(bid, panelPosition);

		// set up file menus
		setupMenus();
	}
}
