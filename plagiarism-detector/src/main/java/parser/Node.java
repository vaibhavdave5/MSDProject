package parser;

public class Node {
	private int ruleNumber;
	private int hash;
	private Object object;
	
	@Override
	public int hashCode() {
		return ;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof Node)) {
			return false;
		} else if () {
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
