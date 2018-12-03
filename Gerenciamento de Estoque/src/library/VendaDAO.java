package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO
{
	public static List<VendaVO> makeVO(ResultSet r)
	{
		if (r == null)
			return null;
		List<VendaVO> ret = new ArrayList<VendaVO>();
		try
		{
			while(r.next())
			{
				VendaVO n = new VendaVO();
				n.Cod_venda(Integer.parseInt(r.getString("Cod_venda")));
				n.Cod_cliente(Integer.parseInt(r.getString("Cod_cliente")));
				n.Cod_produto(Integer.parseInt(r.getString("Cod_produto")));
				n.Qtde_vendida(Integer.parseInt(r.getString("Qtde_vendida")));
				n.Valor_venda(Double.parseDouble(r.getString("Valor_venda")));
				n.Data_venda(new SimpleDateFormat("yyyy-MM-dd").parse(r.getString("Data_venda")));
				
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
			return new ArrayList<VendaVO>();
		}
		return ret;
	}
	
	public static Boolean insert(VendaVO venda)
	{
		if (venda == null)
			return false;
		String command = "set dateformat dmy; insert into Venda(Cod_venda, Cod_cliente, Cod_produto, Qtde_vendida, Valor_venda, Data_venda) "
				+ "values(" + venda.Cod_venda() + ", " + venda.Cod_cliente() + ", " + venda.Cod_produto() + ", "
				+ venda.Qtde_vendida() + ", " + venda.Valor_venda() + ", '" + (new SimpleDateFormat("dd/MM/yyyy")).format(venda.Data_venda()) + "' )";
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		boolean b = Support.executeU(command, c) > 0;
		try
		{
			c.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public static List<VendaVO> search(int cod)
	{
		String command = "set dateformat dmy; select * from Venda where Cod_venda=" + cod;
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		List<VendaVO> ret = makeVO(Support.executeQ(command, c));
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
	
	public static boolean CheckCodCliente(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Cliente where Cod_cliente=" + cod, c);
		int val = 1;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }
		return val != 0;
	}
	
	public static boolean CheckCodVenda(int cod)
	{
		Connection c = SqlConnection.getConn();
		if (c == null)
			return false;
		ResultSet r = Support.executeQ("select count(*) from Venda where Cod_venda=" + cod, c);
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
