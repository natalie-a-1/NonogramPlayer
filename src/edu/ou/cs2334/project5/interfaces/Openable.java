package edu.ou.cs2334.project5.interfaces;

import java.io.File;
import java.io.IOException;

public interface Openable {
	
	/**
	 * This is a method that opens files.
	 * 
	 * @param file	the file to be opened
	 * @throws IOException	thrown if the file can not be opened
	 */
	void open(File file) throws IOException;
}