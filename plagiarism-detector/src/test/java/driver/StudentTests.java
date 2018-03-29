package driver;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for testing the Student class
 * @author Darshan Panse
 * @author Shail Shah
 */
public class StudentTests {

    private static Student student;

    /**
     * Initializing test data to be used in all the test cases
     */
    @BeforeClass
    public static void init() {
        student = new Student(101, "John Doe", "doe.john@husky.neu.edu");
    }

    /**
     * Test getting the Id of the student
     */
	@Test
    public void testGetId() {
        assertEquals(101, student.getId());
    }

    /**
     * Test getting the name of the student
     */
    @Test
    public void testGetName() {
        assertEquals("John Doe", student.getName());
    }

    /**
     * Test getting the email of the student
     */
    @Test
    public void testGetEmail() {
        assertEquals("doe.john@husky.neu.edu", student.getEmail());
    }

    /**
     * Test changing the Id of the student
     */
    @Test
    public void testSetId() {
        student.setId(102);
        assertEquals(102, student.getId());
        student.setId(101);
        assertEquals(101, student.getId());
    }

    /**
     * Test changing the name of the student
     */
    @Test
    public void testSetName() {
        student.setName("Bob Smith");
        assertEquals("Bob Smith", student.getName());
        student.setName("John Doe");
        assertEquals("John Doe", student.getName());
    }

    /**
     * Test changing the email of the student
     */
    @Test
    public void testSetEmail() {
        student.setEmail("smith.bob@husky.neu.edu");
        assertEquals("smith.bob@husky.neu.edu", student.getEmail());
        student.setEmail("doe.john@husky.neu.edu");
        assertEquals("doe.john@husky.neu.edu", student.getEmail());
    }

    /**
     * Assert that an exception is thrown when an email address is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetEmailException() {
        student.setEmail("notAnEmail");
    }
}
