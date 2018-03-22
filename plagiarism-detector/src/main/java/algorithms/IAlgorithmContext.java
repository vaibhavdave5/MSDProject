package algorithms;

import parser.Node;

import java.util.List;

/**
 * A class implementing IAlgorithmContext is able to execute a set strategy
 * for comparing two ASTs
 * @author Shail Shah
 */
public interface IAlgorithmContext {
    /**
     * Execute this object's strategy to compute the similarity
     * @param list1 a list of nodes
     * @param list2 another list of nodes
     * @return a Result object which encapsulates the similarities between
     * the two list of nodes
     */
    Result executeStrategy(List<Node> list1, List<Node> list2);
}
