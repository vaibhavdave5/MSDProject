package writer;

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.IOException;

import org.junit.Test;

public class WriterTest {

	/**
	 * Writer works properly
	 * 
	 * @throws IOException
	 */
	// Code compiles without errors
	@Test
	public void test() throws IOException {
		BufferedWriter w = Writer.getWriter();
		w.write("Test string");
		Writer.closeWriter();
		assertTrue(true);
	}

	/**
	 * Same instances of the writer created each time
	 * 
	 * @throws IOException
	 */
	@Test
	public void test2() throws IOException {
		BufferedWriter w = Writer.getWriter();
		BufferedWriter w2 = Writer.getWriter();
		assertEquals(w, w2);
	}

	/**
	 * Multiple closes are handled 
	 * @throws IOException
	 */
	@Test
	public void test3() throws IOException {
		BufferedWriter w = Writer.getWriter();
		w.write("xyz");
		Writer.closeWriter();
		Writer.closeWriter();		
		assertTrue(true);
	}

	
}
