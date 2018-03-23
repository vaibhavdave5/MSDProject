package algorithms;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import parser.Node;

/**
 * This class represents the information required
 * after execution of each Algorithm 
 * 
 * @author Vaibhav Dave
 * @author Shail Shah
 * @since 3/21/2018
 *
 */
public class Result {
	private double percentage;
	private Set<SimilaritySnippet> snippet;
	private List<SimilaritySnippet> snippetList;

	/**
	 * Constructor for making a new Result object
	 * @param percentage the percentage of similarity
	 * @param common the list of common Nodes
	 */
	public Result(double percentage, List<SimilaritySnippet> snippetList){
		this.percentage = percentage;
		this.snippetList = snippetList;
	}
	
	/**
	 * @return the percentage similarity of nodes
	 */
	public double getPercentage(){
		return this.percentage;
	}
	
	/**
	 * @return a set of SimilaritySnippets
	 */
	public Set<SimilaritySnippet> getSnippets(){
		return snippet;
	}
	
	public Set<SimilaritySnippet> generateSnippet(){
		Set<SimilaritySnippet> set = new TreeSet<>();
		for(SimilaritySnippet s: snippetList){
			if(s!=null)
			set.add(s);
		}
		return set;
	}
}
