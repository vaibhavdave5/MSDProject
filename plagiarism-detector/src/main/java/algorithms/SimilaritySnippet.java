package algorithms;


public class SimilaritySnippet implements Comparable<SimilaritySnippet> {
	private int start;
	private int end;
	
	public SimilaritySnippet(int start, int end){
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public int compareTo(SimilaritySnippet o) {
		return this.start-o.getStart();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		else if (!(o instanceof SimilaritySnippet)) {
			return false;
		}

		return (this.start <= ((SimilaritySnippet) o).getStart() && this.end >= ((SimilaritySnippet) o).getStart());
	}

	@Override
	public int hashCode(){
		return this.start;
	}
	
}
