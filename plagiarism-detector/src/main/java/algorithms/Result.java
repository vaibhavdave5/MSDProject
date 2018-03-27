package algorithms;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents the information required after execution of each
 * Algorithm
 * 
 * @author Vaibhav Dave
 * @author Shail Shah
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
	 * @param common the list of common Nodes
	 */
	public Result(double percentage, List<SimilaritySnippet> snippetList) {
		this.percentage = percentage;
		this.snippetList = snippetList;
		this.snippet = null;
	}

	@Override
	public double getPercentage() {
		return this.percentage;
	}

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
