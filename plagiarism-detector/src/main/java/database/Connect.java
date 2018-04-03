package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.sqlite.SQLiteConfig;

/**
 * A class to connect and run queries against the database
 * 
 * @author Samanjate Sood
 *
 */
public class Connect {
	
	private Connect() { }
	
	private static final String DB_PATH 
			= Connect.class.getResource("/data/plagiarism-detector-app.sqlite").toString();
	private static Logger logger = Logger.getLogger(Connect.class);
	
	/**
	 * This method runs the provided query.
	 * 
	 * @param query
	 * @return the number of columns of the result of the query
	 */
	public static int runQuery(String query) {
		final SQLiteConfig config = new SQLiteConfig();
	    config.setReadOnly(true);
		try(final Connection connection 
				= DriverManager.getConnection("jdbc:sqlite:" + DB_PATH, 
											 config.toProperties());
			PreparedStatement stmt = 
					connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery()) {
				return rs.getMetaData().getColumnCount();	
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return 0;
	}
}
