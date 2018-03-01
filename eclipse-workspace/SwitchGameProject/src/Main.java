import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.Node; 
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.control.Label; 

import java.util.Arrays;

import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color; 
 
public class Main extends Application{
	
	int game_board[][] = new int[8][8];
	Button[][] gameBoardNodes = new Button[8][8]; 
	Rules rules = new Rules(); 
	GridPane board = new GridPane(); 
	boolean isLegal = false; 
	int blackScore = 0, whiteScore = 0;
	Label whiteScoreLabel = new Label();
	Label blackScoreLabel = new Label();
	
	//Image reversiSquare = new Image(getClass().getResourceAsStream("reversiSquare.jpg"), 50, 50, false, false);
	public static void main(String args[]) {
		
		launch(args);
	}
	public void start(Stage primaryStage) {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int column, row; 
				column = j;
				row = i; 
				game_board[i][j] = 0; 
				gameBoardNodes[i][j] = new Button();
		//		gameBoardNodes[i][j].setGraphic(new ImageView(reversiSquare));
				gameBoardNodes[i][j].setPrefHeight(64);
				gameBoardNodes[i][j].setPrefWidth(64);
				board.add(gameBoardNodes[i][j], j, i);
				
				gameBoardNodes[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(rules.isMoveLegal(row, column))
						{	
							System.out.println(Arrays.deepToString(game_board).replace("], ", "]\n"));
							System.out.println();
							game_board = rules.placeTile(row, column);
							System.out.println(Arrays.deepToString(game_board).replace("], ", "]\n"));
							System.out.println();
							for(int k = 0; k < 8; k++)
							{
								int row1;
								int column1; 
								for(int l = 0; l < 8; l++) {
									if(game_board[k][l] == 1) {
										board.add(new Circle(32, Color.BLACK), l, k);
									}
									else if(game_board[k][l] == 2) {
										board.add(new Circle(32, Color.WHITE), l, k);
									}
								}
							}
						}
						else {
							System.out.println("NOT A LEGAL MOVE!");
						}
						blackScoreLabel.setText("Black Score:" + String.valueOf(rules.getScore(1)));
						whiteScoreLabel.setText("White Score:" + String.valueOf(rules.getScore(2)));
						int possibleMoves = 0; 
						for(int i = 0; i < 7; i++) {
							for(int j = 0; j < 7; j++) {
								if(rules.isMoveLegal(i, j)) {
									possibleMoves++; 
								}
								
								if(possibleMoves == 0) {
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
		blackScoreLabel.setText("Black Score: 2");
		whiteScoreLabel.setText("White Score: 2");
		game_board = rules.start(); 
		board.add(new Circle(32, Color.WHITE), 3, 3);
		board.add(new Circle(32, Color.BLACK), 3, 4);
		board.add(new Circle(32, Color.BLACK), 4, 3);
		board.add(new Circle(32, Color.WHITE), 4, 4);
		board.setPadding(new Insets(0,0,0,0));
		Scene scene = new Scene(board, 700, 700);
		primaryStage.setTitle("Othello");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
