package algorithms;

import parser.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance of LCS is able to compute the similarity between two Node Lists
 * 
 * @author Vaibhav Dave
 * @author Shail Shah
 *
 */
public class LCSAlgorithm implements AlgorithmStrategy {
	private List<SimilaritySnippet> snippets = new ArrayList<>();
 
	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @throws IllegalArgumentException
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public IResult computeSimilarity(List<Node> list1, List<Node> list2) {
		if (list1.isEmpty()) throw new IllegalArgumentException("File 1 is empty");
		else if (list2.isEmpty()) throw new IllegalArgumentException("File 2 is empty");
		System.out.println("Inside LCS");
		setCommonNodes(list1, list2);
		
		return new Result(((1.0 * snippets.size()) / list1.size()),
		   				 ((1.0 * snippets.size()) / list2.size()),
			 			  snippets);
	}

	/**
	 * Backtrack the result of the lcs and find out the actual longest common
	 * subsequence
	 * 
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return Node[] of lcs
	 */
	public void setCommonNodes(List<Node> list1, List<Node> list2) {
		int[][] map = getMap(list1, list2);

		int i = list1.size();
		int j = list2.size();
		while (i > 0 && j > 0) {

			if (list1.get(i - 1).equals(list2.get(j - 1))) {
				//Add the nodes part of the LCS to similar snippet
				snippets.add(new SimilaritySnippet(list1.get(i-1), list2.get(j-1)));
				i--;
				j--;
			} else if (map[i - 1][j] > map[i][j - 1])
				i--;
			else
				j--;
		}

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