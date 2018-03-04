package writer;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class WriterTest {

	@Test
	public void test() throws IOException {
		BufferedWriter w = Writer.getWriter();
		w.write("Test string");
		assertTrue(true);
		BufferedReader br = new BufferedReader(new FileReader("Report.txt"));
		br.lines().forEach(System.out::println);
		br.close();
		Writer.closeWriter();
	}
}
