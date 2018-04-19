package gameset;
import java.util.ArrayList;

/**
 * 
 * @author Joshua Stuart
 * @version 2.0
 */
public class Rules {
	/** Player class. */
	private Player p1 = new Player();
	/** Player class. */
	private Player p2 = new Player();
	/** Player class. */
	private Player p3 = new Player();
	/** Player class. */
	private Player p4 = new Player();
	/** Holds dice class. */
	private Dice d1 = new Dice();
	/** Holds dice class. */
	private Dice d2 = new Dice();
	/** Holds dice class. */
	private Dice d3 = new Dice();
	/** Holds dice class. */
	private Dice d4 = new Dice();
	/** Holds dice class. */
	private Dice d5 = new Dice();
	/** .*/
	private int activeValue = 0;
	/** Number of active dice. */
	private int activenumDice = 0;
/**
 * Constructor, empty.
 */
	public Rules() {

	}

	/**
	 * This method is invoked at the start 
	 * of each round to change the hands of
	 * all players.
	 * 
	 */
	public void newRound() {

		ArrayList<Integer> hand2 = new ArrayList<Integer>();
		ArrayList<Integer> hand3 = new ArrayList<Integer>();
		ArrayList<Integer> hand4 = new ArrayList<Integer>();
		// giving player 2 a new hand
		d1.roll();
		hand2.add(d1.getValue());
		d2.roll();
		hand2.add(d2.getValue());
		d3.roll();
		hand2.add(d3.getValue());
		d4.roll();
		hand2.add(d4.getValue());
		d5.roll();
		hand2.add(d5.getValue());
		// c1.setHand(hand2);

		d1.roll();
		hand3.add(d1.getValue());
		d2.roll();
		hand3.add(d2.getValue());
		d3.roll();
		hand3.add(d3.getValue());
		d4.roll();
		hand3.add(d4.getValue());
		d5.roll();
		hand3.add(d5.getValue());
		// c2.setHand(hand3);

		d1.roll();
		hand4.add(d1.getValue());
		d2.roll();
		hand4.add(d2.getValue());
		d3.roll();
		hand4.add(d3.getValue());
		d4.roll();
		hand4.add(d4.getValue());
		d5.roll();
		hand4.add(d5.getValue());
		// c3.setHand(hand4);

		// resetting the bid
		activeValue = 0;
		activenumDice = 0;

	}
	/**This method is used to reset the active values 
	 * and number of dice in the current bid.
	 * this is used before the start of a new round.
	 * 
	 * 
	 */
	public void resetBid() {
		activeValue = 0;
		activenumDice = 0;
	}
	/** This method is used to update the active bid 
	 * by the players. 
	 * Insures their bids are legal.
	 * @param newValue new value for bid
	 * @param numDice number of dice
	 */
	public void newBid(final int newValue, final int numDice) {
		int temp1 = activeValue;
		while (activeValue == temp1) {
			if ((newValue > activeValue 
					|| numDice > activenumDice) 
					&& newValue < 7) {
				temp1++;
				activeValue = newValue;
				activenumDice = numDice;
				System.out.println(
				"The current bid: \n There are " 
				+ activenumDice 
				+ " Dice with the value " 
				+ activeValue);
			} else {
				System.out.println(
						"The Dice value "
						+ "or Number of "
						+ "dice is invalid");
				break;
			}
		}
	}
	/**This method is used to callout the player who cast the last bid.
	 * @param pl The last player
	 * @param pl2 The other player
	 * @return true or false boolean value
	 */
	public boolean bullShit(final Player pl, final Player pl2) {
		System.out.println("I Call BULLSHIT");
		ArrayList<Integer> allHands = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int count = 0;
		temp = p1.getHand();
		allHands.addAll(pl.getHand());
		allHands.addAll(pl2.getHand());
		System.out.println(allHands);
		for (Integer i : allHands) {
			if (i == activeValue) {
				count++;
			}

		}

		if (count >= activenumDice) {
			pl2.loseDice();
			activeValue = 0;
			activenumDice = 0;
			return true;
		} else {
			pl.loseDice();
			activeValue = 0;
			activenumDice = 0;
			return false;
		}

	}
	/**
	 * Handles computers turn.
	 * @param cpu player class holding computer
	 */
	public void compTurn(final Player cpu) {
		ArrayList<Integer> hand = new ArrayList<Integer>();
		hand = cpu.getHand();
		int count = 0;
		int countHigher = 0;
		System.out.println("CPU Turn");
		// Check for any dice that matches the active value
		for (int i = 0; i < hand.size(); i++) {
			if (activeValue == hand.get(i)) {
				count++;
			}
		}
		// if the computer has the same 
		// amount of dice or more than the active
		// value then place a new bid
		if (count >= activenumDice) {
			newBid(activeValue, count + 1);
			System.out.println("CPU has bid " 
			+ activeValue + " " + activenumDice);
			return;
		}
		for (int i = 0; i < hand.size(); i++) {
			// check how many values are exactly one under the bid
			if (hand.get(i) == activeValue - 1) {
				count++;
			}
			// check how many values are exactly one over the bid
			if (hand.get(i) == activeValue + 1) {
				countHigher++;
			}
		}
		if (count > countHigher) {
			if (count >= activenumDice - 1) {
				newBid(activeValue - 1, activenumDice - 1);
				return;
			}
		}
		if (count < countHigher) {
			if (countHigher >= activenumDice - 1) {
				if (activeValue < 6) {
					newBid(activeValue + 1, 
					 activenumDice - 1);
					return;
				}
			}
		}
		bullShit(cpu, p1);
	}

}
