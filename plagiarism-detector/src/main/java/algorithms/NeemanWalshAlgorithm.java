package algorithms;

import java.util.ArrayList;
import java.util.List;
import parser.Node;

/**
 * This class demonstrates the implementation of Neeman-Walsh algorithm
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
	 * @param list1
	 *            first list of nodes
	 * @param list2
	 *            first list of nodes
	 * @return length of the common nodes after optical alignment.
	 */
	private Node[] fetchOpticalAlignment(List<Node> list1, List<Node> list2) {
		Node[] ans;
		int m = list1.size();
		int n = list2.size();
		s = initializeSubstitutionMatrix(list1, list2);
		track = new int[m + 1][n + 1];
		c = new int[m + 1][n + 1];
		computation(list1, list2);
		ans = counterDiagonal(list1, list2, track);

		return ans;
	}

	/**
	 * 
	 * @param list1
	 *            first list of nodes
	 * @param list2
	 *            first list of nodes
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
	 * 
	 * @param list1
	 * @param list2
	 */
	private void computation(List<Node> list1, List<Node> list2) {
		int m = list1.size();
		int n = list2.size();
		int g = -1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {

				c = setC(c, i, j, g);

			}
		}
	}

	/**
	 * 
	 * This function is an intermediate to setting the array of Comparison array
	 * in the algorithm
	 * 
	 * @param comparison
	 *            array[][]
	 * @param int
	 *            i
	 * @param int
	 *            j
	 * @param int
	 *            g
	 * @return updated array
	 */
	private int[][] setC(int[][] c, int i, int j, int g) {
		int scorediag = c[i - 1][j - 1] + s[i][j];
		int scoreup = c[i - 1][j] + g;
		int scoreleft = c[i][j - 1] + g;
		c[i][j] = Math.max(Math.max(scorediag, scoreup), scoreleft);
		if (c[i][j] == scorediag) {
			track[i][j] = 1;
		} else if (c[i][j] == scoreleft) {
			track[i][j] = 2;
		} else {
			track[i][j] = 3;
		}
		return c;
	}

	/**
	 * Converts the List of Nodes to node array
	 * 
	 * @param List
	 *            of Nodes
	 * @return Array of Nodes
	 */
	private Node[] convertListToArray(List<Node> nodes) {
		Node[] ans = new Node[nodes.size()];

		for (int i = 0; i < ans.length; i++) {
			ans[i] = nodes.get(i);
		}

		return ans;
	}

}
