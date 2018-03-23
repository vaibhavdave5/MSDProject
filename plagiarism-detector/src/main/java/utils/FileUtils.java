package utils;

import org.apache.log4j.Logger;
//import scratch.Scratch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handy utilities that will be useful throughout the project
 * @author Shail Shah
 */
public class FileUtils {

	final static Logger logger = Logger.getLogger(FileUtils.class);

	private FileUtils(){};
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
			br.close();
		}catch (IOException e) {
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
}
