package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;

public class NonogramView {

	private static final String STYLE_CLASS = "nonogram-view";
	private static final String SOLVED_STYLE_CLASS = "nongram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHBox;
	private Button loadButton;
	private Button resetButton;
}
