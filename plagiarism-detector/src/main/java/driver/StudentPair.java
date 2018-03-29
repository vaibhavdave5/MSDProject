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

	/**
	 * Returns a similarity score between two students
	 * @return Double similarity score
	 */
	public Double getSimilarityScore() {
		return similarityScore;
	}

	/**
	 * Sets the similarity score for two students for a certain 
	 * HW submission
	 * @param similarityScore
	 */
	public void setSimilarityScore(Double similarityScore) {
		this.similarityScore = similarityScore;
	}

	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student1ID
	 */
	public Integer getStudent1Id() {
		return student1Id;
	}

	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student1ID
	 */
	public void setStudent1Id(Integer student1Id) {
		this.student1Id = student1Id;
	}

	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student2ID
	 */
	public Integer getStudent2Id() {
		return student2Id;
	}

	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student2ID
	 */
	public void setStudent2Id(Integer student2Id) {
		this.student2Id = student2Id;
	}
	
	/**
	 * Returns the string representation of the Student Pair
	 */
	@Override
	public String toString() {
		return "Student-" + getStudent1Id() 
		+ " - " 
		+ "Student-" + getStudent2Id();
	}
	
}
