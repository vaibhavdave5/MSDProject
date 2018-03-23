package algorithms;

/**
 * A SimpleFilePair represents a pair of files
 * @author Shail Shah
 */
public class SimpleFilePair {
	private String fileName1;
	private String fileName2;

	private String snippet1;
	private String snippet2;

	public SimpleFilePair(String fileName1, String fileName2, String snippet1, String snippet2) {
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.snippet1 = snippet1;
		this.snippet2 = snippet2;
	}

	public String getFileName1() {
		return fileName1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public String getSnippet1() {
		return snippet1;
	}


	public String getSnippet2() {
		return snippet2;
	}
}
