package algorithms;

import java.util.List;

import parser.Node;

/**
 * An AlgorithmStrategy is able to compute the similarities between two
 * lists of nodes
 *
 * @author Vaibhav Dave
 * @author Shail Shah
 */
public interface AlgorithmStrategy {
	/**
	 * Compute the similarity between two Node lists
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	public IResult computeSimilarity(List<Node> list1, List<Node> list2);

}
