package algorithms;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for testing SnippetPair getters
 * @author Shail Shah
 */
public class SnippetPairTest {

	static SnippetPair snippetPair;

	/**
	 * Initialize snippetPair so that it can be tested by other test cases
	 */
	@BeforeClass
	public static void init() {
		snippetPair = new SnippetPair("a.c", "b.c", 
				"Here's some text.", "Here's some text", 
				"Here's some text.", "Here's some text.",
				"Here's some text.", "Here's some text.",100, 99);
	}

	/**
	 * Test getting the name of the first file
	 */
	@Test
	public void getFileName1() {
		assertEquals("a.c", snippetPair.getFileName1());
	}

	/**
	 * Test getting the name of the second file
	 */
	@Test
	public void getFileName2() {
		assertEquals("b.c", snippetPair.getFileName2());
	}

	/**
	 * Test getting the first snippet
	 */
	@Test
	public void getSnippet1() {
		assertEquals("Here's some text", snippetPair.getSnippet1());
	}

	/**
	 * Test getting the second snippet
	 */
	@Test
	public void getSnippet2() {
		assertEquals("Here's some text.", snippetPair.getSnippet2());
	}

	/**
	 * Assert that an IllegalArgumentException is thrown when fileName1 is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullFileName1() {
		SnippetPair snippetPair = new SnippetPair(null, 
												 "b.c", 
												 "Here's some text", 
												 "Here's some text",
												 "Here's some text",
												 "Here's some text",
												 "Here's some text",
												 "Here's some text",
												 100, 
												 99);
	}

	/**
	 * Assert that an IllegalArgumentException is thrown when fileName2 is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullFileName2() {
		SnippetPair snippetPair = new SnippetPair("a.c", 
												 null, 
												 "Here's some text",
												 "Here's some text",
												 "Here's some text",
												 "Here's some text",
												 "Here's some text", 
												 "Here's some text.", 
												 100, 
												 99);
	}

	/**
	 * Assert that an IllegalArgumentException is thrown when snippet1 is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullSnipet1() {
		SnippetPair snippetPair = new SnippetPair("a.c", 
												 "b.c", 
												 "Here's some text.", 
												 null,
												 "Here's some text",
												 "Here's some text.", 
												 "Here's some text.",
												 "Here's some text",
												 100, 
												 99);
	}

	/**
	 * Assert that an IllegalArgumentException is thrown when snippet2 is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorNullSnipet2() {
		SnippetPair snippetPair = new SnippetPair("a.c", 
				 "b.c", 
				 "Here's some text.", 
				 "Here's some text",
				 "Here's some text",
				 "Here's some text.", 
				 null,
				 "Here's some text",
				 100, 
				 99);
	}
}