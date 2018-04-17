package gameSet;
/**
 * class that houses the main function
 * @author Aaron Bager, Jessica Ricksgers, Cody Chinn, Joshua Stuart
 */

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label; 
import java.util.Arrays;
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.shape.Circle; 
 
public class OthelloMain extends Application {
	
	private int[][] gameBoard = new int[8][8];
	private Button[][] gameBoardNodes = new Button[8][8]; 
	private OthelloRules rules = new OthelloRules(); 
	private GridPane board = new GridPane(); 
	private boolean isLegal = false; 
	private int blackScore = 0, whiteScore = 0;
	private Label whiteScoreLabel = new Label();
	private Label blackScoreLabel = new Label();
	private Label turnLabel = new Label();

	
	public void start(final Stage primaryStage) {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int column, row; 
				column = j;
				row = i; 
				gameBoard[i][j] = 0; 
				gameBoardNodes[i][j] = new Button();
		//		gameBoardNodes[i][j].setGraphic(new ImageView(reversiSquare));
				gameBoardNodes[i][j].setPrefHeight(64);
				gameBoardNodes[i][j].setPrefWidth(64);
				board.add(gameBoardNodes[i][j], j, i);
				
				gameBoardNodes[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent event) {
						if (rules.isMoveLegal(row, column))
						{	
							System.out.println(Arrays.deepToString(gameBoard).replace("], ", "]\n"));
							System.out.println();
							gameBoard = rules.placeTile(row, column);
							System.out.println(Arrays.deepToString(gameBoard).replace("], ", "]\n"));
							System.out.println();
							for (int k = 0; k < 8; k++) {
								int row1;
								int column1; 
								for (int l = 0; l < 8; l++) {
									if (gameBoard[k][l] == 1) {	
										board.add(new Circle(32, Color.BLACK), l, k); }
									else if (gameBoard[k][l] == 2) {
										board.add(new Circle(32, Color.WHITE), l, k);
									}
								}
							}
						}
						else {
							System.out.println("NOT A LEGAL MOVE!");
						}
						if (rules.getTurn() == 1) { 
							turnLabel.setText("Black's turn!"); }
						else {
							turnLabel.setText("White's turn!");
						}
						blackScoreLabel.setText("Black Score:" + String.valueOf(rules.getScore(1)));
						whiteScoreLabel.setText("White Score:" + String.valueOf(rules.getScore(2)));
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
