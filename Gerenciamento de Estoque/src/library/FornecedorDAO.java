package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO
{
	public static List<FornecedorVO> makeVO(ResultSet r)
	{
		if (r == null)
			return null;
		List<FornecedorVO> ret = new ArrayList<FornecedorVO>();
		try
		{
			while(r.next())
			{
				FornecedorVO n = new FornecedorVO();
				n.Cod_fornecedor(Integer.parseInt(r.getString("Cod_fornecedor")));
				n.Nome_fornecedor(r.getString("Nome_fornecedor"));
				ret.add(n);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<FornecedorVO>();
		}
		return ret;
	}
	
	public static Boolean insert(FornecedorVO forn)
	{
		if (forn == null)
			return false;
		String command = "insert into Fornecedor(Cod_fornecedor, Nome_fornecedor) "
				+ "values(" + forn.Cod_fornecedor() + ", '" + forn.Nome_fornecedor() + "' )";
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		return Support.executeU(command, c) > 0;
	}
	
	public static Boolean update(FornecedorVO forn)
	{
		if (forn == null)
			return false;
		String command = "update Fornecedor set Nome_fornecedor='" + forn.Nome_fornecedor() + "' where Cod_fornecedor=" + forn.Cod_fornecedor();
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
	
	public static List<FornecedorVO> search(int cod)
	{
		String command = "select * from Fornecedor where Cod_fornecedor=" + cod;
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		List<FornecedorVO> ret = makeVO(Support.executeQ(command, c));
		try
		{
			c.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public static boolean CheckCodFornecedor(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Fornecedor where Cod_fornecedor=" + cod, c);
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
