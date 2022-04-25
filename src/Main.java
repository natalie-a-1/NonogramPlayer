import java.util.List;
import edu.ou.cs2334.project5.Presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static final int IDX_CELL_SIZE = 0;
	private static final int DEFAULT_CELL_SIZE = 30;

	/**
	 * The main method the whole program.
	 * 
	 * @param args Arguments to run the program
	 */
	public static void main(String[] args) {
		launch(args);

	}
	
	/**
	 * This method uses all components in the program to display it.
	 * 
	 * @param primaryStage the stage to display the program
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		List<String> parameters = getParameters().getUnnamed();
		NonogramPresenter nonogramPresenter = new NonogramPresenter(IDX_CELL_SIZE);
		Scene scene = new Scene(nonogramPresenter.getPane());
		primaryStage.setScene(scene);
		scene.getStylesheets().add("style.css");
		primaryStage.setTitle("My first java app!"); // CHANGE NAME
		primaryStage.setResizable(false);
		primaryStage.show();

	}

}
