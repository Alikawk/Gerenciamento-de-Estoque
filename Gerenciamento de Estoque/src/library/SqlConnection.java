package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.DatabaseMetaData;

public class SqlConnection
{
	public static String connURL = "jdbc:sqlserver://localhost;databaseName=Estoque;user=SA;password=123456";
	
	public static Connection getConn()
	{
		Connection conn;
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connURL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}
}
