package edu.ou.cs2334.project5;
import java.io.IOException;
import edu.ou.cs2334.project5.Presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class runs the whole program/
 * 
 * @author Natalie Hill
 *
 */
public class Main extends Application {

	private static final int IDX_CELL_SIZE = 0;
	private static final int DEFAULT_CELL_SIZE = 30;

	/**
	 * The main method the whole program.
	 * 
	 * @param args Arguments to run the program
	 */

	/**
	 * This is the main method which takes in all arguments and runs the program. 
	 * 
	 * @param args the arguments of the whole program
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method is an extension of Application that runs the program.
	 */
	public void start(Stage primaryStage) throws IOException {
		int cellSize = 0;
		NonogramPresenter presenter;

		if (getParameters().getUnnamed().size() == 0) {
			cellSize = DEFAULT_CELL_SIZE;
		} else {
			cellSize = Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE));
		}

		presenter = new NonogramPresenter(cellSize);

		Scene scene = new Scene(presenter.getPane());

		primaryStage.setScene(scene);
		scene.getStylesheets().add("style.css");
		primaryStage.setTitle("Nonogram Maker");
		primaryStage.show();
	}
}
