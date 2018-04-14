package driver;

/**
 * A Student represents a student enrolled in the course.
 * @author darshan.panse
 *
 */
public interface IStudent {
	/**
	 * get the name of the student
	 * @return the name of the student
	 */
	String getName();
	
	/**
	 * set the name of the student
	 * @param name the name of the student
	 */
	void setName(String name);
	
	/**
	 * Get the ID of the student
	 * @return the ID of the student
	 */
	int getId();
	
	/**
	 * Set the ID of the student
	 * @param id the ID of the student
	 */
	void setId(int id);
	
	/**
	 * Get the email of the student
	 * @return the email of the student
	 */
	String getEmail();
	
	/**
	 * Set the email of the Student
	 * @param email the email of the Student
	 */
	void setEmail(String email);
}
