package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

public class VendaVO
{
	private int cod_venda;
	private int cod_cliente;
	private int cod_produto;
	private double valor_venda;
	private int qtde_vendida;
	private Date data_venda;
	
	public int Cod_venda()
	{
		return cod_venda;
	}
	public boolean Cod_venda(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("select count * from X where cod_venda=" + value, c);
		int val = 0;
		try
		{
			r.next();
			val = r.getInt(0);
		}
		catch (Exception e) { e.printStackTrace(); }*/
		if (Support.codVal(value))
		{
			cod_venda = value;
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
	
	public int Cod_cliente()
	{
		return cod_cliente;
	}
	public boolean Cod_cliente(int value)
	{
		/*Connection c = SqlConnection.getConn();
		if (c == null)
			return 3;
		ResultSet r = Support.executeQ("select count * from X where cod_cliente=" + value, c);
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
	
	public double Valor_venda()
	{
		return valor_venda;
	}
	public boolean Valor_venda(double value)
	{
		if (value > 0)
		{
			valor_venda = value;
			return true;
		}
		return false;
	}
	
	public int Qtde_vendida()
	{
		return qtde_vendida;
	}
	public boolean Qtde_vendida(int value)
	{
		if (value > 0)
		{
			qtde_vendida = value;
			return true;
		}
		return false;
	}
	
	public Date Data_venda()
	{
		return data_venda;
	}
	public boolean Data_venda(Date value) 
	{
		if (Support.dateVal(value))
		{
			data_venda = value;
			return true;
		}
		return false;
	}
}
