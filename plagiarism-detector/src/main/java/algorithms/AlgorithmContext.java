package algorithms;

import java.util.List;

import parser.Node;

public class AlgorithmContext {
	
	private AlgorithmStrategy strategy;
	public AlgorithmContext(AlgorithmStrategy strategy){
		this.strategy=strategy;
	}
	
	public double executeStrategy(List<Node> list1, List<Node> list2){
		return strategy.computeSimilarity(list1, list2);
	}

}
