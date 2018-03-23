package utils;

import java.io.*;

/**
 * Handy utilities that will be useful throughout the project
 * @author Shail Shah
 */
public class Utils {
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
}
