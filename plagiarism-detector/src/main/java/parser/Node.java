package parser;

public class Node {
	private int ruleNumber;
	private int hash;
	private Object object;

	public Node(int ruleNumber) {
		this.ruleNumber = ruleNumber; 
	}

	@Override
	public int hashCode() {
		return object.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (!(o instanceof Node)) {
			return false;
		} else if (this.ruleNumber==((Node)o).getRuleNumber()) {
			return true;
		}

		return false;
	}

	public void setHash(int val) {
		hash = val;
	}
	
	public int getRuleNumber(){
		return ruleNumber;
	}

}
