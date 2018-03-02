package algorithms;

import java.util.List;

import parser.Node;

public class LCS implements algorithms.Algorithm {

	@Override
	public double computeSimilarity(List<Node> list1, List<Node> list2) {
		return 2 * computeLCS(list1, list2) / (list1.size() + list2.size());
	}

	private double computeLCS(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] map = new int[size1 + 1][size2 + 1];

		for (int i = 1; i <= size1; i++) 
			for (int j = 1; j <= size2; j++)
				map[i][j] = (i == 0 || j == 0) ? 0 :
					(list1.get(i - 1).equals(list2.get(j - 1))) ? (map[i - 1][j - 1] + 1) :
						Math.max(map[i - 1][j], map[i][j - 1]);

		return (double)map[size1][size2];
	}
}
