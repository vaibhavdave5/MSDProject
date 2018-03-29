package driver;

/**
 * A StudentPair represents two students
 * @author Darshan Panse
 */
public interface IStudentPair {
	/**
	 * Returns a similarity score between two students
	 * @return Double similarity score
	 */
	public Double getSimilarityScore();
	
	/**
	 * Sets the similarity score for two students for a certain 
	 * HW submission
	 * @param similarityScore
	 */
	public void setSimilarityScore(Double similarityScore);
	
	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student1ID
	 */
	public Integer getStudent1Id();
	
	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student1ID
	 */
	public void setStudent1Id(Integer student1Id);
	
	/**
	 * Gets student id of student1 for whom we are running 
	 * plagiarism test
	 * @return int student2ID
	 */
	public Integer getStudent2Id();
	
	/**
	 * Sets student id of student2 for whom we are running 
	 * plagiarism test
	 * @param int student2Id
	 */
	public void setStudent2Id(Integer student2Id);
	
	/**
	 * Get this object in a String representation
	 * @return a String representation of this student pair
	 */
	@Override
	public String toString();
}
