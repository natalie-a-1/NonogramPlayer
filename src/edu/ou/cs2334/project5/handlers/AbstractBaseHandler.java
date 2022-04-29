package edu.ou.cs2334.project5.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * This class handles opening the file and establishing a window for it.
 * 
 * @author Natalie Hill
 *
 */
	public abstract class AbstractBaseHandler {

		protected Window window;
		protected FileChooser fileChooser;

		protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
			this.window = window;
			this.fileChooser = fileChooser;
		}

	}
