package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane{

	private final static String STYLE_CLASS = "nongram-view";
	private CellView[][]  cellViews;
	
	public CellGridView(int numRows, int numCols, int cellLength) {
		cellViews = new CellView[numRows][numCols];
		initCells(numRows, numCols, cellLength);
		
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				this.add(cellViews[row][col], row, col);
			}
		}
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add(STYLE_CLASS);
		
	}
	
	public void initCells(int numRows, int numCols, int cellLength) {
		this.getChildren().clear();
		
	}
	
	public CellView getCellView(int rowIdx, int colIdx) {
		return cellViews[rowIdx][colIdx];
	}
	
	//was CellState in parameter
	public void setCellView(int rowIdx, int colIdx, CellState state) {
		cellViews[rowIdx][colIdx].setState(state);
	}
}
