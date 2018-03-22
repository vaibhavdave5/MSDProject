package algorithms;

import parser.Node;

import java.util.Set;

/**
 * Classes implementing IResult are able to store %, common Nodes and Snippets.
 * They can be easily be retreived by getters.
 * @author Shail Shah
 */
public interface IResult {

	/**
	 * @return the percentage of similarities between two lists of nodes
	 */
	double getPercentage();

	/**
	 * @return the common nodes between two Node lists
	 */
	Node[] getCommonNodes();

	/**
	 * @return the set of similar snippets between two node lists
	 */
	Set<SimilaritySnippet> getSnippets();
}
