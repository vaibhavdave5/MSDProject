package parser;

public class Node {
	private String className;
	private int hash;
	private int startLine;
	private int endline;
	
	public Node(int start, int end){
		this.startLine = start;
		this.endline = end;
	}
	
	
	@Override
	public int hashCode() {
		return className.hashCode();
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

	public void setClassName(String str) {
		className = str;
	}

	public void setHash(int val) {
		hash = val;
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
