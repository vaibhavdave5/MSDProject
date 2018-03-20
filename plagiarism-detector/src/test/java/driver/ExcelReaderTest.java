package driver;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

public class ExcelReaderTest {

	@Test
	public void test() {
		final String xlsPath = "C:/MSD/team-107/plagiarism-detector/src/main/java/driver/studentData.xlsx";
		ExcelReader er = new ExcelReader();
		try {
			Map<Integer, Student> map = er.getStudentMap(xlsPath);
			assertTrue(map.containsKey(107));
			assertEquals("e", map.get(107).getName());
		} 
		catch (InvalidFormatException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
