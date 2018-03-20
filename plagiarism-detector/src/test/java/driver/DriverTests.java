package driver;

import static org.junit.Assert.*;

import org.junit.Test;

public class DriverTests {
	@Test
	public void testGetters() {
		Student s = new Student(101, "John", "s.s@husky.neu.edu");
		assertEquals(101, s.getId());
		assertEquals("John", s.getName());
		assertEquals("s.s@husky.neu.edu", s.getEmail());
	}
	
	@Test
	public void testSetters() {
		Student s = new Student(101, "Sam", "s.s@husky.neu.edu");
		s.setId(102);
		assertEquals(102, s.getId());
		
		s.setName("John");
		assertEquals("John", s.getName());
		
		s.setEmail("j.j@husky.neu.edu");
		assertEquals("j.j@husky.neu.edu", s.getEmail());
	}

}
