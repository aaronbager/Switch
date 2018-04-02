import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.Button;
import javafx.scene.control.Label; 
import java.util.Arrays;
import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*; 

public class launcher extends Application {
	public void start(final Stage primaryStage) {
		Button launchButton = new Button("Launch Game");
		
		Label gameLabel = new Label("What game would you like to play?");
		ObservableList<String> gameList =
				FXCollections.observableArrayList(
						"Othello",
						"Checkers",
						"Liars Dice",
						"Battleship",
						"Minesweeper",
						"Connect Four");
		ComboBox gameChoice = new ComboBox(gameList);
		GridPane grid = new GridPane(); 
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.add(gameLabel, 1, 1);
		grid.add(gameChoice, 3, 1);
		grid.add(launchButton, 2, 5);
		Scene scene = new Scene(grid);
		primaryStage.setTitle("Launch Game");
		primaryStage.setScene(scene);
		primaryStage.show(); 
		launchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent event) {
				String choice = gameChoice.getSelectionModel().getSelectedItem().toString();
				Stage secondaryStage = new Stage();
				if(choice == "Othello") {
					OthelloMain othello = new OthelloMain();
					othello.start(secondaryStage);
				}
				else if(choice == "Minesweeper") {
					Board board = new Board();
					board.setBoard(); 
				}
			}
		});
	}
}
