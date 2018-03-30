package driver;

/**
 * A StudentPair represents two students
 * @author Darshan Panse
 */
public class StudentPair implements IStudentPair {
	private Integer studentId1;
	private Integer studentId2;
	private Double similarityScore;

	/**
	 * Constructor for making a new StudentPair
	 * @param studentId1 the ID of the first student
	 * @param studentId2 the ID of the second student
	 */
	StudentPair(Integer studentId1, Integer studentId2) {
		this.studentId1 = studentId1;
		this.studentId2 = studentId2;
	}
 
	/**
	 * Returns a similarity score between two students
	 * @return Double similarity score
	 */
	public Double getSimilarityScore() {
		return similarityScore;
	}

	/**
	 * Sets the similarity score for two students for a certain HW submission
	 * @param similarityScore the similarity score to be set
	 */
	public void setSimilarityScore(Double similarityScore) {
		this.similarityScore = similarityScore;
	}

	/**
	 * Gets student id of the first student
	 * @return the Id of the first student
	 */
	public Integer getStudent1Id() {
		return this.studentId1;
	}

	/**
	 * Set the Id of the first student
	 * @param student1Id the Id to be set for the first student
	 */
	public void setStudent1Id(Integer student1Id) {
		this.studentId1 = student1Id;
	}

	/**
	 * Gets student id of the second student
	 * @return the Id of the second student
	 */
	public Integer getStudent2Id() {
		return studentId2;
	}

	/**
	 * Set the Id of the second student
	 * @param student2Id the Id of the second student
	 */
	public void setStudent2Id(Integer student2Id) {
		this.studentId2 = student2Id;
	}

	/**
	 * Get this object in a String representation
	 * @return a String representation of this student pair
	 */
	@Override
	public String toString() {
		return "Student-" + getStudent1Id()
		+ " - " 
		+ "Student-" + getStudent2Id();
	}
	
}
