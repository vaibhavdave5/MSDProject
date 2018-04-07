package driver;

import java.io.File;

import algorithms.IResult;

/**
 * The classes implementing this interface provide methods to manipulate a file pair.
 * @author darshan.panse
 *
 */
public interface IFilePair {
	/**
	 * Get the first file
	 * @return the first file
	 */
	public File getFile1();
	
	/**
	 * Set the first file
	 * @param file1 the first file
	 */
	public void setFile1(File file1);
	
	/**
	 * Get the second file
	 * @return the second file
	 */
	public File getFile2();
	
	/**
	 * Set the second file
	 * @param file2 the second file
	 */
	public void setFile2(File file2);
	
	/**
	 * Get the snippet result that contains similarities of the two files
	 * @return the snippet result that contains the similarities of the two files
	 */
	public IResult getResult1();
	
	/**
	 * Set the snippet result that contains the similarities of the two files
	 * @param result the snippet result that contains the similarities of the two files
	 */
	public void setResult1(IResult result);
	
	/**
	 * Get the other result that contains similarities of the two files
	 * @return the other result that contains the similarities of the two files
	 */
	public IResult getResult2();
	
	/**
	 * Set the other result that contains the similarities of the two files
	 * @param result the other result that contains the similarities of the two files
	 */
	public void setResult2(IResult result);
}
