package edu.ou.cs2334.project5.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonogramModel {

	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;

	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// TODO: Implement deepCopy. 
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}

	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		// TODO: Initialize cellStates.
		// This is simple, and you should not ask about this on Discord.
//		cellStates = new CellState[numRows][numCols];
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);
		cellStates = initCellStates(getNumRows(), getNumCols());

		// TODO: Read in row clues.
		// This is simple, and you should not ask about this on Discord.
		
		readClueLines(reader, numRows);

		// TODO: Read in column clues.
		// This is simple, and you should not ask about this on Discord.
		
		readClueLines(reader, numCols);

		// Close reader
		reader.close();
	}

	public NonogramModel(String filename) throws IOException {
		// TODO: Fix this constructor
		// This is simple, and you should not ask about this on Discord.
		new NonogramModel(new File(filename));
	}

	// TODO: Add more TODOs

	/* Helper methods */

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
			temp[i] = Arrays.copyOf(temp[i], temp[i].length);
		}
		// You can do this in under 10 lines of code. If you ask the internet
		// "how do I do a deep copy of a 2d array in Java," be sure to cite
		// your source.
		// Note that if we used a 1-dimensional array to store our arrays,
		// we could simply use Arrays.copyOf directly without this helper
		// method.
		// Do not ask about this on Discord. You can do this on your own. :)
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

	public int getNumRows() {
		return cellStates[rowIdx][];

	}

	public int getNumCols() {
		return colClues.length;
	}

	public CellState getCellState(int rowIdx, int colIdx) {
		return cellStates[rowIdx][colIdx];
	}

	public boolean getCellStateBoolean(int rowIdx, int colIdx) {
		return CellState.toBoolean(cellStates[rowIdx][colIdx]);
	}

	boolean setCellState(int rowIdx, int colIdx, CellState state) {

	}

	public int[][] getRowClues() {
		return deepCopy(rowClues);
	}

	public int[][] getColClues() {
		return deepCopy(colClues);
	}

	public int[] getRowClue(int rowIdx) {
		return rowClues[rowIdx];
	}

	public int[] getColClue(int colIdx) {
		return colClues[colIdx];
	}

	boolean isRowSolved(int rowIdx) {

	}

	boolean isColSolved(int colIdx) {

	}

	boolean isSolved() {

	}

	void resetCells() {

	}

	static List<Integer> project() {
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

	static int[] projectCellStatesRow(int rowIdx) {

	}

	static int[] projectCellStatesCol(int colIdx) {

	}

}
