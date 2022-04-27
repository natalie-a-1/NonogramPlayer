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
		this.getStylesheets().add(STYLE_CLASS);
	}

	/**
	 * This method loops through a 2D array to find the longest array (length wise)
	 * within the array.
	 * 
	 * @param numClues the 2D array to be searched
	 * @return the length of the longest array
	 */
	public int getLength(int[][] numClues) {
		int[] temp = null;
		for (int i = 0; i < numClues.length; i++) {
			if (numClues[i].length > temp.length) {
				temp = numClues[i];
			}
		}
		return temp.length;
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

		this.setLeft(leftCluesView);
		this.setTop(topCluesView);
		this.setCenter(cellGridView);

		initBottomHBox();
		this.setBottom(bottomHBox);
	}

	/**
	 * This method initializes the HBox along with the load and reset buttons.
	 * 
	 */
	public void initBottomHBox() {
		bottomHBox = new HBox();
		loadButton = new Button();
		resetButton = new Button();
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
		// TODO SET ROW STATES
	}

	/**
	 * This method sets the state of Clues in a given column.
	 * 
	 * @param colIdx the column to be set
	 * @param solved the state to be set
	 */
	public void setColClueState(int colIdx, boolean solved) {
		// TODO SET COL STATES
	}

	/**
	 * This method sets the state of the whole puzzle.
	 * 
	 * @param solved the state to be set to
	 */
	public void setPuzzleState(boolean solved) {
		// TODO
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
		return alert;
	}
}
