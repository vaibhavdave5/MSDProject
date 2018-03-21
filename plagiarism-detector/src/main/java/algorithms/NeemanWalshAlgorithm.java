package algorithms;

import java.util.ArrayList;
import java.util.List;
import parser.Node;

/**
 * An object of NeemanWalshAlgorithm is able to compute the similarity
 * between two Node lists, using the Neeman-Walsh Similarity Algorithm.
 * 
 * @author Vaibhav Dave
 * @since 02/28/2018
 */

public class NeemanWalshAlgorithm implements AlgorithmStrategy {

	private int[][] track;
	private int[][] c;
	private int[][] s;

	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public Result computeSimilarity(List<Node> list1, List<Node> list2) {
		if (list1.isEmpty() || list2.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Node[] ans = fetchOpticalAlignment(list1, list2);
		return new Result(2.0 * ans.length / (list1.size() + list2.size()), ans);
	}

	/**
	 * 
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return length of the common nodes after optical alignment.
	 */
	private Node[] fetchOpticalAlignment(List<Node> list1, List<Node> list2) {
		Node[] ans;
		int m = list1.size();
		int n = list2.size();
		s = initializeSubstitutionMatrix(list1, list2);
		track = new int[m + 1][n + 1];
		c = new int[m + 1][n + 1];
		populateC(list1, list2);
		ans = counterDiagonal(list1, list2, track);

		return ans;
	}

	/**
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return length of the common nodes after optical alignment.
	 * 
	 */
	private int[][] initializeSubstitutionMatrix(List<Node> list1, List<Node> list2) {
		int m = list1.size();
		int n = list2.size();
		int[][] temp = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = list1.get(i).equals(list2.get(j)) ? 1 : -1;
			}
		}
		return temp;
	}

	/**
	 * 
	 * @param list1
	 * @param list2
	 * @param track
	 * @return
	 */
	private Node[] counterDiagonal(List<Node> list1, List<Node> list2, int[][] track) {
		int m = list1.size();
		int n = list2.size();
		List<Node> list = new ArrayList<>();
		int i = m;
		int j = n;

		while (i != 0 && j != 0) {
			if (track[i][j] == 1) {
				i--;
				j--;
				list.add(list1.get(i));
			} else if (track[i][j] == 2) {
				j--;
			} else {
				i--;
			}
		}
		return convertListToArray(list);
	}

	/**
	 * Populate the c[]
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 */
	private void populateC(List<Node> list1, List<Node> list2) {
		int m = list1.size();
		int n = list2.size();
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++)
				setC(i, j);
	}

	/**
	 * Compute and set the correct value of c[i][j] and track[i][j]
	 * @param i the row's index
	 * @param j the column's index
	 */
	private void setC(int i, int j) {
		int scorediag = c[i - 1][j - 1] + s[i][j];
		int scoreup = c[i - 1][j] - 1;
		int scoreleft = c[i][j - 1] - 1;

		c[i][j] = Math.max(Math.max(scorediag, scoreup), scoreleft);

		track[i][j] = (c[i][j] == scorediag) ? 1 :
				(c[i][j] == scoreleft) ? 2 : 3;
	}

	/**
	 * Convert a list of Nodes to an array of Nodes
	 * @param nodes a list of Nodes
	 * @return and array of Nodes
	 */
	private Node[] convertListToArray(List<Node> nodes) {
		return nodes.stream().toArray(Node[]::new);
	}

}
