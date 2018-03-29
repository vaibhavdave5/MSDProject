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
		Set<IStudentPair> actual = summary.getRedPairs();
		for(IStudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
		}
	}
	
	@Test
	public void testGetSetYellow() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.setYellowPairs(sp);
		Set<IStudentPair> actual = summary.getYellowPairs();
		for(IStudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
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
		ISummary summary = new Summary();
		IStudentPair sp1 = new StudentPair(101, 102);
		summary.setRedPairs(sp1);
		IStudentPair sp2 = new StudentPair(103, 104);
		summary.setYellowPairs(sp2);
		assertEquals(false, summary.isSafe(101));
		assertEquals(false, summary.isSafe(102));
		assertEquals(false, summary.isSafe(103));
		assertEquals(false, summary.isSafe(104));
		assertEquals(true, summary.isSafe(105));
	}
}
