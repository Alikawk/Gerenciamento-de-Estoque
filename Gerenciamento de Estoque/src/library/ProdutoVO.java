package library;

import java.sql.Connection;
import java.sql.ResultSet;

public class ProdutoVO
{
	private int cod_produto;
	private String nome_produto;
	private String posicao;
	private double valor_produto;
	private int qtde_produto;
	private int tempo_reposicao;
	
	public int Cod_produto()
	{
		return cod_produto;
	}
	public boolean Cod_produto(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("select count * from X where cod_produto=" + value, c);
		int val = 0;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }*/
		if (Support.codVal(value))
		{
			cod_produto = value;
			return true;
		}
		return false;
	}
	
	public String Nome_produto()
	{
		return nome_produto;
	}
	public boolean Nome_produto(String value)
	{
		if (library.Support.stringVal(value))
		{
			nome_produto = value;
			return true;
		}
		return false;
	}
	
	public String Posicao()
	{
		return posicao;
	}
	public boolean Posicao(String value)
	{
		if (library.Support.stringVal(value))
		{
			posicao = value;
			return true;
		}
		return false;
	}
	
	public double Valor_produto()
	{
		return valor_produto;
	}
	public boolean Valor_produto(double value)
	{
		if (Support.codVal(value))
		{
			valor_produto = value;
			return true;
		}
		return false;
	}
	
	public int Qtde_produto()
	{
		return qtde_produto;
	}
	public boolean Qtde_produto(int value)
	{
		if (Support.codVal(value))
		{
			qtde_produto = value;
			return true;
		}
		return false;
	}
	
	public int Tempo_reposicao()
	{
		return tempo_reposicao;
	}
	public boolean Tempo_reposicao(int value)
	{
		if (Support.codVal(value))
		{
			tempo_reposicao = value;
			return true;
		}
		return false;
	}
}
