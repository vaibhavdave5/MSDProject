package algorithms;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import parser.Node;

public class EditDistanceTest {

	/**
	 * If the Node lists are empty, an exception should be thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test() {
		List<Node> list1 = new LinkedList<>();
		List<Node> list2 = new LinkedList<>();
		
		AlgorithmStrategy ed = new LevenshteinDistance();
		ed.computeSimilarity(list1, list2);		
	}


}
