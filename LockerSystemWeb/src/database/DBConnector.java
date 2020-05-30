// Connect database
// Author: See Di Ching
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	// Get connection according to the username and password
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String username="root";
		String password="123456";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lockersystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
		return connection;
	}
	
 //for testing connection purpose
//	
// 	public static void main(String [] args) {
//		try {
//			System.out.println(DBConnector.getConnection());
//		}catch(ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
