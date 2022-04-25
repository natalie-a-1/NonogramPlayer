package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * This class holds the rules of the entire game by storing rules and cell states. 
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
	private static CellState[][] cellStates;

	/**
	 * This is the constructor for the NonogramModel that takes in two 2D arrays.
	 * 
	 * @param rowClues	2D array of row clues
	 * @param colClues	2D array of column clues
	 */
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		this.rowClues = deepCopy(rowClues);
//		deepCopy(rowClues);
		this.colClues = deepCopy(colClues);
//		deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	/**
	 * This is a constructor for the NonogramModel that reads in information from a file.
	 * 
	 * @param file	the file to be read in
	 * @throws IOException	thrown if the file is unable to be opened
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

		// Close reader
		reader.close();
	}

	/**
	 * This a constructor for the NonogramModel that changes a String 
	 * into a file in order to read in information from the String.
	 * 
	 * @param filename	the name of the file to be converted
	 * @throws IOException	thrown if the file is unable to be opened
	 */
	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}

	// This is implemented for you
	private static CellState[][] initCellStates(int numRows, int numCols) {
		// Create a 2D array to store numRows * numCols elements
		CellState[][] cellStates = new CellState[numRows][numCols];

		// Set each element of the array to empty
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}

		// Return the result
		return cellStates;
	}

	// Rorick: stackoverflow
	private static int[][] deepCopy(int[][] array) {
		int[][] temp = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			temp[i] = Arrays.copyOf(array[i], array[i].length);
		}
		return temp;
	}

	// This method is implemented for you. You need to figure out how it is useful.
	private static int[][] readClueLines(BufferedReader reader, int numLines) throws IOException {
		// Create a new 2D array to store the clues
		int[][] clueLines = new int[numLines][];

		// Read in clues line-by-line and add them to the array
		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			// Read in a line
			String line = reader.readLine();

			// Split the line according to the delimiter character
			String[] tokens = line.split(DELIMITER);

			// Create new int array to store the clues in
			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}

			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}

		// Return the result
		return clueLines;
	}

	/**
	 * This retrieves the number of rows in the Nonogram.
	 * 
	 * @return	 the length of rowClues
	 */
	public int getNumRows() {
		return rowClues.length;

	}

	/**
	 * This retrieves the number of columns in the Nonogram. 
	 * 
	 * @return	the length of colClues
	 */
	public int getNumCols() {
		return colClues.length;
	}

	/**
	 * This retrieves the state of a given cell.
	 * 
	 * @param rowIdx	the index of the row in the Nonogram
	 * @param colIdx	the index of the column in the Nonogrma
	 * @return	the state of a cell at the row/column index
	 */
	public CellState getCellState(int rowIdx, int colIdx) {
		return cellStates[rowIdx][colIdx];
	}

	/**
	 * This changes the state of a given cell into a boolean value.
	 * 
	 * @param rowIdx	the index of the row in the Nonogram
	 * @param colIdx	the index of the column in the Nonogrma
	 * @return	the state of a cell at the row/column index as a boolean value
	 */
	public boolean getCellStateBoolean(int rowIdx, int colIdx) {
		return CellState.toBoolean(cellStates[rowIdx][colIdx]);
	}

	/**
	 * This changes the state of a cell at a given index in the Nonogram. 
	 * 
	 * @param rowIdx	the index of the row in the Nonogram
	 * @param colIdx	the index of the column in the Nonogrma
	 * @param state		the state the index should be changed to 
	 * @return	true if the index was changed or false if it was not
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state) {
		if(cellStates[rowIdx][colIdx] == null || isSolved()) {
			return false;
		} else {
			CellState.toBoolean(cellStates[rowIdx][colIdx]);
			return true;
		}
		
	}

	/**
	 * This retrieves a copy of rowClues. 
	 * 
	 * @return	a copy of rowClues
	 */
	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}

	/**
	 * This returns a copy of the colClues.
	 * 
	 * @return	a copy of colClues
	 */
	public int[][] getColClues() {
		return deepCopy(colClues);
	}

	/**
	 * This retrieves the clues from a given row.
	 * 
	 * @param rowIdx	the index of the row to be return
	 * @return	an array of rowClues
	 */
	public int[] getRowClue(int rowIdx) {
		int[][] temp = deepCopy(rowClues);
		return temp[rowIdx];
	}
	

	/**
	 * This retrieves the clues from a given column.
	 * 
	 * @param colIdx	the index of the column to be searched
	 * @return	an array of colClues
	 */
	public int[] getColClue(int colIdx) {
		int[][] temp = deepCopy(colClues);
		return temp[colIdx];
	}

	/**
	 * This tells the user if the row is solved.
	 * 
	 * @param rowIdx	the index of the row to be searched
	 * @return	true if the row is solved and false if the row is not solved
	 */
	public boolean isRowSolved(int rowIdx) {
		return (rowClues[rowIdx] == projectCellStatesRow(rowIdx));
	}

	/**
	 * This tells the user if the column is solved.
	 * 
	 * @param colIdx	the index of the column to be searched
	 * @return	true if the column is solved and false if the column is not solved
	 */
	public boolean isColSolved(int colIdx) {
		return (colClues[colIdx] == projectCellStatesCol(colIdx));

	}

	/**
	 * This method tells the user if the entire puzzle is solved.
	 * 
	 * @return	true if the puzzle is solved and false if the puzzle is not solved
	 */
	public boolean isSolved() {
		for (int row = 0; row < getNumRows(); row++) {
			int[] projectRow = projectCellStatesRow(row);
			for (int i = 0; i < projectRow.length; i++) {
				if (projectRow[i] == 0) {
					return false;
				}
			}
		}
		
		for (int col = 0; col < getNumCols(); col++) {
			int[] projectCol = projectCellStatesCol(col);
			for (int i = 0; i < projectCol.length; i++) {
				if (projectCol[i] == 0) {
					return false;
				}
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
	 * @return	a list of cellStates
	 */
	public List<Integer> project() {
		List<Integer> temp = new ArrayList<Integer>();
		int count = 0;
		for (int row = 0; row < getNumRows(); row++) {
			for(int col = 0; col < getNumCols(); col++)
			if (cellStates[row][col] == CellState.FILLED) {
				count++;
			} else if (cellStates[row][col] == CellState.EMPTY) {
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
	 * @param rowIdx	the index of the row to be projected
	 * @return	array of int values
	 */
	public static int[] projectCellStatesRow(int rowIdx) {
		int[] intValues = new int[cellStates[rowIdx].length];
		boolean[] booValues = new boolean[cellStates[rowIdx].length];
		for(int row = 0; row < cellStates[rowIdx].length; row++) {
			booValues[row] = CellState.toBoolean(cellStates[row][0]);
		}
		
		for (int row = 0; row < booValues.length; row++) {
			if (booValues[row]) {
				intValues[row] = 1;
			} else {
				intValues[row] = 0;
			}
		}
		return intValues;
	}

	/**
	 * This shows the cellState values in a given column.
	 * 
	 * @param colIdx	the index of the column to be projected
	 * @return	array of int values
	 */
	public static int[] projectCellStatesCol(int colIdx) {
		int[] intValues = new int[cellStates[colIdx].length];
		boolean[] booValues = new boolean[cellStates[colIdx].length];
		for(int col = 0; col < cellStates[colIdx].length; col++) {
			booValues[col] = CellState.toBoolean(cellStates[0][col]);
		}
		
		for (int col = 0; col < booValues.length; col++) {
			if (booValues[col]) {
				intValues[col] = 1;
			} else {
				intValues[col] = 0;
			}
		}
		return intValues;
	}

}
