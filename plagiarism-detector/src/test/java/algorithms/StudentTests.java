package algorithms;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controllers.Student;

public class StudentTests {

	private static Student s;
	@BeforeClass
	public static void getStudentInstance() {
		s = new Student(101, "Sam", "s.s@husky.neu.edu");
	}
	
	@Test
	public void testGetters() {
		assertEquals(101, s.getId());
		assertEquals("Sam", s.getName());
		assertEquals("s.s@husky.neu.edu", s.getEmail());
	}
	
	@Test
	public void testSetters() {
		s.setId(102);
		assertEquals(102, s.getId());
		
		s.setName("John");
		assertEquals("John", s.getName());
		
		s.setEmail("j.j@husky.neu.edu");
		assertEquals("j.j@husky.neu.edu", s.getEmail());
	}

}
