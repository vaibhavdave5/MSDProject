package database;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class test the function for database connection and updation
 * @author Vaibhav Dave
 *
 */
public class ConnectTest {

	/**
	 * Checks whether database is not empty
	 */
	@Test
	public void testRunQuery() {
		assertFalse(Connect.runQuery("Select * from Statistics").equals(null));
	}
	
	/**
	 * Test to check for wrong table
	 */
	@Test(expected = NullPointerException.class)
	public void testRunQueryException() {
		assertFalse(Connect.runQuery("Select * from Statistic").equals(null));
	}

	/**
	 * Checks whether database is not empty
	 */
	@Test
	public void testGetNumberofFilesFromStatistics() {
		assertTrue(Connect.getNumberofFilesFromStatistics()>=0);
	}

	/**
	 * Checks if the function actually increases the value in the 
	 * database by 1
	 */
	@Test
	public void testIncreaseByOne() {
		int x = Connect.getNumberofFilesFromStatistics();
		Connect.increaseByOne();
		int y = Connect.getNumberofFilesFromStatistics();
		assertEquals((x+1), y);
	}
	
	/**
	 * Checks if the function actually increases the value in the 
	 * database by multiple numbers
	 */

	@Test
	public void testIncreaseCases(){
		int cases = Connect.getCases();
		Connect.increaseCases(10);
		assertEquals((cases+10), Connect.getCases());
	}

}
