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

	/**
	 * Constructor for making a new SnippetPair
	 * @param fileName1 the name of the file that contains the first snippet
	 * @param fileName2 the name of the file that contains the second snippet
	 * @param snippet1 the text of the first snippet
	 * @param snippet2 the text of the second snippet
	 */
	public SnippetPair(String fileName1, String fileName2, String snippet1, String snippet2) {
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.snippet1 = snippet1;
		this.snippet2 = snippet2;
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
}
