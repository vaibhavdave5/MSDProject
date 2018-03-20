package controllers;

import static org.junit.Assert.*;
import java.io.File;
import java.util.logging.Logger;
import org.junit.Test;
import algorithms.LCSAlgorithm;
import algorithms.NeemanWalshAlgorithm;
import driver.Student;

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
		String path1 = "sample3.c";
		String path2 = "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController(file1, file2);
		double ans = -1;
		ans = ac.getAns(new LCSAlgorithm());
		System.out.println(ans);
		assertTrue(ans >= 0 && ans <= 1);
	}

	/**
	 * Tests larger files
	 */
	@Test
	public void test2() {
		String path1 = "sample3.c";
		String path2 = "sample4.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController(file1, file2);
		double ans = -1;
		ans = ac.getAns(new NeemanWalshAlgorithm());
		System.out.println(ans);
		assertTrue(ans >= 0 && ans <= 1);
	}

	/**
	 * Should throw IOException if path is invalid
	 * 
	 * @throws IOException
	 */
//	@Test(expected = FileNotFoundException.class)
//	public void testIOException() throws IOException {
//		String path1 = "NonExistant.c";
//		String path2 = "sample.c";
//		controllers.AlgorithmController ac = new controllers.AlgorithmController(new File(path1), new File(path2));
//		ac.getAns(new LCSAlgorithm());
//	}

}
