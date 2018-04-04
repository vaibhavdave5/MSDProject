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

	private Connect() {
	}

	private static final String DB_PATH = Connect.class.getResource("/data/plagiarism-detector-app.sqlite").toString();

	private static Logger logger = Logger.getLogger(Connect.class);

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
	public static int runQuery(String query) {
		final Connection connection = connect();

		try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
			int result = rs.getInt("FilesScanned");
			return result;
		} catch (SQLException e) {
			logger.error(e.toString());
		}
		return 0;
	}

	public static void update() {
		String sql = "UPDATE Statistics SET FilesScanned = ? ";
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, (Connect.runQuery("Select * from Statistics") + 1));
			// update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
