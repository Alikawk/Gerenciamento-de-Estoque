package library;

import java.sql.Connection;
import java.sql.ResultSet;

public class ClienteVO
{
	private String nome_cliente;
	private int cod_cliente;
	
	public String Nome_cliente()
	{
		return nome_cliente;
	}
	public boolean Nome_cliente(String value)
	{
		if (library.Support.stringVal(value))
		{
			nome_cliente = value;
			return true;
		}
		return false;
	}
	
	public int Cod_cliente()
	{
		return cod_cliente;
	}
	public boolean Cod_cliente(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("select count * from x where cod_cliente=" + value, c);
		int val = 0;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }*/
		if (Support.codVal(value))
		{
			cod_cliente = value;
			return true;
		}
		return false;
	}
}
