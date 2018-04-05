package parser;
/**
 * This class represents a subset of properties that can be retrieved from
 * CParser
 * @author Vaibhav Dave
 *
 */
public class Node {
	private String className;
	private int hash;
	private int startLine;
	private int endLine;

	/**
	 * Constructor for creating a new Node object
	 * @param start the start of the Node
	 * @param end the end line of the Node
	 * @param hash the hash value of the Node
	 * @param className the name of the class of the Node
	 */
	public Node(int start, int end, int hash, String className){
		this.startLine = start;
		this.endLine = end;
		this.hash = hash;
		this.className = className;
	}

	/**
	 *
	 * @return the hash code of this Node object
	 */
	@Override
	public int hashCode() {
		return className.length(); 
	}

	/**
	 *
	 * @param o an object
	 * @return true iff the parameter is a Node and if it is a clone of this Node
	 */
	@Override
	public boolean equals(Object o) {
		return (o != null) &&
				(o instanceof Node) &&
				((Node) o).className.equals(this.className) &&
				(((Node) o).hash == this.hash);
	}

	/**
	 * @return a hash generated in CParser
	 */
	public int getHash() {
		return hash;
	}
	
	/**
	 * Returns start line of the code
	 * @return int start
	 */
	public int getStart() {
		return this.startLine;
	}
	

	/**
	 * @return end line of the code
	 */
	public int getEnd(){
		return this.endLine;
	}
}
