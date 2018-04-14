package driver;

import algorithms.Algorithm;
import algorithms.IResult;
import algorithms.LCSAlgorithm;
import algorithms.SimilaritySnippet;
import controllers.AlgorithmController;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for testing the Driver
 * @author Darshan Panse
 */
public class DriverTests {

	/**
	 * Test for normal program flow
	 */
	@Test
	public void testDriver() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		repoPaths.add("C:/test-repos/student-110");
		repoPaths.add("C:/test-repos/student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		URL url = this.getClass().getResource("/studentData.xlsx");
	}

	/**
	 * Test for checking the returned Ids in getStudentHWMap()
	 */
	@Test
	public void testgetCodeFiles() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		driver.getCodeFiles();
		
		Integer expectedKey1 = 110;
		Integer expectedKey2 = 111;
		Integer[] actualKeys = new Integer[2];
		int i = 0;
		for(Map.Entry<Integer, Collection<File>> entry : driver.getStudentHWMap().entrySet()) {
			actualKeys[i] = entry.getKey();
			i++;
		}
		assertEquals(expectedKey1, actualKeys[0]);
		assertEquals(expectedKey2, actualKeys[1]);
	}

	@Test
	public void testCheckForPlagiarism() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		Integer expected1 = 110;
		Integer expected2 = 111;

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-" + expected1);
		repoPaths.add(basePath + "student-" + expected2);
		String hwDir = "HW3";

		for(Algorithm algorithm : Algorithm.values()) {
			String message = driver.checkForPlagiarism(repoPaths, hwDir, algorithm);
			ISummary summary = driver.viewSummary();
			Set<IStudentPair> actual = summary.getRedPairs();
			for (IStudentPair sp : actual) {
				Integer actual1 = sp.getStudent1Id();
				Integer actual2 = sp.getStudent2Id();
				assertEquals(expected1, actual1);
				assertEquals(expected2, actual2);
			}
			assertTrue(summary.getYellowPairs().isEmpty());
			assertTrue(summary.getGreenIds().isEmpty());
		}
	}


	/**
	 * Assert that a message is thrown when repoPath is null
	 */
	@Test
	public void testCheckForPlagiarismNullRepos() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = null;
		String hwDir = "HW3";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
		assertEquals("No student repository or homework directory selected", message);
	}

	/**
	 * Assert that a message is thrown when hwdir is empty
	 */
	@Test
	public void testCheckForPlagiarismEmptyHW() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		
		String hwDir = "";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
		assertEquals("No student repository or homework directory selected", message);
	}

	/**
	 * Assert that a message is thrown when hwdir is null
	 */
	@Test
	public void testCheckForPlagiarismNullHW() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		
		String hwDir = null;
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
		assertEquals("No student repository or homework directory selected", message);
	}

	/**
	 * Test getNameById()
	 */
	@Test
	public void testGetNameById() {
		IDriver driver = Driver.getInstance();
		URL url = this.getClass().getResource("/studentData.xlsx");
		System.out.println(url.toString());
		String expected1 = "Darshan";
		String expected2 = "Saman";
		String xlsxPath = url.getPath();
		driver.getStudentData(new File(xlsxPath));
		String actual1 = driver.getNameById(101);
		String actual2 = driver.getNameById(102);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
  
	/**
	 * Testing the snippet generator
	 */
	@Test
	public void testSnippets() {  
		 
		String path1 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample2.c";
		String path2 = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "sample.c";
		
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController();
		IResult result = ac.getResult(new LCSAlgorithm(),ac.getNodeList(file1),ac.getNodeList(file2));
		Set<SimilaritySnippet> set = result.generateSnippet();
		// Checking if all the values are set
		for (SimilaritySnippet s : set) {
			s.getStart1();
			s.getStart2();
			s.getEnd1();
			s.getEnd2();
			s.equals(s);
			s.hashCode();
		}
		
		
		assertTrue(set.size() > 0);
	}

	/**
	 * Test generateSnippet()
	 */
	@Test
	public void testgenerateSnippet() {
		IDriver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		driver.getCodeFiles();
		Integer student1Id = 110;
		Integer student2Id = 111;
		driver.generateSnippet(student1Id, student2Id);
	}
}
