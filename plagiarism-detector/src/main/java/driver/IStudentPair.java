package driver;

/**
 * A StudentPair represents two students
 * @author Darshan Panse
 */
public interface IStudentPair {
	/**
	 * Returns a similarity score between two students
	 * @return similarity score
	 */
	Double getSimilarityScore();
	
	/**
	 * Sets the similarity score for two students for a certain 
	 * HW submission
	 * @param similarityScore the similarity score
	 */
	void setSimilarityScore(Double similarityScore);
	
	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student1ID the id of the first student
	 */
	Integer getStudent1Id();
	
	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param student1Id the id of the first student
	 */
	void setStudent1Id(Integer student1Id);
	
	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return the id of the second student
	 */
	Integer getStudent2Id();
	
	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param student2Id the id of the second student
	 */
	void setStudent2Id(Integer student2Id);
	
	/**
	 * Get this object in a String representation
	 * @return a String representation of this student pair
	 */
	@Override
	String toString();
}
