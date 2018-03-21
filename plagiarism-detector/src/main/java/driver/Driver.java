package driver;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Driver {
	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
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
		catch (InvalidFormatException | IOException e) {
			LOGGER.log(Level.INFO, e.getMessage());
		}
	}
	
	public String constructPath(String repoPath) {
		return repoPath + "/" + this.hwDir;
	}
	
	public void getCodeFiles() {
		String path = null;
		int studentId = 0;
		for(String repoPath: this.repoPaths) {
			LOGGER.log(Level.INFO, repoPath);
			path = constructPath(repoPath);
			
			studentId = Integer.parseInt(repoPath.substring(repoPath.length() - 3));
			
			File dir = new File(path);
			String[] extensions = {"c"};
			Collection<File> listOfFiles = FileUtils.listFiles(dir, extensions, true);
			
			LOGGER.log(Level.INFO, "{0}", studentId);
			for(File file: listOfFiles) {
				LOGGER.log(Level.INFO, file.getAbsolutePath());
			}
		}
	}
}
