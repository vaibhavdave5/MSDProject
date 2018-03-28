package driver;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import algorithms.Algorithm;
import algorithms.IResult;
import algorithms.LCSAlgorithm;
import algorithms.SimilaritySnippet;
import controllers.AlgorithmController;

public class DriverTests {
	@Test
	public void testDriver() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		repoPaths.add("C:/test-repos/student-110");
		repoPaths.add("C:/test-repos/student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		URL url = this.getClass().getResource("/studentData.xlsx");
		// Driver.getStudentData(url.getPath());
	}

	@Test
	public void testgetCodeFiles() {
		Driver driver = Driver.getInstance();
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
	public void testSimilarityScoreListEmpty() {
		Driver driver = Driver.getInstance();
		List<Double> similarityScoreList = new ArrayList<>();
		driver.maxSimilarityScore(similarityScoreList);
		List<Double> expected = new ArrayList<>();
		List<Double> actual = similarityScoreList;
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckForPlagiarismLCS() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		String hwDir = "HW3";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.LCS);
		Summary summary = driver.viewSummary();
		Integer expected1 = 110;
		Integer expected2 = 111;
		Set<StudentPair> actual = summary.getRed();
		for(StudentPair sp: actual) {
			Integer actual1 = sp.getStudent1Id();
			Integer actual2 = sp.getStudent2Id();
			assertEquals(expected1, actual1);
			assertEquals(expected2, actual2);
		}
		assertTrue(summary.getYellow().isEmpty());
		assertTrue(summary.getGreen().isEmpty());
	} 
	
	@Test
	public void testCheckForPlagiarismNW() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		String hwDir = "HW3";
		driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
		Summary summary = driver.viewSummary();
		Integer expected1 = 110;
		Integer expected2 = 111;
		Set<StudentPair> actual = summary.getRed();
		for(StudentPair sp: actual) {
			Integer actual1 = sp.getStudent1Id();
			Integer actual2 = sp.getStudent2Id();
			assertEquals(expected1, actual1);
			assertEquals(expected2, actual2);
		}
		assertTrue(summary.getYellow().isEmpty());
		assertTrue(summary.getGreen().isEmpty());
	}
	
	@Test
	public void testCheckForPlagiarismDefault() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		String hwDir = "HW3";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.DEFAULT);
		Summary summary = driver.viewSummary();
		Integer expected1 = 110;
		Integer expected2 = 111;
		Set<StudentPair> actual = summary.getRed();
		for(StudentPair sp: actual) {
			Integer actual1 = sp.getStudent1Id();
			Integer actual2 = sp.getStudent2Id();
			assertEquals(expected1, actual1);
			assertEquals(expected2, actual2);
		}
		assertTrue(summary.getYellow().isEmpty());
		assertTrue(summary.getGreen().isEmpty());
	}
	
	@Test
	public void testCheckForPlagiarismNullRepos() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = null;
		String hwDir = "HW3";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
	}
	
	@Test
	public void testCheckForPlagiarismEmptyHW() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		
		String hwDir = "";
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
	}
	
	@Test
	public void testCheckForPlagiarismNullHW() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();

		String basePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "java" + File.separator + "controllers" + File.separator + "test-repos"
				+ File.separator;

		repoPaths.add(basePath + "student-110");
		repoPaths.add(basePath + "student-111");
		
		String hwDir = null;
		String message = driver.checkForPlagiarism(repoPaths, hwDir, Algorithm.NW);
	}
	
	@Test
	public void testGetNameById() {
		Driver driver = Driver.getInstance();
		URL url = this.getClass().getResource("/studentData.xlsx");
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
		 
		String path1 = "sample2.c";
		String path2 = "sample.c";
		
		File file1 = new File(path1);
		File file2 = new File(path2);

		AlgorithmController ac = new AlgorithmController(file1, file2);
		IResult result = ac.getResult(new LCSAlgorithm());
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
	
	@Test
	public void testgenerateSnippet() {
		Driver driver = Driver.getInstance();
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
