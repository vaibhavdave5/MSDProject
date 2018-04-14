package driver;

import algorithms.Algorithm;
import algorithms.AlgorithmFactory;
import algorithms.IResult;
import controllers.AlgorithmController;
import database.Connect;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import parser.Node;
import utils.ConfigUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the driver of the application. This connects the frontend to the
 * backend.
 * 
 * @author Darshan Panse
 */
public class Driver implements IDriver {
	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());
	private static Driver instance = new Driver();
	private List<String> repoPaths;
	private String hwDir;
	private Map<Integer, IStudent> studentMap;
	private Map<Integer, Collection<File>> studentHWMap = new HashMap<>();
	private ISummary summary;
	private Algorithm algo = Algorithm.LCS;
	private ConfigUtils config = new ConfigUtils(); 
	
	private Driver() {
	}

	/**
	 * @return the single instance of the Driver class.
	 */
	public static IDriver getInstance() {
		return instance;
	}

	/**
	 * Setter for repoPaths.
	 * 
	 * @param repoPaths a list of paths of the repositories
	 */
	public void setRepoPaths(List<String> repoPaths) {
		this.repoPaths = repoPaths;
	}

	/**
	 * Setter for hwDir.
	 * 
	 * @param hwDir the name of the homework directory
	 */
	public void setHWDir(String hwDir) {
		this.hwDir = hwDir;
	}

	/**
	 * Gets the student data from the excel file provided by prof or TA and
	 * stores it as a map in the studentMap.
	 * 
	 * @param xlsxFile the excel file containing student data
	 * @return errorMessage the error message
	 */
	public String getStudentData(File xlsxFile) {
		String errorMessage = "";
		ExcelReader er = new ExcelReader();
		try {
			this.studentMap = er.getStudentMap(xlsxFile);
		} catch (InvalidFormatException | IOException e) {
			errorMessage = "Could not gather students data.";
			return errorMessage;
		}
		return errorMessage;
	}

	/**
	 * Constructs the path to get the c files from the dir recursively.
	 * 
	 * @param repoPath the path of the repository
	 * @return the path of the homework directory
	 */
	public String constructPath(String repoPath) {
		return repoPath + File.separator + this.hwDir;
	}

	/**
	 * Gets the c files recursively from all the directories.
	 * 
	 * @return errorMessage String
	 */
	public String getCodeFiles() {
		String errorMessage = "";

		for (String repoPath : this.repoPaths) {
			try {
				int studentId = Integer.parseInt(repoPath.substring(repoPath.length() - 3));
				File dir = new File(constructPath(repoPath));
				String[] extensions = { "c" };
				Collection<File> listOfFiles = FileUtils.listFiles(dir, extensions, true);

				this.studentHWMap.put(studentId, listOfFiles);
			} catch (NumberFormatException e) {
				errorMessage = config.readConfig("repoError");
				return errorMessage;
			} catch (IllegalArgumentException e) {
				errorMessage = config.readConfig("hwDirError");
				return errorMessage;
			}
		}
		return errorMessage;
	}

	/**
	 * This method compares the similarity between the homework c files of two
	 * students and generates the summary. Also it returns false if the two
	 * students get paired for plagiarism (both high and medium probability).
	 * 
	 * @param fileCollection a collection of files by the first student
	 * @param fileCollection2 a collection of files by the second student
	 * @param sp StudentPair a StudentPair containing information about the two
	 *            students
	 */
	public void computeSimilarityScore(Collection<File> fileCollection1, Collection<File> fileCollection2,
			IStudentPair sp) {
		Double maxScore = getSimilarityScoreList(fileCollection1, fileCollection2).stream().max(Double::compare)
				.orElse(null);

		if (maxScore == null)
			return;

		if (maxScore >= Double.parseDouble(config.readConfig("redThreshold")))
			this.summary.addToRedPairs(sp);
		else if (maxScore >= Double.parseDouble(config.readConfig("yellowThreshold")))
			this.summary.addToYellowPairs(sp);

		if (maxScore >= Double.parseDouble(config.readConfig("yellowThreshold")))
			sp.setSimilarityScore(maxScore);
	}

	/**
	 * Get the similarity scores for all combinations of files from two
	 * different collections
	 * 
	 * @param fileCollection1
	 *            a collection of files
	 * @param fileCollection2
	 *            another collection of files
	 * @return a list similarity scores of all combination of files from the two
	 *         collections
	 */
	private List<Double> getSimilarityScoreList(Collection<File> fileCollection1, Collection<File> fileCollection2) {
		List<Double> similarityScoreList = new ArrayList<>();
		for (File file1 : fileCollection1) {
			AlgorithmController ac = new AlgorithmController();
			Connect.increaseByOne();
			List<Node> list1 = ac.getNodeList(file1);
			for (File file2 : fileCollection2) {
				LOGGER.log(Level.INFO, "File1: {0}", file1.getAbsolutePath());
				LOGGER.log(Level.INFO, "File2: {0}", file2.getAbsolutePath());
				if (this.algo == Algorithm.LCS) {
					similarityScoreList.add(ac.getSimilarityPercentage(new AlgorithmFactory().getAlgo(Algorithm.LCS),
							list1, ac.getNodeList(file2)));
				}
				else if (this.algo == Algorithm.NW) {
					similarityScoreList.add(ac.getSimilarityPercentage(new AlgorithmFactory().getAlgo(Algorithm.NW),
							list1, ac.getNodeList(file2)));
				}
				//
				// This will be replaced by an ML algorithm in the future
				double weightedAverage = Double.parseDouble(config.readConfig("algo1Weight"))
						* ac.getSimilarityPercentage(new AlgorithmFactory().getAlgo(Algorithm.LCS), list1,
								ac.getNodeList(file2))
						+ Double.parseDouble(config.readConfig("algo2Weight")) * ac.getSimilarityPercentage(new AlgorithmFactory().getAlgo(Algorithm.NW), list1,
								ac.getNodeList(file2));
				similarityScoreList.add(weightedAverage);
			}
		}

		return similarityScoreList;
	}

	/**
	 * This method runs the algorithm on the homework c files of all the
	 * selected student-repos and generates the Summary.
	 */
	public void generateSummary() {
		this.summary = new Summary();

		for (Map.Entry<Integer, Collection<File>> entry1 : this.studentHWMap.entrySet()) {
			Integer student1Id = entry1.getKey();
			for (Map.Entry<Integer, Collection<File>> entry2 : this.studentHWMap.entrySet()) {
				if (entry1.getKey() < entry2.getKey()) {
					Collection<File> fileList1 = entry1.getValue();
					Collection<File> fileList2 = entry2.getValue();
					Integer student2Id = entry2.getKey();
					IStudentPair sp = new StudentPair(student1Id, student2Id);
					this.computeSimilarityScore(fileList1, fileList2, sp);
				}
			}

			if (this.summary.isSafe(student1Id)) {
				this.summary.addToGreenIds(student1Id);
			}
		}
	}

	/**
	 * Employs a comparison strategy and compares all files of all students.
	 * 
	 * @param repoPaths
	 *            a list of repository paths
	 * @param hwDir
	 *            the name of the homework directory that's supposed to be in
	 *            each repository
	 * @return errorMessage an error message, if applicable
	 */
	public String checkForPlagiarism(List<String> repoPaths, String hwDir, Algorithm algo) {
		if (repoPaths != null && hwDir != null && hwDir != "") {
			this.algo = algo;
			this.setRepoPaths(repoPaths);
			this.setHWDir(hwDir);
			String message = this.getCodeFiles();
			this.generateSummary();
			return message;
		} else {
			return "No student repository or homework directory selected";
		}
	}

	/**
	 * This methods returns the Summary generated by the method
	 * generateSummary()
	 * 
	 * @return the summary of the run
	 */
	public ISummary viewSummary() {
		return this.summary;
	}

	/**
	 * This method compares all the HW files of two students and returns the
	 * result of the plagiarism algorithm along with the snippets data.
	 * 
	 * @param fileCollection1 a collection of files
	 * @param fileCollection2 another collection of files
	 * @return a list of FilePairs, with each entry containing the result of the
	 *         algorithm for two files from the two collections
	 */
	public List<IFilePair> compareFilesForResult(Collection<File> fileCollection1, Collection<File> fileCollection2) {
		List<IFilePair> filePairList = new ArrayList<>();
		for (File file1 : fileCollection1) {
			AlgorithmController ac = new AlgorithmController();
			List<Node> list1 = ac.getNodeList(file1);
			for (File file2 : fileCollection2) {
				FilePair fp = new FilePair(file1, file2);
				IResult result1;
				IResult result2;
				
					result1 = ac.getResult(new AlgorithmFactory().getAlgo(Algorithm.LCS), list1,
							ac.getNodeList(file2));
					result2 = ac.getResult(new AlgorithmFactory().getAlgo(Algorithm.NW), list1,
							ac.getNodeList(file2));
				
				
				fp.setResult1(result1);
				fp.setResult2(result2);
				filePairList.add(fp);
			}
		}

		return filePairList;
	}

	/**
	 * This method generates the snippet data for the HW files of two students
	 * and returns the snippet data.
	 * 
	 * @param student1Id Integer
	 * @param student2Id Integer
	 * @return codeSnippet CodeSnippet
	 */
	public ICodeSnippets generateSnippet(Integer student1Id, Integer student2Id) {
		Collection<File> student1FileList = this.studentHWMap.get(student1Id);
		Collection<File> student2FileList = this.studentHWMap.get(student2Id);
		CodeSnippets codeSnippets = new CodeSnippets(student1Id, student2Id);
		List<IFilePair> fpList = this.compareFilesForResult(student1FileList, student2FileList);
		codeSnippets.setFilePairList(fpList);
		return codeSnippets;
	}

	/**
	 * @return the studentHWMap, containing StudentId as key and the submission
	 *         files as value
	 */
	public Map<Integer, Collection<File>> getStudentHWMap() {
		return studentHWMap;
	}

	/**
	 * Get the name of the student by his/her Id
	 * 
	 * @param studentId the Id of the student
	 * @return the name of the student
	 */
	public String getNameById(Integer studentId) {
		return studentMap.get(studentId).getName();
	}

	/**
	 * Get the email of the student by his/her Id
	 * @param studentId the Id of the student
	 * @return the email of the student
	 */
	public String getEmailById(Integer studentId) {
		return studentMap.get(studentId).getEmail();
	}

	/**
	 * reset the state of the application
	 */
	public void resetState() {
		this.setHWDir(null);
		this.setRepoPaths(null);
		this.studentHWMap = new HashMap<>();
		this.summary = null;
	}
}
