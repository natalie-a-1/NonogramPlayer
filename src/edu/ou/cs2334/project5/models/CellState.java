package edu.ou.cs2334.project5.models;

public enum CellState {
	EMPTY, FILLED, MARKED;
	
	public boolean toBoolean(CellState state) {
		boolean stateOf = false;
		stateOf = (state == FILLED);
		return stateOf;
	}
}
