package utils;

import algorithms.IResult;
import algorithms.SimilaritySnippet;
import driver.CodeSnippets;
import driver.Driver;
import driver.FilePair;
import org.apache.log4j.Logger;

import java.io.*;
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

				sb.append(count + ". " + line);
				sb.append("\n");
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
	 * Return a report of two susupicious submissions based on the code snippets
	 * @param codeSnippets code snippets of two students
	 * @return a String containing the
	 */
	public static String getReport(CodeSnippets codeSnippets) {
		StringBuilder sb = new StringBuilder();

		Driver driver = Driver.getInstance();

		String studentName1 = driver.getNameById(codeSnippets.getStudent1Id());
		String studentName2 = driver.getNameById(codeSnippets.getStudent2Id());



		sb.append(new SimpleDateFormat("yyyy-MM-dd",
				Locale.getDefault()).format(new Date()) + "\n");

		sb.append("Report for " + studentName1 + " and " + studentName2 + "\n");

		List<FilePair> filePairs = codeSnippets.getFilePairList();
		filePairs.forEach(fp -> {
			File file1 = fp.getFile1();
			File file2 = fp.getFile2();
			sb.append(file1.getName() + " and " + file2.getName() + " are suspected to be similar. \n");
			IResult result = fp.getResult();
			sb.append("There is a " + result.getPercentage()*100 + "% match.\n");
			Set<SimilaritySnippet> snippets = result.generateSnippet();
			snippets.forEach(s -> {
				int start1 = s.getStart1();
				int end1 = s.getEnd1();
				int start2 = s.getStart2();
				int end2 = s.getEnd2();
				sb.append("Student A's " + file1.getName() + "\n");
				sb.append(getFileString(file1, start1, end1));

				sb.append("Student B's " + file2.getName() + "\n");
				sb.append(getFileString(file2, start2, end2));
			});
		});

		return sb.toString();
	}
}
