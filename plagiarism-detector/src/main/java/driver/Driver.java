package driver;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import algorithms.LCSAlgorithm;
import controllers.AlgorithmController;

/**
 * 
 * @author darshan.panse
 * This is the driver of the application. This connects the frontend to the backend.
 */
public class Driver {
	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
	private static Driver instance = new Driver();
	
	private Driver() {}
	
	/**
	 * 
	 * @return the instance of the Driver class.
	 */
	public static Driver getInstance() {
		return instance;
	}
	
	private List<String> repoPaths;
	private String hwDir;
	private static Map<Integer, Student> studentMap;
	private Map<Integer, Collection<File>> studentHWMap;

	/**
	 * Setter for repoPaths.
	 * @param repoPaths List<String>
	 */
	public void setRepoPaths(List<String> repoPaths) {
		this.repoPaths = repoPaths;
	}
	
	/**
	 * Setter for hwDir.
	 * @param hwDir String
	 */
	public void setHWDir(String hwDir) {
		this.hwDir = hwDir;
	}
	
	/**
	 * Gets the student data from the excel file provided by prof or TA
	 * and stores it as a map in the studentMap.
	 * @param xlsPath String
	 */
	public static void getStudentData(String xlsPath) {
		ExcelReader er = new ExcelReader();
		try {
			studentMap = er.getStudentMap(xlsPath);
		}
		catch (InvalidFormatException | IOException e) {
			LOGGER.log(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * Constructs the path to get the c files from the dir recursively.
	 * @param repoPath String
	 * @return String
	 */
	public String constructPath(String repoPath) {
		return repoPath + File.separator + this.hwDir;
	}
	
	/**
	 * Gets the c files recursively from all the directories.
	 */
	public void getCodeFiles() {
		String path = null;
		int studentId = 0;
		for(String repoPath: this.repoPaths) {
			LOGGER.log(Level.INFO, repoPath);
			
			studentId = Integer.parseInt(repoPath.substring(repoPath.length() - 3));
			
			path = constructPath(repoPath);
			File dir = new File(path);
			String[] extensions = {"c"};
			Collection<File> listOfFiles = FileUtils.listFiles(dir, extensions, true);
			
			this.studentHWMap.put(studentId, listOfFiles);
		}
	}
	
	/**
	 * Employs a comparison strategy and compares all files of all students.
	 * @param repoPaths List<String>
	 * @param hwDir String
	 */
	public void checkForPlagiarism(List<String> repoPaths, String hwDir) {
		this.setRepoPaths(repoPaths);
		this.setHWDir(hwDir);
		this.getCodeFiles();
		
		for(Map.Entry<Integer, Collection<File>> entry1 : this.studentHWMap.entrySet()) {
			for(Map.Entry<Integer, Collection<File>> entry2: this.studentHWMap.entrySet()) {
				if(entry1.getKey() < entry2.getKey()) {
					Collection<File> fileList1 = entry1.getValue();
					Collection<File> fileList2 = entry2.getValue();
					for(File file1: fileList1) {
						for(File file2: fileList2) {
							AlgorithmController ac = new AlgorithmController(file1, file2);
							double similarityScore = ac.getAns(new LCSAlgorithm()); 
							if(similarityScore >= 50) {
								// send student pair to red list
								StudentPair sp = new StudentPair(entry1.getKey(), entry2.getKey(), similarityScore);
								
							} else if(similarityScore >= 30 && similarityScore < 50) {
								// send student pair to yellow list
								StudentPair sp = new StudentPair(entry1.getKey(), entry2.getKey(), similarityScore);
							} else {
								// do nothing
							}
						}
					}
				}
			}
		}
	}
}
