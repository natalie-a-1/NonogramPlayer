package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

/**
 * This class extends GridPane to create its own GridPane of cells.
 * 
 * @author Natalie Hill
 *
 */
public class CellGridView extends GridPane {

	private final static String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;

	/**
	 * This method creates a CellGridView object.
	 * 
	 * @param numRows    the number of rows in the CellGrid
	 * @param numCols    the number of columns in the CellGrid
	 * @param cellLength the length of each cell
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		cellViews = new CellView[numRows][numCols];
		initCells(numRows, numCols, cellLength);
		this.getStyleClass().add(STYLE_CLASS);
	}

	/**
	 * This initializes each cell in the CellGridView object
	 * 
	 * @param numRows    the number of rows in the CellGrid
	 * @param numCols    the number of columns in the CellGrid
	 * @param cellLength the length of each cell
	 */
	public void initCells(int numRows, int numCols, int cellLength) {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				CellView tempView = new CellView(cellLength);
				cellViews[row][col] = tempView;
				this.add(tempView, col, row);
			}
		}
	}

	/**
	 * This method retrieves a CellView at certain index.
	 * 
	 * @param rowIdx the row index to be searched
	 * @param colIdx the column index to be searched
	 * @return the CellView at the given index
	 */
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellViews[rowIdx][colIdx];
	}

	/**
	 * This method sets the state of a given CellView in cellView.
	 * 
	 * @param rowIdx the row index to be searched
	 * @param colIdx the column index to be searched
	 * @param state  the state of the CellView to be changed to
	 */
	public void setCellState(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
}
