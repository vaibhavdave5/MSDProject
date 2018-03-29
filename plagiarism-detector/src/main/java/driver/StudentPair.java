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
		return this.studentId1;
	}

	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student1ID
	 */
	public void setStudent1Id(Integer student1Id) {
		this.studentId1 = student1Id;
	}

	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student2ID
	 */
	public Integer getStudent2Id() {
		return studentId2;
	}

	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student2ID
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
