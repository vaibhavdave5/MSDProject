package driver;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * This interface provides a contract for the ExcelReader class. The classes implementing
 * this read student data from an excel workbook.
 * @author darshan.panse
 *
 */
public interface IExcelReader {
	/**
	 * Get a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @param xlsxFile the file containing student information
	 * @return a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @throws IOException in case the file reading doesn't go as planned
	 * @throws InvalidFormatException in case the file provided isn't in the correct format
	 */
	Map<Integer, Student> getStudentMap(File xlsxFile) throws IOException, InvalidFormatException;
}
