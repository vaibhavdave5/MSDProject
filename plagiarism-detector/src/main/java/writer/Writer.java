package writer;

import java.io.*;
import java.util.logging.Logger;

/**
 * A Writer is used for writing to a file and logging output
 * @author Vaibhav Dave, Shail Shah
 *
 */
public class Writer {
	private static BufferedWriter bufferedWriter;
	private static Logger logger = Logger.getLogger("ErrorLog");

	private Writer() {}

	public static BufferedWriter getWriter() {
		if (bufferedWriter == null) {
			try {
				bufferedWriter = new BufferedWriter(new FileWriter("Report.txt"));
			} catch (IOException e) {
				logger.log(null, "Runtime Error", e);
			}
		}
		return bufferedWriter;
	}
	
	public void write(String str) throws IOException {
		bufferedWriter.write(str);
	}

	public static void closeWriter() {
		if (bufferedWriter != null) {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				logger.log(null, "Runtime Error", e);
			}
			bufferedWriter = null;
		}
	}

}
