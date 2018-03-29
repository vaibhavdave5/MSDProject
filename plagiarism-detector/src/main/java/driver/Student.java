package driver;

import utils.MailUtils;

/**
 * A Student represents a student enrolled in the course
 * @author Darshan Panse
 */
public class Student implements IStudent {
	private String name;
	private int id;
	private String email;

	/**
	 * Constructor for making a new Student object
	 * @param id the id of the student
	 * @param name the name of the student
	 * @param email the email address of the student
	 */
	public Student(int id, String name, String email) {
		this.name = name;
		this.id = id;

		if(MailUtils.isValidEmail(email)) this.email = email;
		else throw new IllegalArgumentException();
	}

	/**
	 * get the name of the student
	 * @return the name of the student
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the student
	 * @param name the name of the student
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the ID of the student
	 * @return the ID of the student
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the ID of the student
	 * @param id the ID of the student
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the email of the student
	 * @return the email of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of the Student
	 * @param email the email of the Student
	 */
	public void setEmail(String email) {
		if(MailUtils.isValidEmail(email)) this.email = email;
		else throw new IllegalArgumentException();
	}
}
