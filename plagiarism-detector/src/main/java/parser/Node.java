package parser;

public class Node {
	private String className;
	private int hash;
	private int startLine;
	private int endline;
	
	public Node(int start, int end, int hash, String className){
		this.startLine = start;
		this.endline = end;
		this.hash = hash;
		this.className = className;
	}
		
	@Override
	public int hashCode() {
		return className.length(); 
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof Node)) {
			return false;
		} else if (((Node) o).className.equals(this.className) && (((Node) o).hash == this.hash)) {
			return (this.hash==((Node) o).getHash());
		}

		return false;
	}

	public int getHash() {
		return hash;
	}
	
	public int getStart() {
		return this.startLine;
	}
	
	public int getEnd(){
		return this.endline;
	}
}
