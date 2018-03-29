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
		ISummary summary = new Summary();
		IStudentPair sp = new StudentPair(101, 102);
		summary.addToRedPairs(sp);
		Set<IStudentPair> actual = summary.getRedPairs();
		for(IStudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
		}
	}

	/**
	 * Test for adding elements to the list of yellow pairs
	 */
	@Test
	public void testYellowPair() {
		ISummary summary = new Summary();
		IStudentPair sp = new StudentPair(101, 102);
		summary.addToYellowPairs(sp);
		Set<IStudentPair> actual = summary.getYellowPairs();
		for(IStudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
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
		ISummary summary = new Summary();
		IStudentPair sp1 = new StudentPair(101, 102);
		summary.addToRedPairs(sp1);
		IStudentPair sp2 = new StudentPair(103, 104);
		summary.addToYellowPairs(sp2);

		assertEquals(false, summary.isSafe(101));
		assertEquals(false, summary.isSafe(102));
		assertEquals(false, summary.isSafe(103));
		assertEquals(false, summary.isSafe(104));
		assertEquals(true, summary.isSafe(105));
	}
}
