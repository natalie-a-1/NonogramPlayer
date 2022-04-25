package edu.ou.cs2334.project5.Presenters;

import java.io.IOException;

import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;

public class NonogramPresenter {
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;
	private static String DEFAULT_PUZZLE = "puzzles/space-invader.txt";
	
	public NonogramPresenter(int cellLength) throws IOException {
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
	}
	
	private void initializePresenter() {
		
	}
	
	
}
