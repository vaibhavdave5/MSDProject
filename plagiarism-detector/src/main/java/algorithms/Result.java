package algorithms;

import parser.Node;

public class Result {
	private double percentage;
	private Node[] commonNodes;
	
	public Result(double percentage, Node[] common){
		this.percentage = percentage;
		this.commonNodes = common;
	}
	
	public double getPercentage(){
		return this.percentage;
	}
	
	public Node[] getCommonNodes(){
		return this.commonNodes;
	}
}
