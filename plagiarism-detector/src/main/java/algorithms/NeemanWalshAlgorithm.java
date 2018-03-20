package algorithms;

import java.util.List;
import parser.Node;

/**
 * This class demonstrates the implementation of Neeman-Walsh algorithm
 * @author Vaibhav Dave
 * @since 02/28/2018
 */

public class NeemanWalshAlgorithm implements AlgorithmStrategy {

	private int[][] track;
	private int[][] c;
	private int[][] s;

	/**
	 * Compute the similarity between two Node lists
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public double computeSimilarity(List<Node> list1, List<Node> list2) {
		if(list1.isEmpty()|| list2.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		int x = fetchOpticalAlignment(list1, list2);
		
		return 2.0 * x / (list1.size() + list2.size());
	}

	/**
	 * 
	 * @param list1 first list of nodes
 	 * @param list2 first list of nodes
	 * @return length of the common nodes after optical alignment.
	 */
	private int fetchOpticalAlignment(List<Node> list1, List<Node> list2) {
		int counter;
		int m = list1.size();
		int n = list2.size();
		s = initializeSubstitutionMatrix(list1, list2);
		track = new int[m + 1][n + 1];
		c = new int[m + 1][n + 1];
		computation(list1, list2);
		counter = counterDiagonal(list1, list2, track);
		
		return counter;
	}

	/**
	 * 
	 * @param list1 first list of nodes
 	 * @param list2 first list of nodes
	 * @return length of the common nodes after optical alignment.	 
	 * 
	 * */
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
	private int counterDiagonal(List<Node> list1, List<Node> list2, int[][] track) {
		int counter = 0;
		int m = list1.size();
		int n = list2.size();

		int i = m;
		int j = n;

		while (i != 0 && j != 0) {
			if (track[i][j] == 1) {
				i--;
				j--;
				counter++;
			} else if (track[i][j] == 2) {
				j--;
			} else {
				i--;
			}
		}
		return counter;
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
				if (i == 0 && j == 0) {
					c[i][j] = 0;
				} else if (i == 0) {
					c[i][j] = g * j;
				} else if (j == 0) {
					c[i][j] = g * i;
				} else {
					c = setC(c, i, j, g);
				}
			}
		}
	}

	/**
	 * 
	 * @param c
	 * @param i
	 * @param j
	 * @param g
	 * @return
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

}
