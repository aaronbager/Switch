import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.Node; 
import javafx.scene.control.Button; 
import javafx.geometry.Insets; 
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color; 
public class Main extends Application{
	
	Cell[][] gameBoard = new Cell[8][8];
	int game_board[][] = new int[8][8];
	Button[][] gameBoardNodes = new Button[8][8];
	//reversi rules = new reversi(); 
	Rules rules = new Rules(); 
	GridPane board = new GridPane(); 
	boolean isLegal = false; 
	int value;
	
	public static void main(String args[]) {
		
		//Stage primaryStage = new Stage(); 
		//start(primaryStage);
		launch(args);
	}
	public void start(Stage primaryStage) {
		
		//rules.resizeBoard(8);
//		game_board = rules.start(); //was throwing error, probably due to the fact array is not initialized yet
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int column, row; 
				column = i;
				row = j; 
				game_board[i][j] = 0; 
				gameBoardNodes[i][j] = new Button();
				gameBoardNodes[i][j].setPrefHeight(64);
				gameBoardNodes[i][j].setPrefWidth(64);
				//gameBoard[i][j] = new Cell(i,j,0);
				board.add(gameBoardNodes[i][j], i, j);
				
				gameBoardNodes[i][j].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						
						if(rules.isMoveLegal(row, column, 1))
						{
							game_board = rules.placeTile(row, column);
				//			rules.legalMove(row, column); 
			//				rules.placeTile(row, column);
			//				gameBoard[row][column].setRow(row);
					//		gameBoard[row][column].setColumn(column);
						//	gameBoard[row][column].setValue(rules.passTurn()); 
			//				board.add(new Circle(32, Color.WHITE), column, row);
				//			gameBoard = rules.checkTiles(gameBoard[row][column]);
							for(int k = 0; k < 8; k++)
							{
								int row1;
								int column1; 
								for(int l = 0; l < 8; l++) {
			//						row1 = gameBoard[k][l].getRow();
			//						column1 = gameBoard[k][l].getColumn();
									if(rules.getTurn() == 1)
									{
										board.add(new Circle(32, Color.BLACK), column, row);
									}
									else if(rules.getTurn() == 2)
									{
										board.add(new Circle(32, Color.WHITE), column, row);
									}
									
									if(game_board[k][l] == 1) {
										board.add(new Circle(32, Color.BLACK), l, k);
									}
									else if(game_board[k][l] == 2) {
										board.add(new Circle(32, Color.BLACK), l, k);
									}
								}
							}
						}
						else {
							System.out.println("NOT A LEGAL MOVE!");
						}
						
//						rules.legalMove(row, column); 
//						rules.placeTile(row, column);
//						 
//						 board.add(new Circle(32, Color.WHITE), column, row);
					}
				});
			}
			
		}
		game_board = rules.start(); 
//		gameBoard[3][3].setValue(2);
//		gameBoard[3][4].setValue(1);
//		gameBoard[4][3].setValue(1);
//		gameBoard[4][4].setValue(2);
		board.add(new Circle(32, Color.WHITE), 3, 3);
		board.add(new Circle(32, Color.BLACK), 3, 4);
		board.add(new Circle(32, Color.BLACK), 4, 3);
		board.add(new Circle(32, Color.WHITE), 4, 4);
		board.setPadding(new Insets(0,0,0,0));
		Scene scene = new Scene(board, 700, 700);
		primaryStage.setTitle("Othello");
		primaryStage.setScene(scene);
		primaryStage.show();
		//rules.start(); 
	}
}
