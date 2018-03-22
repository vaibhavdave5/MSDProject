package algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

import parser.Node;

public class NodeTests {

	
	/**
	 * Checking if the equals method works correctly
	 */
	@Test
	public void test() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 50, 3, "Hi" );
		assertFalse(node.equals(node2));
	}
	
	/**
	 * Checks if equals work correctly with nulls
	 */
	@Test
	public void test2() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = null;
		assertFalse(node.equals(node2));
	}
	
	/**
	 * Checks if equals work correctly
	 */
	@Test
	public void test3() {
		Node node = new Node(1, 40, 3, "Hello");
		Object node2 = new Object();
		assertFalse(node.equals(node2));
	}
	
	/**
	 * Checks if equals work correctly for same objects
	 */
	@Test
	public void test4() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 40, 3, "Hello");
		assertTrue(node.equals(node2));
	}
	
	/**
	 * Checks if equals objects have the same hashcode
	 */
	@Test
	public void test5() {
		Node node = new Node(1, 40, 3, "Hello");
		Node node2 = new Node(1, 40, 3, "Hello");
		assertTrue(node.hashCode()==node2.hashCode());
	}

}
