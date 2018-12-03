package library;

import java.sql.*;
import java.util.Date;

public class Support
{
	public static ResultSet executeQ(String command, Connection c) //throws SQLException
	{
		System.out.println(command);
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
	
	public static int executeU(String command, Connection c) //throws SQLException
	{
		System.out.println(command);
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
	
	public static boolean stringVal(String s)
	{
		s = s.trim();
		if (s == "")
			return false;
		return true;
	}
	
	public static boolean codVal(int a)
	{
		if (a < 0)
			return false;
		return true;
	}
	
	public static boolean codVal(double a)
	{
		if (a < 0)
			return false;
		return true;
	}
	
	public static boolean dateVal(Date d)
	{
		if (d.after(new Date()))
			return false;
		return true;
	}
}

