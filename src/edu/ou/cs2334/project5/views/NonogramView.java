package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NonogramView extends BorderPane {

	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox buttonHBox;
	private Button loadButton;
	private Button resetButton;
	
	/**
	 * This is the constructor for NonogramView that uses a style sheet 
	 * to format the layout of NonogramView.
	 */
	public NonogramView() {
		this.getStylesheets().add(STYLE_CLASS);
	}
	
	/**
	 * This method loops through a 2D array to find the longest 
	 * array (length wise) within the array.
	 * 
	 * @param numClues	the 2D array to be searched
	 * @return	the length of the longest array
	 */
	public int getLength(int[][] numClues) {
		int[] temp = null;
		for (int i = 0; i < numClues.length; i++) {
			if(numClues[i].length > temp.length) {
				temp = numClues[i];
			}
		}
		return temp.length;
	}
	
	/**
	 * This method initializes the NonogramView.
	 * 
	 * @param rowClues	2D array of number of row clues
	 * @param colClues	2D array of number column clues
	 * @param cellLength	the length of each cell
	 */
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength) {
		leftCluesView = new LeftCluesView(rowClues, cellLength, getLength(rowClues));
		topCluesView = new TopCluesView(colClues, cellLength, getLength(colClues));
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		
		this.setLeft(leftCluesView);
		this.setTop(topCluesView);
		this.setCenter(cellGridView);
		
		buttonHBox = new HBox();
		loadButton = new Button();
		resetButton = new Button();
		buttonHBox.getChildren().addAll(loadButton, resetButton);
		this.setBottom(buttonHBox);
	}
}
