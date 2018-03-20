package driver;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public Map<Integer, Student> getStudentMap(String xlsxPath) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(xlsxPath));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        Map<Integer, Student> studentMap = new HashMap<>(); 
//        boolean isFirst = true;
        
        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
        	Row row = sheet.getRow(i);
        	int studentId = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
    		String studentName = dataFormatter.formatCellValue(row.getCell(1));
    		String studentEmail = dataFormatter.formatCellValue(row.getCell(2));
    		Student s = new Student(studentId, studentName, studentEmail);
    		studentMap.put(studentId, s);
        }
        
//        for (Row row: sheet) {
//        	if(isFirst) continue;
//    		int studentId = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
//    		String studentName = dataFormatter.formatCellValue(row.getCell(1));
//    		String studentEmail = dataFormatter.formatCellValue(row.getCell(2));
//    		Student s = new Student(studentId, studentName, studentEmail);
//    		studentMap.put(studentId, s);
//    		isFirst = false;
//        }

        // Closing the workbook
        workbook.close();
        
        return studentMap;
    }
}
