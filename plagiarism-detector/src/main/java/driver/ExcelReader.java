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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.apache.poi.ss.usermodel.WorkbookFactory.create;

/**
 * ExcelReader is able to read from an xlsx file and generate an object
 * that has information about the data in the file
 * @author Darshan Panse
 * @author Shail Shah
 */
public class ExcelReader implements IExcelReader{

	/**
	 * Get a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @param xlsxFile the file containing student information
	 * @return a map in which the key is the ID of the student anf the value is the Student, using an excel file
	 * @throws IOException in case the file reading doesn't go as planned
	 * @throws InvalidFormatException in case the file provided isn't in the correct format
	 */
    public Map<Integer, IStudent> getStudentMap(File xlsxFile) throws IOException, InvalidFormatException {
    	Set<Integer> ids = new HashSet<>();
    	Set<String> emails = new HashSet<>();

    	Map<Integer, IStudent> studentMap = new HashMap<>();
		try(Workbook workbook = create(xlsxFile)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        DataFormatter dataFormatter = new DataFormatter();

	        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
	        	Row row = sheet.getRow(i);
	        	int id;
	        	try {
					id = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
				}
				catch(NumberFormatException e) {
	        		throw new IllegalArgumentException("The id should be an integer. Check row " + i);
				}

				String name = dataFormatter.formatCellValue(row.getCell(1));
				if(name == null || name.isEmpty())
					throw new IllegalArgumentException("Email of Student-" + id + " is incorrect. Check row " + i);

	        	String email = dataFormatter.formatCellValue(row.getCell(2));
	        	if(!MailUtils.isValidEmail(email))
	        		throw new IllegalArgumentException("Email of Student-" + id + " is incorrect. Check row " + i);

	        	if(ids.contains(id))
	        		throw new IllegalArgumentException("Students cannot have the same id. Check row " + i);
	        	if(emails.contains(email))
	        		throw new IllegalArgumentException("Students cannot have the same email. Check row " + i);

	    		studentMap.put(id, new Student(id, name, email));
	    		ids.add(id);
	    		emails.add(email);
	        }
    	}

        return studentMap;
    }
}
