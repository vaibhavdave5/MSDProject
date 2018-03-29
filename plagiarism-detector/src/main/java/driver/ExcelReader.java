package driver;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import utils.MailUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.poi.ss.usermodel.WorkbookFactory.create;

/**
 * ExcelReader is able to read from an xlsx file and generate an object
 * that has information about the data in the file
 * @author Darshan Panse
 * @author Shail Shah
 */
public class ExcelReader {

	/**
	 * Get a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @param xlsxFile the file containing student information
	 * @return a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @throws IOException in case the file reading doesn't go as planned
	 * @throws InvalidFormatException in case the file provided isn't in the correct format
	 */
    public Map<Integer, Student> getStudentMap(File xlsxFile) throws IOException, InvalidFormatException {
    	Map<Integer, Student> studentMap = new HashMap<>();
		try(Workbook workbook = create(xlsxFile)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        DataFormatter dataFormatter = new DataFormatter();

	        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
	        	Row row = sheet.getRow(i);
	        	int id = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
	        	String name = dataFormatter.formatCellValue(row.getCell(1));
	        	String email = dataFormatter.formatCellValue(row.getCell(2));
	        	if(!MailUtils.isValidEmail(email))
	        		throw new IllegalArgumentException("Email of Student-" + id + " is incorrect");
	        	Student student = new Student(id, name, email);
	    		studentMap.put(id, student);
	        }
    	}

        return studentMap;
    }
}
