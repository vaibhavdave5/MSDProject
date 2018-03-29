package driver;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentPairTests {

	@Test
	public void testSetGetSimilarityScore() {
		StudentPair sp = new StudentPair(101, 102);
		sp.setSimilarityScore(0.35);
		assertEquals((Double) 0.35, sp.getSimilarityScore());
	}
	
	@Test
	public void testSetGetStudent1Id() {
		StudentPair sp = new StudentPair(101, 102);
		sp.setStudentId1(111);
		assertEquals((Integer) 111, sp.getStudentId1());
	}

	@Test
	public void testSetGetStudent2Id() {
		StudentPair sp = new StudentPair(101, 102);
		sp.setStudentId2(111);
		assertEquals((Integer) 111, sp.getStudentId2());
	}
	
	@Test
	public void testToString() {
		StudentPair sp = new StudentPair(101, 102);
		String actual = sp.toString();
		String expected = "Student-101 - Student-102";
		assertEquals(expected, actual);
	}
}
