package parser;

public class Node {
	private String className;
	private int hash;

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
			return true;
		}

		return false;
	}

	public void setClassName(String str) {
		className = str;
	}

	public void setHash(int val) {
		hash = val;
	}

}
