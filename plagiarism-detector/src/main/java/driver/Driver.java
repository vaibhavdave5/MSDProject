package driver;

import java.io.*;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Driver {
	private static Driver instance = new Driver();
	
	private Driver() {}
	
	public static Driver getInstance() {
		if(instance == null) return new Driver();
		else return instance;
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
	
	public void extractFiles() throws IOException {
		for(String repoPath: this.repoPaths) {
			int id = Integer.parseInt(repoPath.substring(-3));
			String hwPath = this.repoPaths + "/"+ this.hwDir;
			FileReader file = new FileReader(hwPath);
			
		}
	}
	
	
	void getStudentData() {
		final String xlsPath = "./studentData.xlsx";
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
