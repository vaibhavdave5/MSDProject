package controllers;

import static org.junit.Assert.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Test;
import algorithms.LCSAlgorithm;
import algorithms.NeemanWalshAlgorithm;
import driver.Driver;
import driver.Student;
import parser.Node;

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
	// Student Tests
	/////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGetters() {
		Student s = new Student(101, "John", "s.s@husky.neu.edu");
		assertEquals(101, s.getId());
		assertEquals("John", s.getName());
		assertEquals("s.s@husky.neu.edu", s.getEmail());
	}

	@Test
	public void testSetters() {
		Student s = new Student(101, "Sam", "s.s@husky.neu.edu");
		s.setId(102);
		assertEquals(102, s.getId());

		s.setName("John");
		assertEquals("John", s.getName());

		s.setEmail("j.j@husky.neu.edu");
		assertEquals("j.j@husky.neu.edu", s.getEmail());
	}
	//////////////////////////////////////////////////////////////////////////////

	// Driver Tests
	//////////////////////////////////////////////////////////////////////////////
	@Test
	public void testDriver() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		repoPaths.add("C:/test-repos/student-110");
		repoPaths.add("C:/test-repos/student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		URL url = this.getClass().getResource("/studentData.xlsx");
		Driver.getStudentData(url.getPath());
	}

	@Test
	public void testgetCodeFiles() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		
		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "java" + File.separator + "controllers" + File.separator + "test-repos" + File.separator;
		
		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		driver.getCodeFiles();
	}
	
	@Test
	public void testCheckForPlagiarism() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		
		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "java" + File.separator + "controllers" + File.separator + "test-repos" + File.separator;
		
		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		String hwDir = "HW3";
		driver.checkForPlagiarism(repoPaths, hwDir);
	}

	// @Test
	// public void testgetStudentDataInvalidXLSXPath() {
	// Driver driver = Driver.getInstance();
	// driver.getStudentData("invalid_path_to_test_IOException");
	// }

	//////////////////////////////////////////////////////////////////////////////

	/**
	 * Should throw IOException if path is invalid
	 * 
	 * @throws IOException
	 */

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

	public void Nodetest5() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 40, 3, "Hello");
		assertTrue(node.hashCode() == node2.hashCode());
	}

}
