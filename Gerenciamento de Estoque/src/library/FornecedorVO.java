package library;

import java.sql.Connection;
import java.sql.ResultSet;

public class FornecedorVO
{
	private String nome_fornecedor;
	private int cod_fornecedor;
	
	public String Nome_fornecedor()
	{
		return nome_fornecedor;
	}
	public boolean Nome_fornecedor(String value)
	{
		if (library.Support.stringVal(value))
		{
			nome_fornecedor = value;
			return true;
		}
		return false;
	}
	
	public int Cod_fornecedor()
	{
		return cod_fornecedor;
	}
	public boolean Cod_fornecedor(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("", c);
		int val = 0;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }*/
		if (Support.codVal(value))
		{
			cod_fornecedor = value;
			return true;
		}
		return false;
	}
}
