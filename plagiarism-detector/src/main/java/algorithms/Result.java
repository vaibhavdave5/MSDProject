package algorithms;

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
	
	public Result(double percentage, Node[] common){
		this.percentage = percentage;
		this.commonNodes = common;
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
}
