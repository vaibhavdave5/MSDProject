package driver;

import java.io.File;
import java.util.logging.Logger;

public class Driver {
	private static Driver instance = new Driver();
	
	private Driver() {}
	
	public static Driver getInstance() {
		if(instance == null) return new Driver();
		else return instance;
	}
	
	private File[] files = null;
	private Logger logger = Logger.getLogger(this.getClass().toString());
	
	public void setFiles(File... files) {
		this.files = files;
	}
	
	
}
