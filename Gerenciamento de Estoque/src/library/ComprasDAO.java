package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprasDAO
{
	public static List<ComprasVO> makeVO(ResultSet r)
	{
		List<ComprasVO> ret = new ArrayList<ComprasVO>();
		try
		{
			while(r.next())
			{
				ComprasVO n = new ComprasVO();
				n.Fornecedor = r.getString("Fornecedor");
				n.Produto = r.getString("Produto");
				n.Preco = Double.parseDouble(r.getString("Preco"));
				n.Quantidade = Integer.parseInt(r.getString("Quantidade"));
				n.Tempo = Integer.parseInt(r.getString("Tempo"));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return ret;
	}
	
	public static Boolean insert(ComprasVO compras)
	{
		if (compras == null)
			return false;
		String command = "insert into Compras(Fornecedor, Produto, Preco, Quantidade, Tempo) "
				+ "values ( '" + compras.Fornecedor + "', '" + compras.Produto + "', " + compras.Preco + ", "
				+ compras.Quantidade + ", " + compras.Tempo + " )";
		System.out.println(command);
		Connection c;
		try
		{
			c = SqlConnection.getConn();
			return Support.executeCommand(c, command) > 0;
			//return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
