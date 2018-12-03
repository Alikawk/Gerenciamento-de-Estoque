package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO
{
	public static List<ProdutoVO> makeVO(ResultSet r)
	{
		if (r == null)
			return null;
		List<ProdutoVO> ret = new ArrayList<ProdutoVO>();
		try
		{
			while(r.next())
			{
				ProdutoVO n = new ProdutoVO();
				n.Cod_produto(Integer.parseInt(r.getString("Cod_produto")));
				n.Nome_produto(r.getString("Nome_produto"));
				n.Posicao(r.getString("Posicao"));
				n.Valor_produto(Double.parseDouble(r.getString("Valor_produto")));
				n.Qtde_produto(Integer.parseInt(r.getString("Qtde_produto")));
				n.Tempo_reposicao(Integer.parseInt(r.getString("Tempo_reposicao")));
				
				ret.add(n);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<ProdutoVO>();
		}
		return ret;
	}
	
	public static Boolean insert(ProdutoVO produto)
	{
		if (produto == null)
			return false;
		String command = "insert into Produto(Cod_produto, Nome_produto, Posicao, Valor_produto, Qtde_produto, Tempo_reposicao) "
				+ "values(" + produto.Cod_produto() + ", '" + produto.Nome_produto() + "', '" + produto.Posicao() + "', "
				+ produto.Valor_produto() + ", " + 0 + ", " + produto.Tempo_reposicao() + " )";
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		return Support.executeU(command, c) > 0;
	}
	
	public static Boolean update(ProdutoVO produto)
	{
		if (produto == null)
			return false;
		String command = "update Produto set Nome_produto='" + produto.Nome_produto() + "', Posicao='" + produto.Posicao()
			+ "', Valor_produto=" + produto.Valor_produto() + ", Qtde_produto=" + produto.Qtde_produto() + ", Tempo_reposicao=" +
			produto.Tempo_reposicao() + " where Cod_produto="	+ produto.Cod_produto();
		System.out.println(command);
		Connection c = null;
		try
		{
			c = SqlConnection.getConn();
			return Support.executeU(command, c) > 0;
			//return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				c.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static List<ProdutoVO> search(int cod)
	{
		String command = "select * from Produto where Cod_produto=" + cod;
		System.out.println(command);
		Connection c;
		c = SqlConnection.getConn();
		List<ProdutoVO> ret = makeVO(Support.executeQ(command, c));
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
