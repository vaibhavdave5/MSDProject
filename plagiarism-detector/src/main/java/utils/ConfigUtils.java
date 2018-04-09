package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;


/**
 * This class provides with the utilities to read and write the config 
 * files
 * @author Vaibhav Dave
 * @since 4/9/2018
 *
 */
public class ConfigUtils {

	private static Logger logger = Logger.getLogger(ConfigUtils.class);
	private static Properties properties = new Properties();
	
	/**
	 * This function helps the user to write a particular value
	 * to the config.properties file
	 * @param String title
	 * @param String value
	 */
	public void writeInConfig(String title, String value){
	
		properties.setProperty(title, value);
		try {
			properties.store(new FileOutputStream("config.properties"), null);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * This function helps the user to read a particular value
	 * from the config.properties file
	 * @param String title
	 * @param String value
	 */
	public String readConfig(String title){
		String value = properties.getProperty(title);
		return value;
	}
	
}
