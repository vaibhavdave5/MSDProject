package database;

import org.apache.log4j.Logger;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class to connect and run queries against the database
 * 
 * @author Samanjate Sood
 *
 */
public class Connect {

	private Connect() {
	}

	private static final String DB_PATH = Connect.class.getResource("/data/plagiarism-detector-app.sqlite").toString();

	private static Logger logger = Logger.getLogger(Connect.class);

	/**
	 * This function connects to the database
	 * @return
	 */
	private static Connection connect() {
		// SQLite connection string
		final SQLiteConfig config = new SQLiteConfig();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH, config.toProperties());
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return conn;
	} 

	/**
	 * This method runs the provided query.
	 * 
	 * @param query
	 * @return the number of columns of the result of the query
	 */
	public static ResultSet runQuery(String query) {
		final Connection connection = connect();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			return rs;
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return null;

	}
	/**
	 * This function returns the number of files scanned uptil now
	 * @return int: number of files scanned 
	 */

	public static int getNumberofFilesFromStatistics() {
		final Connection connection = connect();

		try (PreparedStatement stmt = connection.prepareStatement("Select * from Statistics"); 
			ResultSet rs = stmt.executeQuery()) {
			return rs.getInt("FilesScanned");
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return 0;

	}

	/**
	 * The number of plagiarism cases detected
	 * @return int number of cases found 
	 */
	public static int getCases() {
		final Connection connection = connect();

		try (PreparedStatement stmt = connection.prepareStatement("Select * from Statistics"); 
			ResultSet rs = stmt.executeQuery()) {
			return rs.getInt("PlagiarismCasesFound");
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return 0;

	}

	
	/**
	 * This function increases the FilesScanned by one
	 */
	
	public static void increaseByOne() {
		String sql = "UPDATE Statistics SET FilesScanned = ? ";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int x = Connect.getNumberofFilesFromStatistics();
			x++;
			// set the corresponding param
			pstmt.setInt(1, x);
			// update
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * This function increases the number of cases by number of students in 
	 * the red list
	 * @param count
	 */
	public static void increaseCases(int count) {
		String sql = "UPDATE Statistics SET PlagiarismCasesFound = ? ";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			int x = Connect.getCases();
			x = x + count;
			// set the corresponding param
			pstmt.setInt(1, x);
			// update
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
}