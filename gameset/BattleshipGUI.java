package gameset;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.shape.Rectangle;
import java.util.Random; 
/**
 * 
 * @author Aaron Bager
 * Battleship Class
 * 
 */
public class BattleshipGUI {
	/**
 * keeps track of player board for game logic purposes.
 */
private int[][] playerGameBoard = new int[10][10];
/**
 * Keeps track of computer board for game logic purposes. 
 */
private int[][] enemyGameBoard = new int[10][10];
/**
 * Array of buttons to allow player to interact with game. 
 */
private Button[][] gameBoardButtons = new Button[10][10]; 
/**
 * GridPane for adding JavaFX elements.
 */
private GridPane board = new GridPane();
/**
 * JavaFX label to keep track of player's score and display it back.
 */
private Label playerScoreLabel = new Label();
/**
 * JavaFX label to keep track of computer's score and display it back.
 */
private Label computerScoreLabel = new Label();
/**
 * JavaFX label to inform the player of game information. 
 */
private Label statusLabel = new Label(); 
/**
 * Array of rectangles, displaying information about opponents pieces.
 */
private Rectangle[][] opponentNode = new Rectangle[10][10]; 
/**
 * JavaFX rectangles to display on player's side.
 */
private Rectangle[][] pieces = new Rectangle[10][10];
/**
 * JavaFX rectangles used to display on opponent's side. 
 */
private Rectangle[] opponentPieces = new Rectangle[100];
/**
 * Keeps track of game phase.
 * 
 * Phase 1: Placing ships
 * Phase 2: Attack phase
 * Phase 3: Game over, winner has been declared
 */
private int gamePhase = 1; 
/**
 * Keeps track of how many of the player's ships are left on the board.
 */
private int playerShipsLeft = 0; 
/**
 * Keeps track of how many of the computer's 
 * ships are left on the board. 
 */
private int enemyShipsLeft = 0; 
/**
 * Keeps track of how many turns have passed. 
 */
private int turnCount = 0; 
/**
 * Used to start the game.
 * @param primaryStage Stage variable for JavaFX
 */
public void start(final Stage primaryStage) {

	Random randomSpot = new Random();
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			int column, row; 
			column = j;
			row = i; 
			playerGameBoard[i][j] = 0; 
			enemyGameBoard[i][j] = 0; 
			opponentNode[i][j] = new Rectangle(); 
			opponentNode[i][j].setFill(Color.GREY);
			opponentNode[i][j].setStroke(Color.BLACK);
			opponentNode[i][j].setHeight(30);
			opponentNode[i][j].setWidth(30);
			board.add(opponentNode[i][j], j, i);
			gameBoardButtons[i][j] = new Button();
			gameBoardButtons[i][j].setPrefHeight(30);
			gameBoardButtons[i][j].setPrefWidth(30);
			board.add(gameBoardButtons[i][j], j, i + 100);
			gameBoardButtons[i][j].setOnAction(
				new EventHandler<ActionEvent>() {
				@Override
				public void handle(
					final ActionEvent event) {
					if (
						gamePhase == 1 
						&& playerShipsLeft != 10
			&& playerGameBoard[row][column] == 1) {
						statusLabel.setText(
							"You can't place "
+ "a ship on "
+ "top of another ship!");
} else if (
		gamePhase == 1 && playerShipsLeft != 10) {
	pieces[row][column] = new Rectangle(); 
	pieces[row][column].setHeight(10);
	pieces[row][column].setWidth(10);
	pieces[row][column].setFill(Color.GREY);
	playerGameBoard[row][column] = 1;
	board.add(pieces[row][column], column, row + 100);
	board.setHalignment(pieces[row][column], HPos.CENTER);
	playerShipsLeft++;
	playerScoreLabel.setText("Player Score: " 
	+ Integer.toString(playerShipsLeft));
statusLabel.setText("Pieces to place left: " 
	+ Integer.toString(10 - playerShipsLeft));
int ranRow = randomSpot.nextInt(9);
int ranCol = randomSpot.nextInt(9);
while (enemyGameBoard[ranRow][ranCol] == 1) {
	ranRow = randomSpot.nextInt(9);
	ranCol = randomSpot.nextInt(9);
} 
enemyGameBoard[ranRow][ranCol] = 1;
enemyShipsLeft++;
if (playerShipsLeft == 10) {
	statusLabel.setText("Alright, pieces are placed. You go first!");
		gamePhase = 2; 
	}
} else if ((enemyGameBoard[row][column] == 2 
		|| enemyGameBoard[row][column] == 3) 
		&& gamePhase == 2) {
	statusLabel.setText("You might want to try "
			+ "attacking a spot you "
			+ "haven't shot at yet");
} else if (gamePhase == 2 && enemyGameBoard[row][column] == 1) {
	if (enemyGameBoard[row][column] == 1) {
		statusLabel.setText("Hit! Good job!");
enemyGameBoard[row][column] = 3;
opponentPieces[turnCount] = new Rectangle(); 
opponentPieces[turnCount].setHeight(10);
opponentPieces[turnCount].setWidth(10);
opponentPieces[turnCount].setFill(Color.RED);
board.add(opponentPieces[turnCount], column, row); 
board.setHalignment(opponentPieces[turnCount], HPos.CENTER);
turnCount++; 
enemyShipsLeft--; 
int ranRow = randomSpot.nextInt(9);
int ranCol = randomSpot.nextInt(9);
computerScoreLabel.setText("Opponent Score: " 
+ Integer.toString(enemyShipsLeft));
if (enemyShipsLeft == 0) {	
	gamePhase = 3; 
	statusLabel.setText("GAME OVER! YOU WIN!");
} else if (playerGameBoard[ranRow][ranCol] == 1) {
		pieces[ranRow][ranCol].setFill(Color.RED);
		playerGameBoard[ranRow][ranCol] = 2;
		playerShipsLeft--; 
		playerScoreLabel.setText("Player Score: " 
		+ Integer.toString(playerShipsLeft));
if (playerShipsLeft == 0) {
	statusLabel.setText("GAME OVER! You lose.");
				gamePhase = 3; 
			}
		}
	}
} else if (gamePhase == 2 && enemyGameBoard[row][column] == 0) {
	statusLabel.setText("Miss! Try again!");
enemyGameBoard[row][column] = 2;
opponentPieces[turnCount] = new Rectangle();
opponentPieces[turnCount].setHeight(10);
opponentPieces[turnCount].setWidth(10);
opponentPieces[turnCount].setFill(Color.WHITE);
board.add(opponentPieces[turnCount], column, row);
board.setHalignment(opponentPieces[turnCount], HPos.CENTER);
turnCount++; 
int ranRow = randomSpot.nextInt(9);
int ranCol = randomSpot.nextInt(9);
if (playerGameBoard[ranRow][ranCol] == 1) {
	pieces[ranRow][ranCol].setFill(Color.RED);
	playerGameBoard[ranRow][ranCol] = 2;
	playerShipsLeft--; 
	playerScoreLabel.setText("Player Score: " 
	+ Integer.toString(playerShipsLeft));
if (playerShipsLeft == 0) {
	statusLabel.setText("GAME OVER! You lose.");
							gamePhase = 3; 
						}
					}
				}
			}
		});
	}
}
statusLabel.setText("Pieces to place left: 10");
playerScoreLabel.setText("Player Score: 0");
computerScoreLabel.setText("Opponent Score: 10");
board.add(playerScoreLabel, 101, 102);
board.add(computerScoreLabel, 101, 103);
board.add(statusLabel, 101, 104);
Scene scene = new Scene(board, 700, 700);
primaryStage.setTitle("Battleship");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
