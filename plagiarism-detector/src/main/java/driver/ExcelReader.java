package driver;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public Map<Integer, Student> getStudentMap(String xlsxPath) throws IOException, InvalidFormatException {
    	Map<Integer, Student> studentMap = new HashMap<>();
    	Workbook workbook = null;
    	try {
    		workbook = WorkbookFactory.create(new File(xlsxPath));
	        Sheet sheet = workbook.getSheetAt(0);
	        DataFormatter dataFormatter = new DataFormatter(); 
	        
	        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
	        	Row row = sheet.getRow(i);
	        	int studentId = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
	    		String studentName = dataFormatter.formatCellValue(row.getCell(1));
	    		String studentEmail = dataFormatter.formatCellValue(row.getCell(2));
	    		Student s = new Student(studentId, studentName, studentEmail);
	    		studentMap.put(studentId, s);
	        }	
    	}
        finally {
        	if(workbook != null) workbook.close();
        }
        
        
        return studentMap;
    }
}
