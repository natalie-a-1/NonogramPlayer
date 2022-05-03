package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class extends BorderPane and creates a display of the Nonogram.
 * 
 * @author Natalie Hill
 *
 */
public class NonogramView extends BorderPane {

	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadButton;
	private Button resetButton;

	/**
	 * This is the constructor for NonogramView that uses a style sheet to format
	 * the layout of NonogramView.
	 */
	public NonogramView() {
		this.getStyleClass().add(STYLE_CLASS);
	}

	/**
	 * This method loops through a 2D array to find the longest array (length wise)
	 * within the array.
	 * 
	 * @param numClues the 2D array to be searched
	 * @return the length of the longest array
	 */
	public int getLength(int[][] numClues) {
		int temp = -1;
		for (int i = 0; i < numClues.length; i++) {
			if (numClues[i].length > temp) {
				temp = numClues[i].length;
			}
		}
		return temp;
	}

	/**
	 * This method initializes the NonogramView.
	 * 
	 * @param rowClues   2D array of number of row clues
	 * @param colClues   2D array of number column clues
	 * @param cellLength the length of each cell
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		leftCluesView = new LeftCluesView(rowClues, cellLength, getLength(rowClues));
		topCluesView = new TopCluesView(colClues, cellLength, getLength(colClues));
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		
		cellGridView.setAlignment(Pos.CENTER);

		this.setLeft(leftCluesView);
		this.setTop(topCluesView);
		this.setCenter(cellGridView);
		
		setAlignment(leftCluesView, Pos.BOTTOM_LEFT);
		setAlignment(topCluesView, Pos.BOTTOM_RIGHT);

		initBottomHBox();
		this.setBottom(bottomHBox);
	}

	/**
	 * This method initializes the HBox along with the load and reset buttons.
	 * 
	 */
	public void initBottomHBox() {
		bottomHBox = new HBox();
		loadButton = new Button("Load");
		resetButton = new Button("Reset");
		bottomHBox.getChildren().addAll(loadButton, resetButton);
		bottomHBox.setAlignment(Pos.CENTER);

	}

	/**
	 * This method retrieves the CellView at certain index.
	 * 
	 * @param rowIdx the row to be searched
	 * @param colIdx the column to be searched
	 * @return the CellView at a given index
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellGridView.getCellView(rowIdx, colIdx);
	}

	/**
	 * This method sets the CellState of a CellView at a given index.
	 * 
	 * @param rowIdx the row to be searched
	 * @param colIdx the column to be searched
	 * @param state  the state to be set to
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellGridView.setCellState(rowIdx, colIdx, state);
	}

	/**
	 * This method sets the state of Clues in a given row.
	 * 
	 * @param rowIdx the row to be set
	 * @param solved the state to be set to
	 */
	public void setRowClueState(int rowIdx, boolean solved) {
		leftCluesView.setState(rowIdx, solved);
	}

	/**
	 * This method sets the state of Clues in a given column.
	 * 
	 * @param colIdx the column to be set
	 * @param solved the state to be set
	 */
	public void setColClueState(int colIdx, boolean solved) {
		topCluesView.setState(colIdx, solved);
		
	}

	/**
	 * This method sets the state of the whole puzzle.
	 * 
	 * @param solved the state to be set to
	 */
	public void setPuzzleState(boolean solved) {
		if (solved) {
			this.getStyleClass().add(SOLVED_STYLE_CLASS);
		} else {
			getStyleClass().removeAll(SOLVED_STYLE_CLASS);
		}
	}

	/**
	 * This method returns an instance of the load button.
	 * 
	 * @return the load button
	 */
	public Button getLoadButton() {
		return loadButton;
	}

	/**
	 * This method returns an instance of the reset button.
	 * 
	 * @return the reset button
	 */
	public Button getResetButton() {
		return resetButton;
	}

	/**
	 * This method displays an alert that the user have won.
	 * 
	 * @return victory alert
	 */
	public Alert getVictoryAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Puzzle Solved");
		alert.setHeaderText("Congratulations!");
		alert.setContentText("You Win!");
		alert.show();
		return alert;
	}
}
