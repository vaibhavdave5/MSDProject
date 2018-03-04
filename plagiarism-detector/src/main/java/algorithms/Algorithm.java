package algorithms;

import java.util.List;
import parser.Node;

/**
 * An Algorithm can be used to compute the similarity between two Node Lists
 * @author Vaibhav Dave, Shail Shah
 *
 */
@FunctionalInterface
public interface Algorithm {
	/**
	 * Compute the similarity between two Node lists
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	public double computeSimilarity(List<Node> list1, List<Node> list2);
}
