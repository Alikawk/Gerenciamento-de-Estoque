package library;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tabela
{
	public StringProperty cod_produto;
	public StringProperty nome_produto;
	public StringProperty posicao;
	public StringProperty valor_produto;
	public StringProperty qtde_produto;
	public StringProperty tempo_reposicao;
	
	public Tabela(String cod, String nome, String pos, String valor, String qtde, String tempo)
	{
		cod_produto = new SimpleStringProperty(cod);
		nome_produto = new SimpleStringProperty(nome);
		posicao = new SimpleStringProperty(pos);
		valor_produto = new SimpleStringProperty(valor);
		qtde_produto = new SimpleStringProperty(qtde);
		tempo_reposicao = new SimpleStringProperty(tempo);
	}
	
}
