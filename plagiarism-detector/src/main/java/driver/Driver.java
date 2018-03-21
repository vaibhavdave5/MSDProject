package driver;

import java.io.*;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Driver {
	private static Driver instance = new Driver();
	
	private Driver() {}
	
	public static Driver getInstance() {
		return instance;
	}
	
	private List<String> repoPaths;
	private String hwDir;
	private static Map<Integer, Student> studentMap;
//	
	public void setRepoPaths(List<String> repoPaths) {
		this.repoPaths = repoPaths;
	}
	
	public void setHWDir(String hwDir) {
		this.hwDir = hwDir;
	}
	
	public static void getStudentData(String xlsPath) {
		ExcelReader er = new ExcelReader();
		try {
			studentMap = er.getStudentMap(xlsPath);
		} 
		catch (InvalidFormatException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
