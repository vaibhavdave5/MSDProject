package algorithms;

import java.util.Set;

/**
 * Classes implementing IResult are able to store %, common Nodes and Snippets.
 * They can be easily be retrieved by getters.
 * 
 * @author Shail Shah, Vaibhav Dave
 */
public interface IResult {

	/**
	 * @return the percentage of similarities between two lists of nodes
	 */
	public double getPercentage();


	/**
	 * This function deduplicates the snippets captured in the list and returns
	 * a set of similar snippets from the two codes
	 * 
	 * @return a set of similarity snippets.
	 */
	public Set<SimilaritySnippet> generateSnippet();
}
