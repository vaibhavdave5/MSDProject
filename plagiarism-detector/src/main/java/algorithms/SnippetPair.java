package algorithms;

/**
 * A SnippetPair represents a pair of similar snippets
 * @author Shail Shah
 */
public class SnippetPair {
	private String fileName1;
	private String fileName2;

	private String snippet1;
	private String snippet2;

	private double percentage1;
	private double percentage2;

	/**
	 * Constructor for making a new SnippetPair
	 * @param fileName1 the name of the file that contains the first snippet
	 * @param fileName2 the name of the file that contains the second snippet
	 * @param snippet1 the text of the first snippet
	 * @param snippet2 the text of the second snippet
	 */
	public SnippetPair(String fileName1, String fileName2, String snippet1, String snippet2, double percentage1, double percentage2) {
		if(fileName1 == null || fileName2 == null || snippet1 == null || snippet2 == null)
			throw new IllegalArgumentException();
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.snippet1 = snippet1;
		this.snippet2 = snippet2;
		this.percentage1 = percentage1;
		this.percentage2 = percentage2;
	}

	/**
	 *
	 * @return the name of the file that contains the first snippet
	 */
	public String getFileName1() {
		return fileName1;
	}

	/**
	 * @return the name of the file that contains the second snippet
	 */
	public String getFileName2() {
		return fileName2;
	}

	/**
	 * @return the text of the first snippet
	 */
	public String getSnippet1() {
		return snippet1;
	}

	/**
	 * @return the text of the second snippet
	 */
	public String getSnippet2() {
		return snippet2;
	}

	/**
	 * @return the percentage of first file
	 */
	public double getPercentage1() {
		return this.percentage1;
	}

	/**
	 * @return the percentage of second file
	 */
	public double getPercentage2() {
		return this.percentage2;
	}
}
