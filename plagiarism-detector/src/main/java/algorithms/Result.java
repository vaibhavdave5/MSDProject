package algorithms;

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
	private Node[] commonNodes;

	/**
	 * Constructor for making a new Result object
	 * @param percentage the percentage of similarity
	 * @param common the list of common Nodes
	 */
	public Result(double percentage, Node[] common){
		this.percentage = percentage;
		this.commonNodes = common;
	}
	
	/**
	 * @return the percentage similarity of nodes
	 */
	public double getPercentage(){
		return this.percentage;
	}
	
	
	/**
	 * @return the common nodes detected by the algorithm
	 */
	public Node[] getCommonNodes(){
		return this.commonNodes;
	}
}
