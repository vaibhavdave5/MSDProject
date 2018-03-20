package driver;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DriverTests {

	@Test
	public void test1() {
		Driver driver = Driver.getInstance();
		List<String> repoPaths = new ArrayList<>();
		repoPaths.add("C:/student-110");
		repoPaths.add("C:/student-111");
		driver.setRepoPaths(repoPaths);
		driver.setHWDir("HW3");
		driver.getStudentData("C:/MSD/team-107/plagiarism-detector/src/main/java/driver/studentData.xlsx");
	}
	
	@Test
	public void test2() {
		Driver driver = Driver.getInstance();
		driver.getStudentData("invalid_path_to_test_IOException");
	}

}
