package utils;

import algorithms.IResult;
import algorithms.SimilaritySnippet;
import driver.Driver;
import driver.ICodeSnippets;
import driver.IDriver;
import driver.IFilePair;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Handy utils that will be useful throughout the project
 * @author Shail Shah
 */
public class FileUtils {

	private static final Logger logger = Logger.getLogger(FileUtils.class);

	private FileUtils(){}

	/**
	 * Converts a file from the given start and end lines to its String equivalent
	 * @param file a source file
	 * @param start the starting line
	 * @param end the ending line
	 * @return a String of the provided line, from the start to end lines (inclusive)
	 */
	public static String getFileString(File file, int start, int end) {
		int startOffset = start >= 1 ? start : 1;
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;

				if (count < startOffset) {
					continue;
				}

				if (count > end) {
					return sb.toString();
				}

				sb.append(count)
						.append(". ")
						.append(line)
						.append("\n");
			}
		} catch (IOException e) {
			logger.error("IOException");
		}

		return sb.toString();
	}

	/**
	 * Writes the given message in the given path
	 * @param filePath the path of the file to write
	 * @param message the message to print in the file
	 * @throws IOException such as FileNotFoundException
	 */
	public static void writeToFile(String filePath, String message) throws IOException {
		Files.write(Paths.get(filePath), message.getBytes());
	}

	/**
	 * Return a report of two suspicious submissions based on the code snippets
	 * @param codeSnippets code snippets of two students
	 * @return a String containing the
	 */
	public static String getReport(ICodeSnippets codeSnippets) {
		StringBuilder sb = new StringBuilder();
		IDriver driver = Driver.getInstance();

		String studentName1 = driver.getNameById(codeSnippets.getStudent1Id());
		String studentName2 = driver.getNameById(codeSnippets.getStudent2Id());
		String date = new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault()).format(new Date());

		sb.append(date)
				.append("\n")
				.append("Report for ")
				.append(studentName1)
				.append(" and ")
				.append(studentName2)
				.append("\n")
				.append(getReportFilePairs(codeSnippets.getFilePairList()));

		return sb.toString();
	}

	/**
	 * Generate the report, given a list of file pairs
	 * @param filePairs a list of file pairs
	 * @return a string that contains the report for the given file pair
	 */
	private static String getReportFilePairs(List<IFilePair> filePairs){
		StringBuilder sb = new StringBuilder();

		filePairs.forEach(fp -> {
			File file1 = fp.getFile1();
			File file2 = fp.getFile2();

			sb.append(file1.getName())
					.append(" and ")
					.append(file2.getName())
					.append(" are suspected to be similar. \n")
					.append(getReportResult(fp.getResult(), file1, file2));
		});

		return sb.toString();
	}

	/**
	 * Get the report, given an IResult object and two files
	 * @param result an IResult file containing similarity information
	 * @param file1 a file
	 * @param file2 another file
	 * @return a string representing the report of the two files
	 */
	private static String getReportResult(IResult result, File file1, File file2) {
		return "File1 match: " +result.getPercentagefile1()*100
				+ "%\nFile2 match:" + result.getPercentagefile2()*100 + "%\n" +
				getReportSnippets(result.generateSnippet(), file1, file2);
	}

	/**
	 * Get the report, given a set of snippets and the files they belong to
	 * @param snippets a set of snippets
	 * @param file1 the first file
	 * @param file2 the second file
	 * @return a string representing the report
	 */
	private static String getReportSnippets(Set<SimilaritySnippet> snippets, File file1, File file2) {
		StringBuilder sb = new StringBuilder();

		snippets.forEach(s -> {
			int start1 = s.getStart1();
			int end1 = s.getEnd1();
			int start2 = s.getStart2();
			int end2 = s.getEnd2();

			sb.append("Student A's ")
					.append(file1.getName())
					.append("\n")
					.append(getFileString(file1, start1, end1))
					.append("Student B's ")
					.append(file2.getName())
					.append("\n")
					.append(getFileString(file2, start2, end2))
					.append("--------------------\n");
		});

		return sb.toString();
	}
}
