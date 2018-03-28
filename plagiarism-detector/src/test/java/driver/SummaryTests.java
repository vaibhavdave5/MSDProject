package driver;

import static org.junit.Assert.*;
import java.util.Set;

import org.junit.Test;

public class SummaryTests {

	@Test
	public void testGetSetRed() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.setRed(sp);
		Set<StudentPair> actual = summary.getRed();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
		}
	}
	
	@Test
	public void testGetSetYellow() {
		Summary summary = new Summary();
		StudentPair sp = new StudentPair(101, 102);
		summary.setYellow(sp);
		Set<StudentPair> actual = summary.getYellow();
		for(StudentPair stp: actual) {
			assertEquals((Integer) 101, stp.getStudent1Id());
			assertEquals((Integer) 102, stp.getStudent2Id());
		}
	}
	
	@Test
	public void testGetSetGreen() {
		Summary summary = new Summary();
		summary.setGreen(101);
		Set<Integer> actual = summary.getGreen();
		for(Integer id: actual) {
			assertEquals((Integer) 101, id);
		}
	}
	
	@Test
	public void testIsSafe() {
		Summary summary = new Summary();
		StudentPair sp1 = new StudentPair(101, 102);
		summary.setRed(sp1);
		StudentPair sp2 = new StudentPair(103, 104);
		summary.setYellow(sp2);
		assertEquals(false, summary.isSafe(101));
		assertEquals(false, summary.isSafe(102));
		assertEquals(false, summary.isSafe(103));
		assertEquals(false, summary.isSafe(104));
		assertEquals(true, summary.isSafe(105));
	}
}
