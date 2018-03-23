package utils;

import algorithms.Result;
import algorithms.SimilaritySnippet;
import driver.CodeSnippets;
import driver.FilePair;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Handy file utilities that will be useful throughout the project
 * @author Shail Shah
 */
public class FileUtils {
	/**
	 * Converts a file from the given start and end lines to its String equivalent
	 * @param file a source file
	 * @param start the starting line
	 * @param end the ending line
	 * @return a String of the provided line, from the start to end lines (inclusive)
	 */
	public static String getFileString(File file, int start, int end) {
		start = start >= 1 ? start : 1;

		StringBuffer sb = new StringBuffer();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;

				if(count < start) {
					continue;
				}

				if(count > end) {
					break;
				}

				sb.append(count + ". " + line);
				sb.append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public String getReport(CodeSnippets codeSnippets) {
		StringBuffer sb = new StringBuffer();

		List<FilePair> filePairs = codeSnippets.getFilePairList();
		filePairs.forEach(fp -> {
			File file1 = fp.getFile1();
			File file2 = fp.getFile2();
			sb.append(file1.getName() + " and " + file2.getName() + " are suspected to be similar. \n");
			Result result = fp.getResult();
			sb.append("There is a " + result.getPercentage()*100 + "% match.\n");
			Set<SimilaritySnippet> snippets = result.getSnippets();
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
