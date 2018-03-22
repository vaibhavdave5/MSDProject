package algorithms;

import java.util.ArrayList;
import java.util.List;
import parser.Node;

/**
 * An object of NeemanWalshAlgorithm is able to compute the similarity
 * between two Node lists, using the Neeman-Walsh Similarity Algorithm.
 * 
 * @author Vaibhav Dave
 * @author Shail Shah
 * @since 02/28/2018
 */

public class NeemanWalshAlgorithm implements AlgorithmStrategy {

	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public Result computeSimilarity(List<Node> list1, List<Node> list2) {
		if (list1.isEmpty() || list2.isEmpty())
			throw new IllegalArgumentException();

		Node[] commonNodesArray = getCommonNodesArray(list1, list2);
		double similarityScore = 2.0 * commonNodesArray.length / (list1.size() + list2.size());

		return new Result(similarityScore, commonNodesArray);
	}

	/**
	 * 
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return a list of common nodes
	 */
	private Node[] getCommonNodesArray(List<Node> list1, List<Node> list2) {
		return getCommonNodesList(list1, list2)
				.stream()
				.toArray(Node[]::new);
	}

	private List<Node> getCommonNodesList(List<Node> list1, List<Node> list2) {
		List<Node> commonNodes = new ArrayList<>();
		//TODO: Rename c[][], substitutionMatrix[][], and trackMatrix[][] with better names
		int[][] trackMatrix = getTrackMatrix(list1, list2);


		int i = list1.size();
		int j = list2.size();
		while(i!= 0 && j !=0) {
			switch(trackMatrix[i][j]) {
				case 1:
					i--;
					j--;
					commonNodes.add(list1.get(i));
					break;

				case 2:
					j--;
					break;

				case 3:
					i--;
					break;

				default:
					throw new IllegalStateException("trackMatrix not initialized correctly.");
			}
		}

		return commonNodes;
	}

	/**
	 * Populate the c array
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 */
	private int[][] getTrackMatrix(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] substitutionMatrix = initializeSubstitutionMatrix(list1, list2);
		int[][] c = new int[size1 + 1][size2 + 1];
		int[][] trackMatrix = new int[size1 + 1][size2 + 1];

		for (int i = 1; i <= size1; i++)
			for (int j = 1; j <= size2; j++)
				setTrackMatrix(substitutionMatrix, c, trackMatrix, i, j);

		return trackMatrix;
	}


	/**
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @return an initialized substitution matrix
	 * 
	 */
	private int[][] initializeSubstitutionMatrix(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] substitutionMatrix = new int[size1 + 1][size2 + 1];

		for (int i = 0; i < size1; i++)
			for (int j = 0; j < size2; j++)
				substitutionMatrix[i][j]
						= list1.get(i).equals(list2.get(j)) ? 1 : -1;

		return substitutionMatrix;
	}

	/**
	 * Set the ith row and jth column of the trackMatrix
	 * @param substitutionMatrix the substitution matrix
	 * @param c the c matrix
	 * @param trackMatrix the trackMatrix
	 * @param i the row number
	 * @param j the column number
	 */
	private void setTrackMatrix(int[][] substitutionMatrix, int[][] c,
								int[][] trackMatrix, int i, int j) {

		int scoreDiagonal = c[i - 1][j - 1] + substitutionMatrix[i][j];
		int scoreup = c[i - 1][j] - 1;
		int scoreleft = c[i][j - 1] - 1;

		c[i][j] = Math.max(Math.max(scoreDiagonal, scoreup), scoreleft);

		if(c[i][j] == scoreDiagonal)
			trackMatrix[i][j] = 1;
		else if(c[i][j] == scoreleft)
			trackMatrix[i][j] = 2;
		else
			trackMatrix[i][j] = 3;
	}
}
