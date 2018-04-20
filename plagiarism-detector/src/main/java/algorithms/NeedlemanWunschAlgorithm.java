package algorithms;

import parser.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * An object of NeedlemanWunschAlgorithm is able to compute the similarity
 * between two Node lists, using the Optical Alignment concept between two
 * Similarity Algorithm.
 * 
 * @author Vaibhav Dave
 * @since 02/28/2018
 */

public class NeedlemanWunschAlgorithm implements AlgorithmStrategy {

	private int diagonalScore = 1;
	private int rightScore = 2;
	private int leftScore = 3;
	
	/**
	 * Compute the similarity between two Node lists
	 * 
	 * @param list1 a list of Nodes
	 * @param list2 another list of Nodes
	 * @throws IllegalArgumentException if either lists are empty
	 * @return a number representing the similarity between two nodes
	 */
	@Override
	public IResult computeSimilarity(List<Node> list1, List<Node> list2) {

		// check for file1 whether its empty or not
		if (list1.isEmpty())
			throw new IllegalArgumentException("The file1 are empty");

		// check for file2 whether its empty or not
		if (list2.isEmpty())
			throw new IllegalArgumentException("The file2 are empty");

		List<SimilaritySnippet> snippets = getCommonNodesList(list1, list2);

		return new Result(((1.0 * snippets.size()) / list1.size()),
  				 ((1.0 * snippets.size()) / list2.size()),
	 			  snippets);
	}

	/**
	 * Gets the nodes that are part of the optical alignment
	 * 
	 * @param list1 a list
	 * @param list2 another list
	 * @return a list of snippets that are common for both lists
	 */
	private List<SimilaritySnippet> getCommonNodesList(List<Node> list1, List<Node> list2) {
		List<SimilaritySnippet> snippets = new ArrayList<>();

		int[][] trackMatrix = getTrackMatrix(list1, list2);

		int i = list1.size();
		int j = list2.size();
		while (i != 0 && j != 0) {
			switch (trackMatrix[i][j]) {
			// go to diagonal neighbor because of exact match
			case 1:
				snippets.add(new SimilaritySnippet(list1.get(i - 1), list2.get(j - 1)));
				i--;
				j--;
				break;

			// the neighbor above it (a gap is introduced in top sequence).
			case 2:
				j--;
				break;

			// the neighbor to the left, (a gap is introduced in the left
			// sequence)
			case 3:
				i--;
				break;

			default:
				break;
			}
		}
		return snippets;
	}

	/**
	 * The trace back step determines the actual alignment(s) that result in the
	 * maximum score.
	 * 
	 * @param list1 a list
	 * @param list2 another list
	 * @return an intermediate matrix
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
	 * Creating the substitution matrix put 1 if characters at i and j match put
	 * -1 otherwise in the substitutionMatrix[i][j] Substitution Matrix S
	 * 
	 * @param list1 a list
	 * @param list2 another list
	 * @return a substitution matrix that has been initialized
	 */
	private int[][] initializeSubstitutionMatrix(List<Node> list1, List<Node> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		int[][] substitutionMatrix = new int[size1 + 1][size2 + 1];

		for (int i = 0; i < size1; i++)
			for (int j = 0; j < size2; j++)
				substitutionMatrix[i][j] = list1.get(i).equals(list2.get(j)) ? 1 : -1;

		return substitutionMatrix;
	}

	/**
	 * The score of any cell c(i,j) depends only on three adjacent cell values.
	 * The score of any cell c(i, j) is the maximum of scorediag = c (i-1, j-1)
	 * + S(i, j) scoreup = c(i-1, j) + g scoreleft = c(i, j-1) + g where S(i,j)
	 * is the substitution score for letters i and j, and g is the gap penalty
	 * 
	 * @param substitutionMatrix the substitution matix
	 * @param c the result matrix
	 * @param trackMatrix the track matrix
	 * @param i the x coordinate
	 * @param j the y coordinate
	 */
	private void setTrackMatrix(int[][] substitutionMatrix, int[][] c, int[][] trackMatrix, int i, int j) {

		int scoreDiagonal = c[i - 1][j - 1] + substitutionMatrix[i][j];
		int scoreup = c[i - 1][j] - 1;
		int scoreleft = c[i][j - 1] - 1;
		c[i][j] = Math.max(Math.max(scoreDiagonal, scoreup), scoreleft);
		if (c[i][j] == scoreDiagonal) trackMatrix[i][j] = diagonalScore;
		else if (c[i][j] == scoreleft) trackMatrix[i][j] = leftScore;
		else trackMatrix[i][j] = rightScore;
	}
}