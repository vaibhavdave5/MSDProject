package machinelearning;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static org.apache.poi.ss.usermodel.WorkbookFactory.create;

import java.io.File;
import java.io.IOException;

public class TrainDataElicitor {
	File xlsxFile;
	
	public TrainDataElicitor(File xlsxFile) {
		this.xlsxFile = xlsxFile;
	}
	
	public TrainData getTrainData() throws InvalidFormatException, IOException {
		double[] y = new double[18];
        double[][] x = new double[18][2];
        
		try(Workbook workbook = create(this.xlsxFile)) {
	        Sheet sheet = workbook.getSheetAt(0);
	        DataFormatter dataFormatter = new DataFormatter();
	        
	        for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
	        	Row row = sheet.getRow(i);
	        	try {
	        		x[i][0] = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(0)));
		        	x[i][1] = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(1)));
		        	y[i] = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(2)));
	        	} catch(NumberFormatException e) {
	    			throw new IllegalArgumentException("The id should be a double. Check row " + i);
	    		}
	        }
		}
		
		return new TrainData(x, y);
	}
}
