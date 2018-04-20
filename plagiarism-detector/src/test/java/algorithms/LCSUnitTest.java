package algorithms;


import org.junit.Test;

import controllers.AlgorithmController;
import parser.Node;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Tests for LCS Algorithm
 * @author Shail Shah
 * @author Vaibhav Dave
 */
public class LCSUnitTest {


	/**
	 * If the Node lists are empty, an exception should be thrown
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkingEmptyLists() {
		List<Node> list1 = new LinkedList<>();
		List<Node> list2 = new LinkedList<>();
		
		AlgorithmStrategy nw = new LCSAlgorithm();
		nw.computeSimilarity(list1, list2);		
	}
	
	/**
	 * Checking if files are not present
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkingFileNotFound() {
		
		String path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "NoSuchFile.c";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "NoSuchFile2.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController();
		double[] ans = ac.getSimilarityPercentage(new LCSAlgorithm(), ac.getNodeList(file1), ac.getNodeList(file2));
		assertTrue(ans[0] >= 0 && ans[0] <= 1);
		assertTrue(ans[1] >= 0 && ans[1] <= 1);
	}

	/**
	 * Sanity check
	 */
	@Test
	public void sanityTest() {
		String path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample3.c";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController();
		double[] ans = ac.getSimilarityPercentage(new AlgorithmFactory().getAlgo(Algorithm.LCS), ac.getNodeList(file1), ac.getNodeList(file2));
		assertTrue(ans[0] >= 0 && ans[0] <= 1);
		assertTrue(ans[1] >= 0 && ans[1] <= 1);
	}

}

