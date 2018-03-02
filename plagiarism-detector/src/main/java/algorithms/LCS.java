package algorithms;

import java.util.List;

import parser.Node;

public class LCS implements algorithms.Algorithm {

	@Override
	public double computeSimilarity(List<Node> list1, List<Node> list2) {
		int lcs = computeLCS(list1, list2);
		return (1.0 * 2 * 1.0 * lcs) / (1.0 * list1.size() + 1.0 * list2.size());
	}

	private int computeLCS(List<Node> list1, List<Node> list2) {

		int m = list1.size();
		int n = list2.size();
		int[][] l = new int[m + 1][n + 1];

		for (int i = 1; i <= list1.size(); i++) {
			for (int j = 1; j <= list2.size(); j++) {
				if (i == 0 || j == 0)
					l[i][j] = 0;
				else if (list1.get(i - 1).equals(list2.get(j - 1))) {
					l[i][j] = l[i - 1][j - 1] + 1;
				} else
					l[i][j] = Math.max(l[i - 1][j], l[i][j - 1]);
			}
		}
		return l[m][n];
	}
}
