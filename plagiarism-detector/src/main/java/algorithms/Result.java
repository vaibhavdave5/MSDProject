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
	private double percentage1;
	private double percentage2;
	private Set<SimilaritySnippet> snippet;
	private List<SimilaritySnippet> snippetList;

	/**
	 * Constructor for making a new Result object
	 * 
	 * @param percentage the percentage of similarity
	 * @param snippetList the list of snippets
	 */
	public Result(double percentage1, double percentage2, List<SimilaritySnippet> snippetList) {
		this.percentage1 = percentage1;
		this.percentage2 = percentage2;
		this.snippetList = snippetList;
		this.snippet = null;
	}

	/**
	 * Generates the snippets the snippets are stored into a set snippet itself
	 * has a equals function which takes care that duplicate snippets are not
	 * added. snippet also has a compareTo function which helps in sorting the
	 * snippet w.r.t line number of file-1 .
	 * 
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

	/**
	 * @return the percentage of similarities between two files w.r.t file1.
	 */
	@Override
	public double getPercentagefile1() {
		return this.percentage1;
	}

	/**
	 * @return the percentage of similarities between two files w.r.t file2.
	 */

	@Override
	public double getPercentagefile2() {
		return this.percentage2;
	}

}
