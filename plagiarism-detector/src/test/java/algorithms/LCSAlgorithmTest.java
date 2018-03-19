package algorithms;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import parser.Node;

/**
 * Tests for LCS Algorithm
 * @author Shail Shah
 */
public class LCSAlgorithmTest {

	/**
	 * If the Node lists are empty, an exception should be thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test() {
		List<Node> list1 = new LinkedList<>();
		List<Node> list2 = new LinkedList<>();
		
		AlgorithmStrategy lcs = new LCSAlgorithm();
		lcs.computeSimilarity(list1, list2);
		
	}
}

