package algorithms;

import java.util.Set;
import java.util.TreeSet;

import parser.Node;

/**
 * This class represents the information required
 * after execution of each Algorithm 
 * 
 * @author Vaibhav Dave
 * @since 3/21/2018
 *
 */
public class Result {
	private double percentage;
	private Node[] commonNodes;
	private Set<SimilaritySnippet> snippet;
	
	public Result(double percentage, Node[] common){
		this.percentage = percentage;
		this.commonNodes = common;
		this.snippet = this.generateSnippet();
	}
	
	/**
	 * This function returns the percentage similarity of nodes
	 * @return double 
	 */
	public double getPercentage(){
		return this.percentage;
	}
	
	
	/**
	 * This function returns the common nodes detected by the algorithm
	 * @return Node Array
	 */
	public Node[] getCommonNodes(){
		return this.commonNodes;
	}
	
	
	private Set<SimilaritySnippet> generateSnippet(){
		Set<SimilaritySnippet> set = new TreeSet<>();
		for(Node n: commonNodes){
			if(!(n==null))
			set.add(new SimilaritySnippet(n.getStart(),n.getEnd()));
		}
		return set;
	}
	
	public Set<SimilaritySnippet> getSnippets(){
		return snippet;
	}
	
}
