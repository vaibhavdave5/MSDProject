package driver;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import algorithms.Algorithm;
import algorithms.LCSAlgorithm;
import algorithms.NeemanWalshAlgorithm;
import algorithms.IResult;
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
	private Map<Integer, Student> studentMap;
	private Map<Integer, Collection<File>> studentHWMap = new HashMap<>();
	private Summary summary;
	private Algorithm algo = Algorithm.LCS;

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
	 * @param xlsxFile File
	 * @return errorMessage String
	 */
	public String getStudentData(File xlsxFile) {
		String errorMessage = "";
		ExcelReader er = new ExcelReader();
		try {
			this.studentMap = er.getStudentMap(xlsxFile);
		}
		catch (InvalidFormatException | IOException e) {
			errorMessage = "Could not gather students data.";
			return errorMessage;
		}
		return errorMessage;
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
	 * @return errorMessage String
	 */
	public String getCodeFiles() {
		String errorMessage = "";
		String path = null;
		Integer studentId = 0;
		for(String repoPath: this.repoPaths) {
			try {
				studentId = Integer.parseInt(repoPath.substring(repoPath.length() - 3));
				path = constructPath(repoPath);
				File dir = new File(path);
				String[] extensions = {"c"};
				Collection<File> listOfFiles = FileUtils.listFiles(dir, extensions, true);
				
				this.studentHWMap.put(studentId, listOfFiles);
			}
			catch(NumberFormatException e) {
				errorMessage = "One of the selected directories is not a student repository"; 
				return errorMessage;
			}
			catch(IllegalArgumentException e) {
				errorMessage = "The homework directory specified is incorrect";
				return errorMessage;
			}
		}
		return errorMessage;
	}
	
	/**
	 * This method returns the max similarity score between all the HW files of
	 * two students.
	 * @param similarityScoreList List<Double>
	 * @return maxSimilarityScore Double
	 */
	public double maxSimilarityScore(List<Double> similarityScoreList) {
		double maxSimilarityScore = 0.0;
		  if(!similarityScoreList.isEmpty()) {
		    for(Double score : similarityScoreList) {
		    	LOGGER.log(Level.INFO, "Score: {0}", score);
		    	if(score > maxSimilarityScore) maxSimilarityScore = score;
		    }
		    return maxSimilarityScore;
		  }
		  return maxSimilarityScore;
	}
	
	
	/**
	 * This method compares the similarity between the homework c files of two students
	 * and generates the summary. Also it returns false if the two students get paired
	 * for plagiarism (both high and medium probability).
	 * @param fileList1 Collection<File>
	 * @param fileList2 Collection<File>
	 * @param sp StudentPair
	 */
	public void compareFiles(Collection<File> fileList1, Collection<File> fileList2, StudentPair sp) {
		List<Double> similarityScoreList = new ArrayList<>();
		for(File file1: fileList1) {
			for(File file2: fileList2) {
				LOGGER.log(Level.INFO, "File1: {0}", file1.getAbsolutePath());
				LOGGER.log(Level.INFO, "File2: {0}", file2.getAbsolutePath());
				AlgorithmController ac = new AlgorithmController(file1, file2);
				if(this.algo == Algorithm.LCS) {
					similarityScoreList.add(ac.getAns(new LCSAlgorithm()));
				} else if(this.algo == Algorithm.NW) {
					similarityScoreList.add(ac.getAns(new NeemanWalshAlgorithm()));
				} else {
					// This will be replaced by an ML algorithm in the next sprint
					double weightedAverage = 0.25 * ac.getAns(new NeemanWalshAlgorithm())
											+ 0.75 * ac.getAns(new LCSAlgorithm());
					similarityScoreList.add(weightedAverage);
				}
			}
		}
		
		double maxScore = this.maxSimilarityScore(similarityScoreList);
		LOGGER.log(Level.INFO, "Avg Score: {0}", maxScore);
		if(maxScore >= 0.5) {
			// send student pair to red list
			sp.setSimilarityScore(maxScore);
			this.summary.setRed(sp);
		} else if(maxScore >= 0.3 && maxScore < 0.5) {
			// send student pair to yellow list
			sp.setSimilarityScore(maxScore);
			this.summary.setYellow(sp);
		} else {
			// do nothing
		}
	}
	
	
	/**
	 * This method runs the algorithm on the homework c files of all the 
	 * selected student-repos and generates the Summary.
	 */
	public void generateSummary() {
		this.summary = new Summary();
		
		for(Map.Entry<Integer, Collection<File>> entry1 : this.studentHWMap.entrySet()) {
			Integer student1Id = entry1.getKey();
			for(Map.Entry<Integer, Collection<File>> entry2: this.studentHWMap.entrySet()) {
				if(entry1.getKey() < entry2.getKey()) {
					Collection<File> fileList1 = entry1.getValue();
					Collection<File> fileList2 = entry2.getValue();
					Integer student2Id = entry2.getKey();
					StudentPair sp = new StudentPair(student1Id, student2Id);
					this.compareFiles(fileList1, fileList2, sp);
				}
			}
			
			if(this.summary.isSafe(student1Id)) {
				this.summary.setGreen(student1Id);
			}
		}
	}
	
	/**
	 * Employs a comparison strategy and compares all files of all students.
	 * @param repoPaths List<String>
	 * @param hwDir String
	 * @return errorMessage String
	 */
	public String checkForPlagiarism(List<String> repoPaths, String hwDir, Algorithm algo) {
		if(repoPaths != null && hwDir != null && hwDir != "") {
			this.algo = algo;
			this.setRepoPaths(repoPaths);
			this.setHWDir(hwDir);
			String message = this.getCodeFiles();
			this.generateSummary();
			return message;
		}
		else {
			return "No student repository or homework directory selected";
		}
	}
	
	/**
	 * This methods returns the Summary generated by the method generateSummary()
	 * @return Summary
	 */
	public Summary viewSummary() {
		return this.summary;
	}
	
	/**
	 * This method compares all the HW files of two students and returns the result
	 * of the plagiarism algorithm along with the snippets data.
	 * @param fileList1 List<File>
	 * @param fileList2 List<File>
	 * @return List<SimpleFilePair>
	 */
	public List<FilePair> compareFilesForResult(Collection<File> fileList1, Collection<File> fileList2) {
		List<FilePair> filePairList = new ArrayList<>();
		for(File file1: fileList1) {
			for(File file2: fileList2) {
				FilePair fp = new FilePair(file1, file2);
				AlgorithmController ac = new AlgorithmController(file1, file2);
				IResult result = ac.getResult(new LCSAlgorithm());
				fp.setResult(result);
				filePairList.add(fp);
			}
		}
		
		return filePairList;
	}
	
	/**
	 * This method generates the snippet data for the HW files of two students 
	 * and returns the snippet data.
	 * @param student1Id Integer
	 * @param student2Id Integer
	 * @return codeSnippet CodeSnippet
	 */
	public CodeSnippets generateSnippet(Integer student1Id, Integer student2Id) {
		Collection<File> student1FileList = this.studentHWMap.get(student1Id);
		Collection<File> student2FileList = this.studentHWMap.get(student2Id);
		CodeSnippets codeSnippets = new CodeSnippets(student1Id, student2Id);
		List<FilePair> fpList = this.compareFilesForResult(student1FileList, student2FileList);
		codeSnippets.setFilePairList(fpList);
		return codeSnippets;
	}

	/**
	 * Getter for studentHWMap. This method is made for testing purposes.
	 * @return studentHWMap Map<Integer, Collection<File>>
	 */
	public Map<Integer, Collection<File>> getStudentHWMap() {
		return studentHWMap;
	}
	
	/**
	 * This method returns the name of the student given the id of the student.
	 * @param studentId Integer
	 * @return String
	 * @param studentId
	 * @return String
	 */
	public String getNameById(Integer studentId) {
		Student student = studentMap.get(studentId);
		return student.getName();
	}
	
}
