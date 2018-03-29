package algorithms;

import parser.Node;

import java.util.List;

/**
 * An object of AlgorithmContext is able to set the strategy
 * and execute it on two ASTs to compute their similarities
 * @author Vaibhav Dave
 */
public class AlgorithmContext {
	
	private AlgorithmStrategy strategy;

	/**
	 * Constructor for making a new AlgorithmContext
	 * @param strategy the strategy to be employed to
	 *                   compute the similarity between two Node lists
	 */
	public AlgorithmContext(AlgorithmStrategy strategy){
		this.strategy=strategy;
	}

	/**
	 * Execute this object's strategy to compute the similarity
	 * @param list1 a list of nodes
	 * @param list2 another list of nodes
	 * @return a Result object which encapsulates the similarities between
	 * the two list of nodes
	 */
	public IResult executeStrategy(List<Node> list1, List<Node> list2){
		return strategy.computeSimilarity(list1, list2);
	}

}
