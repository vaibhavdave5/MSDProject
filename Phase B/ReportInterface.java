package src;

import java.nio.file.Path;
import java.util.List;

/**
* 
* An object of ReportInterface gets the Similar snippets of the submissions of
* Student pairs and has functions to save the report and email to the authorities.
* 
* @author  team-107
* @version 1.0
* @since   2018-02-10 
*/


public interface ReportInterface {
	
	
	/**
	 * Saves the report at a specified path
	 * @param path
	 * 
	 */
	public void saveReport(Path path);
	
	/**
	 * 
	 * It is an click listener for email report
	 * 
	 */
	public void emailReport();
	
	/**
	 * Given a pair of students return an object that describes the specific snippets of similarity
	 * @param studentPair
	 * @return a list of SimilarSnippet 
	 */
	List <SimilarSnippet> setSimilarSnippets(StudentPair studentPair);
	
	
	/**
	 * return an object that describes the specific snippets of similarity
	 * 
	 * @return a list of SimilarSnippet 
	 */
	List <SimilarSnippet> getSimilarSnippets();
	

}
