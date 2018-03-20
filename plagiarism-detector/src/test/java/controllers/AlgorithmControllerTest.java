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
		String path1 = "sample.c";
		String path2 = "sample2.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController(file1, file2);
		double ans = -1;
		ans = ac.getAns(new LCSAlgorithm());
		assertTrue(ans >= 0 && ans <= 1);
	}

	/**
	 * Tests larger files
	 */
	@Test
	public void test2() {
		String path1 = "sample.c";
		String path2 = "sample2.c";
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController(file1, file2);
		double ans = -1;
		ans = ac.getAns(new NeemanWalshAlgorithm());
		assertTrue(ans >= 0 && ans <= 1);
	}
	
//	@Test
//	public void testGetters() {
//		Student s = new Student(101, "Sam", "s.s@husky.neu.edu");
//		assertEquals(101, s.getId());
//		assertEquals("Sam", s.getName());
//		assertEquals("s.s@husky.neu.edu", s.getEmail());
//	}
//	
//	@Test
//	public void testSetters() {
//		Student s = new Student(101, "Sam", "s.s@husky.neu.edu");
//		s.setId(102);
//		assertEquals(102, s.getId());
//		
//		s.setName("John");
//		assertEquals("John", s.getName());
//		
//		s.setEmail("j.j@husky.neu.edu");
//		assertEquals("j.j@husky.neu.edu", s.getEmail());
//	}

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
