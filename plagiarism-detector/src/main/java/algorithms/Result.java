package algorithms;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents the information required after execution of each
 * Algorithm
 * 
 * @author Vaibhav Dave
 * @since 3/21/2018
 *
 */
public class Result implements IResult {
	private double percentage;
	private Set<SimilaritySnippet> snippet;
	private List<SimilaritySnippet> snippetList;

	/**
	 * Constructor for making a new Result object
	 * 
	 * @param percentage the percentage of similarity
	 * @param snippetList the list of snippets
	 */
	public Result(double percentage, List<SimilaritySnippet> snippetList) {
		this.percentage = percentage;
		this.snippetList = snippetList;
		this.snippet = null;
	}

	/**
	 * Get the percentage of similarity
	 * @return the percentage of similarity
	 */
	@Override
	public double getPercentage() {
		return this.percentage;
	}


	/**
	 * Gernerate the snippets
	 * @return a set containing similar snippets
	 */
	@Override
	public Set<SimilaritySnippet> generateSnippet() {
		if (this.snippet == null) {
			Set<SimilaritySnippet> set = new TreeSet<>();
			for (SimilaritySnippet s : snippetList) {
				if (s != null)
					set.add(s);
			}
			this.snippet = set;
			return set;
		}
		return this.snippet;
	}

}
