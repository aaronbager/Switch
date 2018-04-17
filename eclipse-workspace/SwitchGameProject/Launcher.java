package gameSet;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.Button;
import javafx.scene.control.Label; 
import javax.swing.JFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*; 
/**
 * Used to hold the GUI when launching program.
 * @author Aaron Bager.
 * @version 2.0
 */
public class Launcher extends Application {
	/**
	 * Used to launch the GUI.
	 * @param primaryStage This is simply the stage variable for the GUI
	 */
	public void start(final Stage primaryStage) {
		Button launchButton = new Button("Launch Game");
		
		Label gameLabel = new Label("What game would you "
				+ "like to play?");
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
		primaryStage.setTitle(
				"Launch "
				+ "Game");
		primaryStage.setScene(scene);
		primaryStage.show(); 
		launchButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent event) {
				String choice = gameChoice.getSelectionModel().getSelectedItem().toString();
				Stage secondaryStage = new Stage();
				if (choice == "Othello") {
					OthelloMain othello = new OthelloMain();
					othello.start(secondaryStage);
				} else if (choice == "Minesweeper") {
					Board board = new Board();
				} else if (choice == "Liars Dice") {
					LiarGUI gui = new LiarGUI();
					gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					gui.setTitle("Liars Dice");
					gui.pack();
					gui.setVisible(true);
				} else if (choice == "Battleship") {
					System.out.println("BATTLESHIP!");
				}
			}
		});
	}
}
