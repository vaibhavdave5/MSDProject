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
public class LevenshteinDistance implements AlgorithmStrategy {
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

		int ed = computeEditDistance(list1, list2);
		
		return new Result((Math.abs(((1.0 * ed) / list1.size())-1)),
		   				 (Math.abs(((1.0 * ed) / list2.size())-1)),
			 			  snippets);
	}

	/**
	 * Compute the edit distance between the two lists 
	 * using LevenshteinDistance
	 * for more info refer
	 * https://en.wikipedia.org/wiki/Levenshtein_distance
	 * 
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return Node[] of lcs
	 */
	public int computeEditDistance(List<Node> list1, List<Node> list2) {
        int m = list1.size();
        int n = list2.size();
		// Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];
      
        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i==0)
                    dp[i][j] = j;  // Min. operations = j
      
                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j==0)
                    dp[i][j] = i; // Min. operations = i
      
                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (list1.get(i-1).equals(list2.get(j-1))){
                    snippets.add(new SimilaritySnippet(list1.get(i-1), list2.get(j-1)));
                	dp[i][j] = dp[i-1][j-1];
                }
      
                // If last character are different, consider all
                // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j-1],  // Insert
                                       dp[i-1][j],  // Remove
                                       dp[i-1][j-1]); // Replace
            }
        }
  
        return dp[m][n];
    }
	
	
	/**
	 * This function compares three variables at a time
	 * and computes the minimum out of the two variables.
	 * @param a
	 * @param b
	 * @param c
	 * @return min
	 */
	private static int min(int a,int b,int c){
		int min = b;
		if(a<b){
			min = a;
		}
		if(c<min)
			return c;
		
		return min;
	}
	
}