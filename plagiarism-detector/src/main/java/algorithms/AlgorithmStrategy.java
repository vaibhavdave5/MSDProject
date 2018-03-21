package algorithms;

import java.util.List;

import parser.Node;

public interface AlgorithmStrategy {
	/**
	 * Compute the similarity between two Node lists
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	public Result computeSimilarity(List<Node> list1, List<Node> list2);

}
