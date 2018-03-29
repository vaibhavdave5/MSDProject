package driver;

import algorithms.IResult;

import java.io.File;

/**
 * A FilePair is a pair containing two files and their similarities
 * @author darshan.panse
 */
public class FilePair implements IFilePair {
	private File file1;
	private File file2;
	private IResult result;

	/**
	 * Constructor for making a new FilePair
	 * @param file1 the first file
	 * @param file2 the second file
	 */
	public FilePair(File file1, File file2) {
		this.file1 = file1;
		this.file2 = file2;
	}

	/**
	 * Get the first file
	 * @return the first file
	 */
	public File getFile1() {
		return file1;
	}

	/**
	 * Set the first file
	 * @param file1 the first file
	 */
	public void setFile1(File file1) {
		this.file1 = file1;
	}

	/**
	 * Get the second file
	 * @return the second file
	 */
	public File getFile2() {
		return file2;
	}

	/**
	 * Set the second file
	 * @param file2 the second file
	 */
	public void setFile2(File file2) {
		this.file2 = file2;
	}

	/**
	 * Get the result that contains similarities of the two files
	 * @return the result that contains the similarities of the two files
	 */
	public IResult getResult() {
		return result;
	}


	/**
	 * Set the result that contains the similarities of the two files
	 * @param result the result that contains the similarities of the two files
	 */
	public void setResult(IResult result) {
		this.result = result;
	}
	
}
