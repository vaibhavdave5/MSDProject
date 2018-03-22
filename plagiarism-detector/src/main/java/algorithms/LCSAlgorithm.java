package algorithms;

import java.util.List;
import parser.Node;

/**
 * An instance of LCS is able to compute the similarity between two Node Lists
 * 
 * @author Vaibhav Dave
 * @author Shail Shah
 *
 */
public class LCSAlgorithm implements AlgorithmStrategy {
	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public Result computeSimilarity(List<Node> list1, List<Node> list2) {
		int totalSize = list1.size() + list2.size();
		if (totalSize == 0)
			throw new IllegalArgumentException("Both lists are empty.");

		Node[] commonNodes = getCommonNodes(list1, list2);
		return new Result(((2.0 * commonNodes.length) / totalSize), commonNodes);
	}

	/**
	 * Backtrack the result of the lcs and find out the actual
	 * longest common subsequence
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return Node[] of lcs
	 */
	public Node[] getCommonNodes(List<Node> list1, List<Node> list2) {
		int[][] map = getMap(list1, list2);

		int i = list1.size()-1;
		int j = list2.size()-1;
		int index = map[i][j];
		Node[] lcs = new Node[index + 1];
		lcs[index] = null; 						// Set the terminating character
		while (i > 0 && j > 0) {
			if (list1.get(i - 1).equals(list2.get(i - 1))) {
				lcs[index - 1] = list1.get(i - 1);
				i--;
				j--;
				index--;
			}
			else if (map[i - 1][j] > map[i][j - 1])
				i--;
			else
				j--;
		}

		return lcs;
	}

	/**
	 * Compute the LCS for two given Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a 2D array that contains the intermediate LCS values
	 */
	private int[][] getMap(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] map = new int[size1 + 1][size2 + 1];

		for (int i = 1; i <= size1; i++)
			for (int j = 1; j <= size2; j++)
				if (list1.get(i - 1).equals(list2.get(j - 1))) {
					map[i][j] = map[i - 1][j - 1] + 1;
				} else
					map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);

		return map;
	}
}
