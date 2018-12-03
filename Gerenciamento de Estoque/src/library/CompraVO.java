package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Date;

public class CompraVO
{
	private int cod_compra;
	private int cod_produto;
	private int cod_fornecedor;
	private Double valor_compra;
	private int qtde_comprada;
	private Date data_compra;
	
	/*public String Nome_produto()
	{
		return nome_produto;
	}
	public boolean Nome_Produto(String value)
	{
		return library.Support.stringVal(value);
	}*/
	
	public int Cod_compra()
	{
		return cod_compra;
	}
	public boolean Cod_compra(int value)
	{
		if (Support.codVal(value))
		{
			cod_compra = value;
			return true;
		}
		return false;
	}
	
	public int Cod_produto()
	{
		return cod_produto;
	}
	public boolean Cod_produto(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("select count * from X where cod_poduto=" + value, c);
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
	
	public double Valor_compra()
	{
		return valor_compra;
	}
	public boolean Valor_compra(double value)
	{
		if (value > 0)
		{
			valor_compra = value;
			return true;
		}
		return false;
	}
	
	public int Qtde_comprada()
	{
		return qtde_comprada;
	}
	public boolean Qtde_comprada(int value)
	{
		if (value > 0)
		{
			qtde_comprada = value;
			return true;
		}
		return false;
	}
	
	public Date Data_compra()
	{
		return data_compra;
	}
	public boolean Data_compra(Date value) 
	{
		if (Support.dateVal(value))
		{
			data_compra = value;
			return true;
		}
		return false;
	}
}
