package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO
{
	public static List<CompraVO> makeVO(ResultSet r)
	{
		if (r == null)
			return null;
		List<CompraVO> ret = new ArrayList<CompraVO>();
		try
		{
			while(r.next())
			{
				CompraVO n = new CompraVO();
				n.Cod_compra(Integer.parseInt(r.getString("Cod_compra")));
				n.Cod_fornecedor(Integer.parseInt(r.getString("Cod_fornecedor")));
				n.Cod_produto(Integer.parseInt(r.getString("Cod_produto")));
				n.Qtde_comprada(Integer.parseInt(r.getString("Qtde_comprada")));
				n.Valor_compra(Double.parseDouble(r.getString("Valor_compra")));
				n.Data_compra(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("Data_compra")));
				
				ret.add(n);
				/*n.Fornecedor = r.getString("Fornecedor");
				n.Produto = r.getString("Produto");
				n.Preco = Double.parseDouble(r.getString("Preco"));
				n.Quantidade = Integer.parseInt(r.getString("Quantidade"));
				n.Tempo = Integer.parseInt(r.getString("Tempo"));*/
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<CompraVO>();
		}
		return ret;
	}
	
	public static Boolean insert(CompraVO compra)
	{
		if (compra == null)
			return false;
		String command = "set dateformat dmy; insert into Compra(Cod_compra, Cod_fornecedor,Cod_produto, Qtde_comprada, Valor_compra, Data_compra) "
				+ "values(" + compra.Cod_compra() + ", " + compra.Cod_fornecedor() + ", " + compra.Cod_produto() + ", "
				+ compra.Qtde_comprada() + ", " + compra.Valor_compra() + ", '" + (new SimpleDateFormat("dd/MM/yyyy")).format(compra.Data_compra()) + "' )";
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		return Support.executeU(command, c) > 0;
	}
	
	/*public static Boolean update(CompraVO compra)
	{
		if (compras == null)
			return false;
		String command = "update Compras set Cod_fornecedor=" + compras.Cod_fornecedor()+ ", Cod_produto="
			+ compras.Cod_produto() + ", Qtde_comprada=" + compras.Qtde_comprada() + ", Valor_compra="
			+ compras.Valor_compra() + " where Cod_compra=" + compras.Cod_compra();
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
	
	public static boolean delete(int cod)
	{
		String command = "delete from Compras where Cod_compra=" + cod;
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
	}*/
	
	public static List<CompraVO> search(int cod)
	{
		String command = "set dateformat dmy; select * from Compra where Cod_compra=" + cod;
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		List<CompraVO> ret = makeVO(Support.executeQ(command, c));
		try
		{
			c.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	public static boolean CheckCodProduto(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Produto where Cod_poduto=" + cod, c);
		int val = 1;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }
		return val != 0;
	}
	
	public static boolean CheckCodFornecedor(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Fornecedor where Cod_fornecedor=" + cod, c);
		int val = 1;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }
		return val != 0;
	}
	
	public static boolean CheckCodCompra(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Compra where Cod_compra=" + cod, c);
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
