package controllers;

import algorithms.LCSAlgorithm;
import algorithms.NeedlemanWunschAlgorithm;
import org.junit.Test;
import parser.Node;

import java.io.File;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests AlgorithmController to see if the similarity is computed as expected.
 * 
 * @author Vaibhav Dave, Shail
 *
 */

public class AlgorithmControllerTest {

	Logger logger = Logger.getLogger(this.getClass().toString());

	/**
	 * Tests the simple small files
	 */

	@Test
	public void test() {
		String path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample3.c";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController();
		double[] ans = ac.getSimilarityPercentage(new LCSAlgorithm(), ac.getNodeList(file1), ac.getNodeList(file2));
		assertTrue(ans[0] >= 0 && ans[0] <= 1);
		assertTrue(ans[1] >= 0 && ans[1] <= 1);
	}

	/**
	 * Tests larger files
	 */
	@Test
	public void test2() {
		String path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample3.c";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController();
		double[] ans = ac.getSimilarityPercentage(new NeedlemanWunschAlgorithm(), ac.getNodeList(file1), ac.getNodeList(file2));
		assertTrue(ans[0] >= 0 && ans[0] <= 1);
		assertTrue(ans[1] >= 0 && ans[1] <= 1);
	}

	/**
	 * Checking if the equals method works correctly
	 */
	@Test
	public void Nodetest() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 50, 3, "Hi");
		assertFalse(node.equals(node2));
	}

	/**
	 * Checks if equals work correctly with nulls
	 */
	@Test
	public void Nodetest2() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = null;
		assertFalse(node.equals(node2));
	}

	/**
	 * Checks if equals work correctly
	 */
	@Test
	public void Nodetest3() {
		Node node = new Node(1, 40, 3, "Hello");
		Object node2 = new Object();
		assertFalse(node.equals(node2));
	}

	/**
	 * Checks if equals work correctly for same objects
	 */
	@Test
	public void Nodetest4() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 40, 3, "Hello");
		assertTrue(node.equals(node2));
	}

	@Test
	public void Nodetest5() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 40, 3, "Hello");
		assertTrue(node.hashCode() == node2.hashCode());
	}

}
