package algorithms;

import parser.Node;

import java.util.List;

/**
 * A class implementing IAlgorithmContext is able to execute a set strategy
 * for comparing two ASTs
 * @author Vaibhav Dave, Shail Shah
 */
public interface IAlgorithmContext {
	/**
	 * Execute this object's strategy to compute the similarity
	 * @param list1 a list of nodes from file 1
	 * @param list2 list of nodes from file 2
	 * @return a Result object which encapsulates the similarities between
	 * the two list of nodes
	 */
    Result executeStrategy(List<Node> list1, List<Node> list2);
}
