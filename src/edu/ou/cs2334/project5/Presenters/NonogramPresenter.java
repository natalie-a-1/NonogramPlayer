package edu.ou.cs2334.project5.Presenters;

import java.io.File;

import java.io.IOException;
import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * This class implements the interface Openable to create a NonogramPresenter.
 * NonogramPresenter combines components from NonogramModel and NonogramView to
 * make a visual and functional Nonogram.
 * 
 * @author Natalie Hill
 *
 */
public class NonogramPresenter implements Openable {
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";

	/**
	 * This is the constructor for a NonogramPresenter.
	 * 
	 * @param cellLength the length of each cell
	 * @throws IOException thrown if the constructor is unable to open the file
	 */
	public NonogramPresenter(int cellLength) throws IOException {
		this.cellLength = cellLength;
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
		initializePresenter();
	}

	private void initializePresenter() {
		initializeView();
		bindCellViews();
		synchronize();
		configureButtons();
	}

	/**
	 * This method initializes the view object and sets the window to the correct
	 * size.
	 */
	public void initializeView() {
		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
	}

	/**
	 * This method binds each cell in CellView to a button that handles mouse click
	 * events.
	 */
	public void bindCellViews() {
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				final int Frow = row;
				final int Fcol = col;
				view.getCellView(row, col).setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						if (arg0.getButton().compareTo(MouseButton.SECONDARY) == 0) {
							handleRightClick(Frow, Fcol);
						} else if (arg0.getButton().compareTo(MouseButton.PRIMARY) == 0) {
							handleLeftClick(Frow, Fcol);
						}

					}

				});
			}
		}
	}

	/**
	 * This method handles the left click events.
	 * 
	 * @param rowIdx the index of the row to be searched
	 * @param colIdx the index of the column to be searched
	 */
	public void handleLeftClick(int rowIdx, int colIdx) {
		if (model.getCellState(rowIdx, colIdx) == CellState.FILLED) {
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		} else if (model.getCellState(rowIdx, colIdx) == CellState.EMPTY
				|| model.getCellState(rowIdx, colIdx) == CellState.MARKED) {
			updateCellState(rowIdx, colIdx, CellState.FILLED);
		}
	}

	/**
	 * This method handles the right click events.
	 * 
	 * @param rowIdx the index of the row to be searched
	 * @param colIdx the index of the column to be searched
	 */
	public void handleRightClick(int rowIdx, int colIdx) {
		if (model.getCellState(rowIdx, colIdx) == CellState.MARKED) {
			updateCellState(rowIdx, colIdx, CellState.EMPTY);
		} else if (model.getCellState(rowIdx, colIdx) == CellState.EMPTY
				|| model.getCellState(rowIdx, colIdx) == CellState.FILLED) {
			updateCellState(rowIdx, colIdx, CellState.MARKED);
		}
	}

	/**
	 * This method updates the state of the cell provided.
	 * 
	 * @param rowIdx the index of the row to be searched
	 * @param colIdx the index of the column to be searched
	 * @param state  the state the cell should be changed to
	 */
	public void updateCellState(int rowIdx, int colIdx, CellState state) {
		if (model.setCellState(rowIdx, colIdx, state)) {
			model.setCellState(rowIdx, colIdx, state);
			view.setCellState(rowIdx, colIdx, state);
			view.setRowClueState(rowIdx, model.isRowSolved(rowIdx));
			view.setColClueState(colIdx, model.isColSolved(colIdx));

			view.setPuzzleState(model.isSolved());
			if (model.isSolved()) {
				processVictory();
			}
		}
	}

	/**
	 * This method synchronizes the view and model together.
	 */
	public void synchronize() {
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				view.setCellState(row, col, model.getCellState(row, col));
			}
		}
		for (int row = 0; row < model.getNumRows(); row++) {
			view.setRowClueState(row, model.isRowSolved(row));
		}
		for (int col = 0; col < model.getNumCols(); col++) {
			view.setColClueState(col, model.isColSolved(col));
		}
		view.setPuzzleState(model.isSolved());
		// view.setPuzzleState(true);

		if (model.isSolved()) {
			processVictory();
		}
	}

	/**
	 * This method clears all marks and displays the victory alert from the
	 * NonogramView class.
	 */
	public void processVictory() {
		removeCellViewMarks();
		view.getVictoryAlert();
	}

	/**
	 * This method changes any cell with a marked state to an empty state.
	 */
	public void removeCellViewMarks() {
		for (int rows = 0; rows < model.getNumRows(); rows++) {
			for (int cols = 0; cols < model.getNumCols(); cols++) {
				if (model.getCellState(rows, cols) == CellState.MARKED) {
					view.setCellState(rows, cols, CellState.EMPTY);
				}
			}
		}
	}

	/**
	 * This method sets the actions for the load and reset buttons.
	 */
	public void configureButtons() {
		FileChooser filechooser = new FileChooser();
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), filechooser, this));
		view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				resetPuzzle();
			}
		});
	}

	/**
	 * This method resets the NonogramModel and NonogramView.
	 */
	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}

	/**
	 * This method retrieves the window from NonogramView.
	 * 
	 * @return the window from NonogramView
	 */
	public Window getWindow() {
		try {
			return getPane().getScene().getWindow();
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * This method returns the view, because it is the Pane.
	 * 
	 * @return the Pane
	 */
	public Pane getPane() {
		return view;
	}

	/**
	 * This method opens the file.
	 * 
	 * @param file the file to be opened
	 * @throws IOException thrown if the method can not open the file.
	 */
	@Override
	public void open(File file) throws IOException {
		model = new NonogramModel(file);
		this.initializePresenter();
	}

}
