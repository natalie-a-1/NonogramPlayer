package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class holds the rules of the entire game by storing rules and cell
 * states.
 * 
 * @author Natalie Hill
 *
 */
public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;

	/**
	 * This is the constructor for the NonogramModel that takes in two 2D arrays.
	 * 
	 * @param rowClues 2D array of row clues
	 * @param colClues 2D array of column clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);
		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	/**
	 * This is a constructor for the NonogramModel that reads in information from a
	 * file.
	 * 
	 * @param file the file to be read in
	 * @throws IOException thrown if the file is unable to be opened
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		int[][] rows = readClueLines(reader, numRows);
		this.rowClues = deepCopy(rows);

		int[][] cols = readClueLines(reader, numCols);
		this.colClues = deepCopy(cols);

		cellStates = initCellStates(getNumRows(), getNumCols());

		reader.close();
	}

	/**
	 * This a constructor for the NonogramModel that changes a String into a file in
	 * order to read in information from the String.
	 * 
	 * @param filename the name of the file to be converted
	 * @throws IOException thrown if the file is unable to be opened
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}

	private static CellState[][] initCellStates(int numRows, int numCols) {
		CellState[][] cellStates = new CellState[numRows][numCols];

		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}

		return cellStates;
	}

	// Rorick: stackoverflow
	private int[][] deepCopy(int[][] array) {
		int[][] temp = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			temp[i] = Arrays.copyOf(array[i], array[i].length);
		}
		return temp;
	}

	//Implemented for you 
	private static int[][] readClueLines(BufferedReader reader, int numLines) throws IOException {

		int[][] clueLines = new int[numLines][];

		for (int lineNum = 0; lineNum < numLines; ++lineNum) {

			String line = reader.readLine();

			String[] tokens = line.split(DELIMITER);

			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}

			clueLines[lineNum] = clues;
		}

		// Return the result
		return clueLines;
	}

	/**
	 * This retrieves the number of rows in the Nonogram.
	 * 
	 * @return the length of rowClues
	 */
	public int getNumRows() {
		return rowClues.length;

	}

	/**
	 * This retrieves the number of columns in the Nonogram.
	 * 
	 * @return the length of colClues
	 */
	public int getNumCols() {
		return colClues.length;
	}

	/**
	 * This retrieves the state of a given cell.
	 * 
	 * @param rowIdx the index of the row in the Nonogram
	 * @param colIdx the index of the column in the Nonogrma
	 * @return the state of a cell at the row/column index
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		return cellStates[rowIdx][colIdx];
	}

	/**
	 * This changes the state of a given cell into a boolean value.
	 * 
	 * @param rowIdx the index of the row in the Nonogram
	 * @param colIdx the index of the column in the Nonogrma
	 * @return the state of a cell at the row/column index as a boolean value
	 */
	public boolean getCellStateBoolean(int rowIdx, int colIdx) {
		return CellState.toBoolean(getCellState(rowIdx, colIdx));
	}

	/**
	 * This changes the state of a cell at a given index in the Nonogram. I received
	 * help from Keon Moradi on this method.
	 * 
	 * @param rowIdx the index of the row in the Nonogram
	 * @param colIdx the index of the column in the Nonogrma
	 * @param state  the state the index should be changed to
	 * @return true if the index was changed or false if it was not
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		boolean output = false;
		if (state == null || isSolved()) {
			return false;
		}
		output = !(getCellState(rowIdx, colIdx).equals(state));
		cellStates[rowIdx][colIdx] = state;
		return output;
	}

	/**
	 * This retrieves a copy of rowClues.
	 * 
	 * @return a copy of rowClues
	 */
	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}

	/**
	 * This returns a copy of the colClues.
	 * 
	 * @return a copy of colClues
	 */
	public int[][] getColClues() {
		return deepCopy(colClues);
	}

	/**
	 * This retrieves the clues from a given row.
	 * 
	 * @param rowIdx the index of the row to be return
	 * @return an array of rowClues
	 */
	public int[] getRowClue(int rowIdx) {
		int[][] temp = deepCopy(rowClues);
		return temp[rowIdx];
	}

	/**
	 * This retrieves the clues from a given column.
	 * 
	 * @param colIdx the index of the column to be searched
	 * @return an array of colClues
	 */
	public int[] getColClue(int colIdx) {
		int[][] temp = deepCopy(colClues);
		return temp[colIdx];
	}

	/**
	 * This tells the user if the row is solved.
	 * 
	 * @param rowIdx the index of the row to be searched
	 * @return true if the row is solved and false if the row is not solved
	 */
	public boolean isRowSolved(int rowIdx) {
		return Arrays.equals(getRowClue(rowIdx), projectCellStatesRow(rowIdx));
	}

	/**
	 * This tells the user if the column is solved.
	 * 
	 * @param colIdx the index of the column to be searched
	 * @return true if the column is solved and false if the column is not solved
	 */
	public boolean isColSolved(int colIdx) {
		return Arrays.equals(getColClue(colIdx), projectCellStatesCol(colIdx));

	}

	/**
	 * This method tells the user if the entire puzzle is solved.
	 * 
	 * @return true if the puzzle is solved and false if the puzzle is not solved
	 */
	public boolean isSolved() {
		for (int i = 0; i < getNumRows(); i++) {
			if (isRowSolved(i) == false) {
				return false;
			}
		}
		for (int i = 0; i < getNumCols(); i++) {
			if (isColSolved(i) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method sets all cells to an empty state.
	 * 
	 */
	public void resetCells() {
		for (int row = 0; row < getNumRows(); row++) {
			for (int col = 0; col < getNumCols(); col++) {
				cellStates[row][col] = CellState.EMPTY;
			}
		}
	}

	/**
	 * This method projects a list of cellStates.
	 * 
	 * @param cells boolean array
	 * @return a list of cellStates
	 */
	public static List<Integer> project(boolean[] cells) {
		List<Integer> temp = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == true) {
				count++;
			} else if (cells[i] == false) {
				if (count != 0) {
					temp.add(count);
				}
				count = 0;
			}
		}
		if (count > 0) {
			temp.add(count);
		}

		if (temp.size() == 0) {
			temp.add(0);
		}
		return temp;
	}

	/**
	 * This shows the cellState values in a given row.
	 * 
	 * @param rowIdx the index of the row to be projected
	 * @return array of int values
	 */
	public int[] projectCellStatesRow(int rowIdx) {
		boolean[] booValues = new boolean[getNumCols()];
		for (int col = 0; col < getNumCols(); col++) {
			booValues[col] = CellState.toBoolean(getCellState(rowIdx, col));
		}

		List<Integer> temp = project(booValues);

		return temp.stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * This shows the cellState values in a given column.
	 * 
	 * @param colIdx the index of the column to be projected
	 * @return array of int values
	 */
	public int[] projectCellStatesCol(int colIdx) {
		boolean[] booValues = new boolean[getNumRows()];
		for (int row = 0; row < booValues.length; row++) {
			booValues[row] = CellState.toBoolean(getCellState(row, colIdx));
		}

		List<Integer> temp = project(booValues);

		return temp.stream().mapToInt(Integer::intValue).toArray();
	}

}
