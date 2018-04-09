package utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests ConfigUtils
 * @author Vaibhav Dave
 *
 */
public class ConfigUtilsTest {

	ConfigUtils cu = new ConfigUtils();
	@Test
	public void testWriteInConfig() {
		cu.writeInConfig("test", "testing");
		assertEquals("testing", cu.readConfig("test"));
	}

	@Test
	public void testReadConfig() {
		assertEquals("Vaibhav", cu.readConfig("author"));
	}

}
