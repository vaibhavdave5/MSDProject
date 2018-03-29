package driver;

/**
 * A StudentPair represents two students
 * @author Darshan Panse
 */
public class StudentPair {
	private Integer studentId1;
	private Integer studentId2;
	private Double similarityScore;

	/**
	 * Constructor for making a new StudentPair
	 * @param studentId1 the ID of the first student
	 * @param studentId2 the ID of the second student
	 */
	public StudentPair(Integer studentId1, Integer studentId2) {
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
	 * @param student2Id the Id to be set for the second student
	 */
	public void setStudent2Id(Integer student2Id) {
		this.studentId2 = student2Id;
	}
	
	/**
	 * Get the Id of the first student in the pair
	 * @return the Id of the first student in the pair
	 */
	public Integer getStudentId1() {
		return studentId1;
	}

	/**
	 * Set the Id of the first student in the pair
	 * @param studentId1 the Id of the first student in the pair
	 */
	public void setStudentId1(Integer studentId1) {
		this.studentId1 = studentId1;
	}

	/**
	 * Get the Id of the second student in the pair
	 * @return the Id of the second student in the pair
	 */
	public Integer getStudentId2() {
		return studentId2;
	}

	/**
	 * Set the Id of the second student in the pair
	 * @param studentId2 the Id of the second student in the pair
	 */
	public void setStudentId2(Integer studentId2) {
		this.studentId2 = studentId2;
	}

	/**
	 * Get this object in a String representation
	 * @return a String representation of this student pair
	 */
	@Override
	public String toString() {
		return "Student-" + getStudentId1()
		+ " - " 
		+ "Student-" + getStudentId2();
	}
	
}
