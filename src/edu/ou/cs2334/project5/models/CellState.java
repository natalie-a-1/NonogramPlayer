package edu.ou.cs2334.project5.models;

/**
 * This class holds the different state a CellView can have.
 * 
 * @author Natalie Hill
 *
 */
public enum CellState {
	/**
	 * This represents an empty state.
	 */
	EMPTY, 
	/**
	 * This represents a filled state.
	 */
	FILLED, 
	/**
	 * This represents a marked state.
	 */
	MARKED;
	
	/**
	 * This method takes in a state and changes it to a boolean value.
	 * 
	 * @param state	the state the CellView is in
	 * @return	the state as a boolean value
	 */
	public static boolean toBoolean(CellState state) {
		boolean stateOf = false;
		stateOf = (state == FILLED);
		return stateOf;
	}
}
