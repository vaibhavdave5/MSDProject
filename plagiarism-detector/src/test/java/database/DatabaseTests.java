package database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A class to test the database connection and queries
 * 
 * @author Samanjate Sood
 *
 */
public class DatabaseTests {

	/**
	 * A method that tests the number of columns returned by the query
	 */
	@Test
	public void test() {
		assertEquals("Columns returned should be 1", 1, Connect.runQuery("SELECT Name AS fName FROM STUDENT"));
		assertEquals("Columns returned should be 3", 3, Connect.runQuery("SELECT * FROM STUDENT"));
	}

}
