package algorithms;

import java.util.Set;

/**
 * 
 * An object of this interface represents the a result obtained after running
 * an algorithm strategy on two files
 * The object should be able to return percent plagiarism between two files 
 * and a Set of similarity snippets
 * 
 * @author Vaibhav Dave
 */
public interface IResult {

	/**
	 * @return the percentage of similarities between two lists of nodes
	 */
	double getPercentage();


	/**
	 * This function deduplicates the snippets captured in the list and returns
	 * a set of similar snippets from the two codes
	 * 
	 * @return a set of similarity snippets.
	 */
	Set<SimilaritySnippet> generateSnippet();
}
