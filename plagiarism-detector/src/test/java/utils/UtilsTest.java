package utils;

import org.junit.Test;

import java.io.File;
import static org.junit.Assert.*;

/**
 * Tests for the Util class
 * @author Shail Shah
 */
public class UtilsTest {

	/**
	 * get file string from the start
	 */
	@Test
	public void getFileStringNormalStart() {
		File file = new File("sample.c");
		int start = 1;
		int end = 3;
		String fileString = Utils.getFileString(file, start, end);
		String expectedfileString = "1. int main()\n" + "2. {\n" + "3. \tint a = 0;\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string from the middle
	 */
	@Test
	public void getFileStringNormalMiddle() {
		File file = new File("sample.c");
		int start = 2;
		int end = 3;
		String fileString = Utils.getFileString(file, start, end);
		String expectedfileString = "2. {\n" + "3. \tint a = 0;\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string with a negative starting line
	 */
	@Test
	public void getFileStringNormalNegativeStart() {
		File file = new File("sample.c");
		int start = -1;
		int end = 3;
		String fileString = Utils.getFileString(file, start, end);
		String expectedfileString = "1. int main()\n" + "2. {\n" + "3. \tint a = 0;\n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string with a very big end line
	 */
	@Test
	public void getFileStringNormalExceedFilel() {
		File file = new File("sample.c");
		int start = -1;
		int end = 300;
		String fileString = Utils.getFileString(file, start, end);
		String expectedfileString = "1. int main()\n" + "2. {\n" + "3. \tint a = 0;\n"
				+ "4. 	return a;\n" + "5. } \n";
		assertEquals(expectedfileString, fileString);
	}

	/**
	 * get file string from a non-existant file
	 */
	@Test
	public void getFileStringNormalNonExistantFile() {
		int start = -1;
		int end = 3;
		File file = new File("DoesNotExist.c");
		String fileString = Utils.getFileString(file, start, end);
		assertEquals("", fileString);
	}

	// Test for forcing IOException - Works only on Windows :/
	// Skipping it ~
	// final RandomAccessFile raFile = new RandomAccessFile(csvFile, "rw");
	// raFile.getChannel().lock();
}