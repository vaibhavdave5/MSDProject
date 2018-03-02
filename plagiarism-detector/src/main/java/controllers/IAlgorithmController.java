package controllers;

import java.io.File;

/**
 * The main controller of the application that can tell if two files are similar
 * @author Vaibhav Dave
 *
 */
public c
public interface IAlgorithmController {
		/**
		 * Initialize two files to be compared 
		 * @param file1 the first file
		 * @param file2 the second file 
		 */
	    void setFiles(File file1, File file2);
	    
	    /**
	     * Get the similarity between the two files 
	     * @return a number representing the similarity between two files
	     */
	    double getAns();	
}
