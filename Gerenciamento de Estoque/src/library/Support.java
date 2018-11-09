package library;

import java.sql.*;

public class Support
{
	public static ResultSet executeCommand(String command, Connection c) //throws SQLException
	{
		ResultSet r = null;
		try
		{
			Statement s = c.createStatement();
			r = s.executeQuery(command);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	
	public static int executeCommand(Connection c, String command) //throws SQLException
	{
		try
		{
			Statement s = c.createStatement();
			return s.executeUpdate(command);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}

