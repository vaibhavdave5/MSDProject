package algorithms;

import parser.Node;

/**
 * This class gives an object representing similar code 
 * snippets that are detected by the algorithm 
 * @author Vaibhav Dave
 * @since 03/20/2018
 *
 */
public class SimilaritySnippet implements Comparable<SimilaritySnippet> {
	private Node node1;
	private Node node2;
	
	public SimilaritySnippet(Node node1, Node node2) {
		this.node1 = node1;
		this.node2 = node2;
	}

	/**
	 * This is the start of code snippet in file 1
	 * @return int start
	 */
	public int getStart() {
		return node1.getStart();
	}


	/**
	 * This is the start of code snippet in file 2
	 * @return int start2
	 */
	public int getStart2() {
		return node2.getStart();
	}
	

	/**
	 * This is the end of code snippet in file 1
	 * @return int end
	 */
	public int getEnd() {
		return node1.getEnd();
	}


	/**
	 * This is the end of code snippet in file 2
	 * @return int end2
	 */
	public int getEnd2() {
		return node2.getEnd();
	}
	
	/**
	 * This function compares two snippets on the 
	 * basis of the starts of first file 
	 * This is done to maintain an order.
	 * @param SimilaritySnippet o
	 * @return int
	 */
	@Override
	public int compareTo(SimilaritySnippet o) {
		return this.getStart() - o.getStart();
	}

	/**
	 * Avoids similar snippets
	 */
	@Override
	public boolean equals(Object o) {
		return (o instanceof SimilaritySnippet)
					&& this.getStart() >= ((SimilaritySnippet) o).getStart()
					&& this.getEnd() <= ((SimilaritySnippet) o).getEnd();
	}

	@Override
	public int hashCode() {
		return this.getStart();
	}

}
