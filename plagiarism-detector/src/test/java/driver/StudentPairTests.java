package driver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for StudentPair methods
 */
public class StudentPairTests {

	/**
	 * Test for setting similarity score
	 */
	@Test
	public void testSetGetSimilarityScore() {
		IStudentPair sp = new StudentPair(101, 102);
		sp.setSimilarityScore(0.35);
		assertEquals((Double) 0.35, sp.getSimilarityScore());
	}

	/**
	 * Test for getting the first student's Id
	 */
	@Test
	public void testSetGetStudent1Id() {
		IStudentPair sp = new StudentPair(101, 102);
		sp.setStudent1Id(111);
		assertEquals((Integer) 111, sp.getStudent1Id());
	}

	/**
	 * Test for getting the second student's Id
	 */
	@Test
	public void testSetGetStudent2Id() {
		IStudentPair sp = new StudentPair(101, 102);
		sp.setStudent2Id(111);
		assertEquals((Integer) 111, sp.getStudent2Id());
	}

	/**
	 * Test getting a string that has Ids of both students
	 */
	@Test
	public void testToString() {
		IStudentPair sp = new StudentPair(101, 102);
		String actual = sp.toString();
		String expected = "Student-101 - Student-102";
		assertEquals(expected, actual);
	}
}
