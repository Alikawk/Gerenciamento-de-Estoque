package library;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.DatabaseMetaData;

public class SqlConnection
{
	public static String connURL = "jdbc:sqlserver://localhost;databaseName=Estoque;user=SA;password=123456";
	
	public static Connection getConn() throws Exception
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(connURL);
		return conn;
	}
}
