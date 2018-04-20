package algorithms;

/**
 * A SnippetPair represents a pair of similar snippets
 * @author Shail Shah
 */
public class SnippetPair {
	private String fileName1;
	private String fileName2;

	private String snippet1Above;
	private String snippet1;
	private String snippet1Below;
	private String snippet2Above;
	private String snippet2;
	private String snippet2Below;

	private double percentage1;
	private double percentage2;

	/**
	 * Constructor for making a new SnippetPair
	 * @param fileName1 the name of the file that contains the first snippet
	 * @param fileName2 the name of the file that contains the second snippet
	 * @param snippet1 the text of the first snippet
	 * @param snippet2 the text of the second snippet
	 */
	public SnippetPair(String fileName1, 
					  String fileName2, 
					  String snippet1Above, 
					  String snippet1, 
					  String snippet1Below, 
					  String snippet2Above,
					  String snippet2,
					  String snippet2Below,
					  double percentage1, 
					  double percentage2) {
		if(fileName1 == null || fileName2 == null || snippet1 == null || snippet2 == null)
			throw new IllegalArgumentException();
		this.fileName1 = fileName1;
		this.fileName2 = fileName2;
		this.snippet1Above = snippet1Above;
		this.snippet1 = snippet1;
		this.snippet1Below = snippet1Below;
		this.snippet2Above = snippet2Above;
		this.snippet2 = snippet2;
		this.snippet2Below = snippet2Below;
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
	 * @return the text of the code above the first snippet
	 */
	public String getSnippet1Above() {
		return snippet1Above;
	}

	/**
	 * @return the text of the first snippet
	 */
	public String getSnippet1() {
		return snippet1;
	}
	
	/**
	 * @return the text of the code below the first snippet
	 */
	public String getSnippet1Below() {
		return snippet1Below;
	}
	
	/**
	 * @return the text of the code above the second snippet
	 */
	public String getSnippet2Above() {
		return snippet2Above;
	}

	/**
	 * @return the text of the second snippet
	 */
	public String getSnippet2() {
		return snippet2;
	}
	
	/**
	 * @return the text of the code below the second snippet
	 */
	public String getSnippet2Below() {
		return snippet2Below;
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
