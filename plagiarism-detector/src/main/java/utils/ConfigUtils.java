package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class provides with the utilities to read and write the config files
 * 
 * @author Vaibhav Dave
 * @since 4/9/2018
 *
 */
public class ConfigUtils {

	private static Logger logger = Logger.getLogger(ConfigUtils.class);
	private static Properties properties = new Properties();
	private InputStream propertiesLoc = ConfigUtils.class.getResourceAsStream("/config.properties");

	/**
	 * This function helps the user to read a particular value from the
	 * config.properties file
	 * 
	 * @param String title
	 * @param String value
	 */
	public String readConfig(String title) {
		try {
			properties.load(propertiesLoc);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return properties.getProperty(title);
	}

}
