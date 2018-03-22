package driver;

/**
 * 
 * @author darshan.panse
 * This class makes a studentId pair 
 *
 */
public class StudentPair {
	private Integer student1Id;
	private Integer student2Id;
	private Double similarityScore;
	
	public StudentPair(Integer student1Id, Integer student2Id) {
		this.student1Id = student1Id;
		this.student2Id = student2Id;
	}

	public Double getSimilarityScore() {
		return similarityScore;
	}

	public void setSimilarityScore(Double similarityScore) {
		this.similarityScore = similarityScore;
	}

	public Integer getStudent1Id() {
		return student1Id;
	}

	public void setStudent1Id(Integer student1Id) {
		this.student1Id = student1Id;
	}

	public Integer getStudent2Id() {
		return student2Id;
	}

	public void setStudent2Id(Integer student2Id) {
		this.student2Id = student2Id;
	}
	
	
}
