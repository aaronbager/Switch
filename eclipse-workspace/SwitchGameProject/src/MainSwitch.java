import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints; 
import javafx.scene.layout.GridPane;
import javafx.stage.Stage; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle; 
//import javafx.scene.input.MouseEvent; 
import javafx.scene.shape.Circle; 
import javafx.scene.control.Button; 
import javafx.scene.control.TextField; 

public class MainSwitch extends Application{
	final int BOARD_SIZE = 8; 
	final int SQUARES = 64;
	GridPane board = new GridPane(); 
	Cell[] gamePieces = new Cell[64];
	Rectangle[][] place = new Rectangle[8][8];
	int piecesUsed = 4;
	Button commitButton = new Button("Commit");
	TextField rowField = new TextField();
	TextField columnField = new TextField(); 
	int row, column; 
	reversi rules = new reversi(); 
	
	
	
	
	public static void main(String args[])
	{
		
		launch(args);
	}
	public void start(Stage primaryStage) {
		rules.resizeBoard(BOARD_SIZE);
		buildBoard(board);
		board = putSquaresOnBoard(board);
		board.setPadding(new Insets(15, 15, 15, 15));
		Scene scene = new Scene(board, 700, 700);
		primaryStage.setTitle("Reversi");
		primaryStage.setScene(scene);
		primaryStage.show();
		commitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				row = Integer.parseInt(rowField.getText()) - 1;
				column = Integer.parseInt(columnField.getText()) - 1;
				gamePieces[piecesUsed] = new Cell(row, column, 1);
				board.add(new Circle(32, gamePieces[piecesUsed].getColor()), gamePieces[piecesUsed].getRow(), gamePieces[piecesUsed].getColumn());
				rules.checkTiles(gamePieces[piecesUsed]);
			}
		});
		
	}
	private void buildBoard(GridPane board) {
		for(int i = 0; i < BOARD_SIZE; i++) {
			RowConstraints rc = new RowConstraints(); 
			rc.setMinHeight(SQUARES);
			rc.setMaxHeight(SQUARES);
			rc.setPrefHeight(SQUARES);
			rc.setValignment(VPos.CENTER);
			board.getRowConstraints().add(rc);
			
			ColumnConstraints cc = new ColumnConstraints();
			cc.setMinWidth(SQUARES);
			cc.setMaxWidth(SQUARES);
			cc.setPrefWidth(SQUARES);
			cc.setHalignment(HPos.CENTER);
			board.getColumnConstraints().add(cc);
			
		}
	}
	private GridPane putSquaresOnBoard(GridPane board) {
		Color[] sqColors = new Color[] {Color.GREEN, Color.DARKGREEN};
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				//Rectangle 
				board.add(new Rectangle(SQUARES, SQUARES,sqColors[(i+j)%2]),i,j);
				place[i][j] = new Rectangle(SQUARES,SQUARES,sqColors[(i+j)%2]); 
			}
		}
		//add initial pieces here
		gamePieces[0] = new Cell(4, 3, 0);
		gamePieces[1] = new Cell(4, 4, 1);
		gamePieces[2] = new Cell(3, 4, 0);
		gamePieces[3] = new Cell(3, 3, 1);
		board.add(new Circle(32, gamePieces[0].getColor()), gamePieces[0].getRow(), gamePieces[0].getColumn());
		board.add(new Circle(32, gamePieces[1].getColor()), gamePieces[1].getRow(), gamePieces[1].getColumn());
		board.add(new Circle(32, gamePieces[2].getColor()), gamePieces[2].getRow(), gamePieces[2].getColumn());
		board.add(new Circle(32, gamePieces[3].getColor()), gamePieces[3].getRow(), gamePieces[3].getColumn());
		rules.start();
		//rules.checkTiles(gamePieces[0]);
		//rules.checkTiles(gamePieces[1]);
		//rules.checkTiles(gamePieces[2]);
		//rules.checkTiles(gamePieces[3]);
		board.add(rowField, 9, 9);
		board.add(columnField, 9, 10);
		board.add(commitButton, 9, 11);
		
		
		
		return board;
	}
	private void placePiecesOnBoard(GridPane board){
		
	}
}
