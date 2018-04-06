package database;

import static org.junit.Assert.*;


import org.junit.Test;

public class ConnectTest {

	@Test
	public void testRunQuery() {
		assertFalse(Connect.runQuery("Select * from Statistics").equals(null));
	}
	
	@Test(expected = NullPointerException.class)
	public void testRunQueryException() {
		assertFalse(Connect.runQuery("Select * from Statistic").equals(null));
	}

	@Test
	public void testGetNumberofFilesFromStatistics() {
		assertTrue(Connect.getNumberofFilesFromStatistics()>=0);
	}

	@Test
	public void testIncreaseByOne() {
		int x = Connect.getNumberofFilesFromStatistics();
		Connect.increaseByOne();
		int y = Connect.getNumberofFilesFromStatistics();
		assertEquals((x+1), y);
	}
	
	@Test
	public  void testIncreaseCases(){
		int cases = Connect.getCases();
		Connect.increaseCases(10);
		assertEquals((cases+10), Connect.getCases());
	}

}
