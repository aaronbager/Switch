import java.lang.reflect.Array;
import java.util.ArrayList;
public class Player {
	int numDice=5;
	//number of dice play still has available
	int dieValue;
	//dice value player has guessed
	int numGuess;
	//number of dice player has guessed
	ArrayList<Integer> hand = new ArrayList<Integer>();
	//array to hold the dice values of the player
	Dice d1 = new Dice();
	Dice d2 = new Dice();
	Dice d3 = new Dice();
	Dice d4 = new Dice();
	Dice d5 = new Dice();
	
	boolean playerTurn ; 
	/*This method is used to return if its the users turn or not
	 * 
	 * 
	 * @return boolean value
	 */
	public boolean getTurn(){
		return playerTurn;
	}
	/*This method is used to set the players turn
	 * no return values.
	 * 
	 * 
	 */
	public void setPlayerTurn(boolean b){
		playerTurn = b;
	}
	/*This method is used to return the number of dice the player has left
	 * 
	 * 
	 * @ returns integer 0-5
	 */
	public int getNumDice(){
		return numDice;
	}
	/*This method is used to set the number of dice the player has.
	 * 
	 * 
	 * 
	 */
	public void setNumDice(int numdie){
		numDice = numdie;
	}
	public Player(){
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();
		d4 = new Dice();
		d5 = new Dice();
		ArrayList<Integer> hand = new ArrayList<Integer>();
		hand.add(d1.getValue());
		hand.add(d2.getValue());
		hand.add(d3.getValue());
		hand.add(d4.getValue());
		hand.add(d5.getValue());
	}
	/*This method is used to return the value of specific Die
	 * 
	 * 
	 * @return die value 1-6
	 */
	public int getdieValue(){
		return dieValue;
	}
	/*This method is used to return number of dice player has left.
	 * 
	 * @returns number ofdicee 0-5
	 * 
	 */
	public int getnumDice(){
		return numDice;
	}
	/*This Method returns the players Dice 
	 * 
	 */
	public Dice getDice(int dice){
		Dice temp = d1;
		switch(dice){
		case 1: temp = d1;
		break;
		case 2: temp = d2;
		break;
		case 3: temp = d3;
		break;
		case 4: temp = d4;
		break;
		case 5: temp = d5;
		break;
		}
		return temp;
	}
	/*This method is used to make a new player in the game
	 * the player will have 5 dice
	 * This method is only used at the start of a new game.
	 * 
	 */
	
	/*this method should fill the players hand for the start of a round.
	 * 
	 */
	public void roll(){
		
		switch(numDice){
		case 1: d1.roll();;
		break;
		case 2: d1.roll();
				d2.roll();
		break;
		case 3: d1.roll();
				d2.roll();
				d3.roll();
		break;
		case 4: d1.roll();
				d2.roll();
				d3.roll();
				d4.roll();
		break;
		case 5: d1.roll();
				d2.roll();
				d3.roll();
				d4.roll();
				d5.roll();
		break;
		}
		}
		
	/*This method is used to return all dice values in players hand
	 * 
	 * @ return ArrayList<Integer>
	 * 
	 */
	public ArrayList<Integer> getHand(){
		return hand;
	}/*This method is used to set the player hand from rolling dice in the GUI
	 * 
	 * 
	 * 
	 */
	
	public void setHand(ArrayList<Integer> newHand){
		hand = newHand;
	}
	/*This method is used to return the number of items in the players hand
	 * 
	 * @return integer
	 * 
	 */
	public int getHandSize(){
		return hand.size();
	}
	/*This method is used for showing players their hand on a text field.
	 * 
	 */
	public void printHand(){
			System.out.println(hand);
	}
	/*This method is for the loss of dice by a player.
	 * 
	 */
	public void loseDice(){
		switch(numDice){
		case 1: hand.remove(0);
				System.out.println("You got wrecked");
				d1.setFreeze(true);
				d2.setFreeze(true);
				d3.setFreeze(true);
				d4.setFreeze(true);
				d5.setFreeze(true);
				numDice--;
		break;
		case 2: hand.remove(1);
				d2.setFreeze(true);
				d3.setFreeze(true);
				d4.setFreeze(true);
				d5.setFreeze(true);
				numDice--;
		break;
		case 3: hand.remove(2);
				d3.setFreeze(true);
				d4.setFreeze(true);
				d5.setFreeze(true);
				numDice--;
		break;
		case 4: hand.remove(3);
				d4.setFreeze(true);
				d5.setFreeze(true);
				numDice--;
		break;
		case 5: hand.remove(4);
				d5.setFreeze(true);
				
				numDice--;
		break;
		}
		System.out.println("You have "+ hand.size()+ " dice left.");
	}
}
