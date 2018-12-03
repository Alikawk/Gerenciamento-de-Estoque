package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO
{
	public static List<ClienteVO> makeVO(ResultSet r)
	{
		if (r == null)
			return null;
		List<ClienteVO> ret = new ArrayList<ClienteVO>();
		try
		{
			while(r.next())
			{
				ClienteVO n = new ClienteVO();
				n.Cod_cliente(Integer.parseInt(r.getString("Cod_cliente")));
				n.Nome_cliente(r.getString("Nome_cliente"));
				ret.add(n);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<ClienteVO>();
		}
		return ret;
	}
	
	public static Boolean insert(ClienteVO cliente)
	{
		if (cliente == null)
			return false;
		String command = "insert into Cliente(Cod_cliente, Nome_cliente) "
				+ "values(" + cliente.Cod_cliente() + ", '" + cliente.Nome_cliente() + "' )";
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		return Support.executeU(command, c) > 0;
	}
	
	public static Boolean update(ClienteVO cliente)
	{
		if (cliente == null)
			return false;
		String command = "update Cliente set Nome_cliente='" + cliente.Nome_cliente() + "' where Cod_cliente=" + cliente.Cod_cliente();
		System.out.println(command);
		Connection c;
		try
		{
			c = SqlConnection.getConn();
			return Support.executeU(command, c) > 0;
			//return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<ClienteVO> search(int cod)
	{
		String command = "select * from Cliente where Cod_cliente=" + cod;
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		List<ClienteVO> ret = makeVO(Support.executeQ(command, c));
		try
		{
			c.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public static boolean CheckCodCliente(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Cliente where Cod_cliente=" + cod, c);
		int val = 0;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }
		return val == 0;
	}
}
