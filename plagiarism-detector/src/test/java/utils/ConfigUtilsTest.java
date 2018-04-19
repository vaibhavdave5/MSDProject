package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests ConfigUtils
 * @author Vaibhav Dave
 *
 */
public class ConfigUtilsTest {

	ConfigUtils cu = new ConfigUtils();

	@Test
	public void testReadConfig() {
		assertEquals("Vaibhav Dave", cu.readConfig("author"));
	}

}
