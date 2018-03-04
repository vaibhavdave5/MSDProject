package algorithms;

import java.util.List;

import parser.Node;

/**
 * An instance of LCS is able to compute the similarity between two Node Lists
 * 
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class LCSAlgorithm implements Algorithm {

	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1  a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public double computeSimilarity(List<Node> list1, List<Node> list2) {
		int totalSize = list1.size() + list2.size();
		if(totalSize == 0) throw new IllegalArgumentException("Both lists are empty.");
		return 2 * computeLCS(list1, list2) / totalSize;
	}

	/**
	 * Compute the LCS for two given Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	private double computeLCS(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] map = new int[size1 + 1][size2 + 1];

		for (int i = 1; i <= size1; i++)
			for (int j = 1; j <= size2; j++)
				if (list1.get(i - 1).equals(list2.get(j - 1)))
					map[i][j] = map[i - 1][j - 1] + 1;
				else
					map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);

		return (double) map[size1][size2];
	}
}
