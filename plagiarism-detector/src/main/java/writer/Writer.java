package writer;

import java.io.*;
import java.util.logging.Logger;

/**
 * A Writer is used for writing to a file and logging output
 * @author Vaibhav Dave
 *
 */
public class Writer {
	private static BufferedWriter write;
	private static Logger logger = Logger.getLogger("ErrorLog");

	private Writer() {
	}

	public static BufferedWriter getWriter() {
		if (write == null) {
			try {
				write = new BufferedWriter(new FileWriter("Report.txt"));
			} catch (IOException e) {
				logger.log(null, "Runtime Error", e);
			}
		}
		return write;
	}

	public static void closeWriter() {
		if (write != null) {
			try {
				write.close();
			} catch (IOException e) {
				logger.log(null, "Runtime Error", e);
			}
			write = null;
		}
	}

}
