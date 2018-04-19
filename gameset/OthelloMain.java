package gameset;


import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.geometry.Insets;
import javafx.scene.control.Label; 
import javafx.application.Application; 
import javafx.event.EventHandler; 
import javafx.scene.shape.Circle; 
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
/**
 * class that handles the GUI for othello.
 * @author Aaron Bager
 * @author Jessica Ricksgers
 * @author Cody Chinn
 * @author Joshua Stuart
 * @version 2.0
 */
public class OthelloMain extends Application {
	/**
	 * keeps track of the board for game logic purposes.
	 */
	private int[][] gameBoard = new int[8][8];
	/**
	 * gameboard nodes, allows the player to interact with game.
	 * 
	 * removed buttons and replaced with rectangles.
	 */
	private Rectangle[][] gameBoardSquares = new Rectangle[8][8];
	/**
	 * rules class that keeps track of game logic.
	 */
	private OthelloRules rules = new OthelloRules();
	/**
	 * GridPane to attach JavaFX elements to.
	 */
	private GridPane board = new GridPane(); 
	/**
	 * JavaFX label to display white score.
	 */
    private Label whiteScoreLabel = new Label();
    /**
     * JavaFX label to display black score. 
     */
	private Label blackScoreLabel = new Label();
	/**
	 * JavaFX label to show who's turn it is. 
	 */
	private Label turnLabel = new Label();

	/**
	 * Used to start the game.
	 * @param primaryStage Stage variable for JavaFX
	 */
	public void start(final Stage primaryStage) {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int column, row; 
				column = j;
				row = i; 
				gameBoard[i][j] = 0; 
				gameBoardSquares[i][j] = new Rectangle();
				gameBoardSquares[i][j].setHeight(64);
				gameBoardSquares[i][j].setWidth(64);
				gameBoardSquares[i][j].setFill(Color.GREEN);
				gameBoardSquares[i][j].setStroke(Color.BLACK);
				board.add(gameBoardSquares[i][j], j, i);
				gameBoardSquares[i][j].setOnMouseClicked(
						new EventHandler<MouseEvent>() {
					@Override
					public void handle(
							final MouseEvent arg0) {
						if (
							rules.isMoveLegal(row,
							column)) {	
					gameBoard = rules.placeTile(row,
							column);
					for (int k = 0;
							k < 8;
							k++) {
						int row1;
						int column1; 
						for (int l = 0;
							l < 8;
							l++) {
					if (gameBoard[k][l] == 1) {	
						board.add(new Circle(32,
								Color.BLACK), 
								l,
								k); 
							} else if (
					  gameBoard[k][l] == 2) {
					  board.add(new Circle(32, 
							  Color.WHITE), l, k);
							}
						}
							}
						} else {
							turnLabel.setText(
							"NOT A LEGAL MOVE!");
						}
						if (rules.getTurn() == 1) { 
							turnLabel.setText(
							"Black's turn!"); 
							} else {
							turnLabel.setText(
							"White's turn!");
						}
						blackScoreLabel.setText(
							"Black Score:" 
						+ 
						String.valueOf(
						rules.getScore(1)));
						whiteScoreLabel.setText(
						"White Score:" 
						+ String.valueOf(
						rules.getScore(2)));
						int possibleMoves = 0; 
						for (int i = 0; i < 7; i++) {
						for (int j = 0; j < 7; j++) {
						if (rules.isMoveLegal(i, j)) {
							possibleMoves++; 
								}
								
						if (possibleMoves == 0) {
					int winner = rules.declareWinner(); 
								}
							}
						}
						
					}
				});
			}
			
		}
		board.add(blackScoreLabel, 11, 11);
		board.add(whiteScoreLabel, 11, 12);
		board.add(turnLabel, 11, 13);
		blackScoreLabel.setText("Black Score: 2");
		whiteScoreLabel.setText("White Score: 2");
		turnLabel.setText("Black's Turn");
		gameBoard = rules.start(); 
		board.add(new Circle(32, Color.WHITE), 3, 3);
		board.add(new Circle(32, Color.BLACK), 3, 4);
		board.add(new Circle(32, Color.BLACK), 4, 3);
		board.add(new Circle(32, Color.WHITE), 4, 4);
		board.setPadding(new Insets(0, 0, 0, 0));
		Scene scene = new Scene(board, 700, 700);
		primaryStage.setTitle("Othello");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
