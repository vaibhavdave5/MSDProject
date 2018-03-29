package driver;

import static org.junit.Assert.*;
import java.util.Set;

import org.junit.Test;

public class SummaryTests {

	@Test
	public void testGetSetRed() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.setRedPairs(sp);
		Set<StudentPair> actual = summary.getRedPairs();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudentId1());
			assertEquals((Integer) 102, stp.getStudentId2());
		}
	}
	
	@Test
	public void testGetSetYellow() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.setYellowPairs(sp);
		Set<StudentPair> actual = summary.getYellowPairs();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudentId1());
			assertEquals((Integer) 102, stp.getStudentId2());
		}
	}
	
	@Test
	public void testGetSetGreen() {
		Summary summary = new Summary();
		summary.setGreenIds(101);
		Set<Integer> actual = summary.getGreenIds();
		for(Integer id: actual) {
			assertEquals((Integer) 101, id);
		}
	}
	
	@Test
	public void testIsSafe() {
		Summary summary = new Summary();
		StudentPair sp1 = new StudentPair(101, 102);
		summary.setRedPairs(sp1);
		StudentPair sp2 = new StudentPair(103, 104);
		summary.setYellowPairs(sp2);
		assertEquals(false, summary.isSafe(101));
		assertEquals(false, summary.isSafe(102));
		assertEquals(false, summary.isSafe(103));
		assertEquals(false, summary.isSafe(104));
		assertEquals(true, summary.isSafe(105));
	}
}
