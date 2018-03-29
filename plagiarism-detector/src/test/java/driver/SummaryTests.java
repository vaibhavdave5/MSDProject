package driver;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the Summary class
 * @author Darshan Panse
 */
public class SummaryTests {

	/**
	 * Test for adding elements to the list of red pairs
	 */
	@Test
	public void testRedPair() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.addToRedPairs(sp);
		Set<StudentPair> actual = summary.getRedPairs();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudentId1());
			assertEquals((Integer) 102, stp.getStudentId2());
		}
	}

	/**
	 * Test for adding elements to the list of yellow pairs
	 */
	@Test
	public void testYellowPair() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.addToYellowPairs(sp);
		Set<StudentPair> actual = summary.getYellowPairs();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudentId1());
			assertEquals((Integer) 102, stp.getStudentId2());
		}
	}

	/**
	 * Test for adding to the list of green Ids
	 */
	@Test
	public void testGreenIds() {
		Summary summary = new Summary();
		summary.addToGreenIds(101);
		Set<Integer> actual = summary.getGreenIds();
		for(Integer id: actual) {
			assertEquals((Integer) 101, id);
		}
	}

	/**
	 * Test for checking if a student has not cheated
	 */
	@Test
	public void testIsSafe() {
		Summary summary = new Summary();
		StudentPair sp1 = new StudentPair(101, 102);
		summary.addToRedPairs(sp1);
		StudentPair sp2 = new StudentPair(103, 104);
		summary.addToYellowPairs(sp2);
		assertEquals(false, summary.isSafe(101));
		assertEquals(false, summary.isSafe(102));
		assertEquals(false, summary.isSafe(103));
		assertEquals(false, summary.isSafe(104));
		assertEquals(true, summary.isSafe(105));
	}
}
