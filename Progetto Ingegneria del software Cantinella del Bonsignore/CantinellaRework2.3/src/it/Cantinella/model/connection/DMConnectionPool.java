package it.Cantinella.model.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

public class DMConnectionPool {

	
	private static LinkedList<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found: " + e.getMessage());
		}		
	}
	
	private static synchronized Connection createDBConnection() throws SQLException{

		Connection newConnection = null;
		
		
		String ip = "localhost";
		String port = "3306";
		String db = "lacantinelladelbonsignore";
		String username = "root";
		String password = "root";


		newConnection = (Connection) DriverManager.getConnection("jdbc:mysql://"+ ip +":"+ port+ "/" + db, username , password);
		System.out.println("Print della connection : " + newConnection);
		newConnection.setAutoCommit(false);

		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException{

		Connection connection ;
		
		
		if (!freeDbConnections.isEmpty()){
			
			connection = freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if(connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		}else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException{
		if(connection != null) freeDbConnections.add(connection);
	}
}