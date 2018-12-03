package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.ProdutoDAO;
import library.ProdutoVO;
import library.SqlConnection;
import library.Support;
import library.Tabela;

public class CtrlConsultar implements Initializable{
	@FXML
	private AnchorPane childPane;

	
	@FXML
	public ComboBox<String> CbCod;
	
	@FXML
	public ComboBox<String> CbValor;

	@FXML
	public ComboBox<String> CbQtde;
	
	@FXML
    private TextField txtNom;
    
    @FXML
    private TextField txtCod;
    
    @FXML
    private TextField txtPos;
    
    @FXML
    private TextField txtTem;
    
    @FXML
    private TextField txtPre;
    
    @FXML
    private TextField txtQtde;
    
    @FXML
    private RadioButton rbNom;
    
    @FXML
    private RadioButton rbCod;
    
    @FXML
    private RadioButton rbPos;
    
    @FXML
    private RadioButton rbTem;
    
    @FXML
    private RadioButton rbPre;
    
    @FXML
    private RadioButton rbQtde;
    
    @FXML
    private TableView<Tabela> TableMain;
    
    @FXML
    private TableColumn<Tabela, String> tmNom;
    
    @FXML
    private TableColumn<Tabela, String> tmCod;
    
    @FXML
    private TableColumn<Tabela, String> tmPos;
    
    @FXML
    private TableColumn<Tabela, String> tmTem;
    
    @FXML
    private TableColumn<Tabela, String> tmPre;
    
    @FXML
    private TableColumn<Tabela, String> tmQtde;
	
	public void ShowConsultar() throws IOException
	{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowConsultar.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	
	
	ObservableList<String> MIM = FXCollections.observableArrayList("Menor","Igual", "Maior");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// TODO Auto-generated method stub
		tmCod.setCellValueFactory(cellData -> cellData.getValue().cod_produto);
		tmNom.setCellValueFactory(cellData -> cellData.getValue().nome_produto);
		tmPos.setCellValueFactory(cellData -> cellData.getValue().posicao);
		tmPre.setCellValueFactory(cellData -> cellData.getValue().valor_produto);
		tmQtde.setCellValueFactory(cellData -> cellData.getValue().qtde_produto);
		tmTem.setCellValueFactory(cellData -> cellData.getValue().tempo_reposicao);
		rbNom.setSelected(true);
		
		CbCod.setItems(MIM);
		CbValor.setItems(MIM);
		CbQtde.setItems(MIM);
	}
	
	public static void Dialog(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void fillTable(ResultSet r)
	{
		ObservableList<Tabela> t = FXCollections.observableArrayList();
		List<ProdutoVO> p = ProdutoDAO.makeVO(r);
		if (p == null)
		{
			Dialog("Erro na conexao com o banco de dados.");
			return;
		}
		for (int i = 0; i < p.size(); i++)
		{
			ProdutoVO a = p.get(i);
			t.add(new Tabela(a.Nome_produto(), ""+a.Cod_produto(), a.Posicao(), ""+a.Valor_produto(), ""+a.Qtde_produto(), ""+a.Tempo_reposicao()));
		}
		//t.add(new Tabela("a", "b", "c", "d", "e", "f"));
		//t.add(new Tabela("1", "2", "3", "4", "5", "6"));
		TableMain.setItems(t);
	}
	
	@FXML
	protected void btnPesquisarHandler(ActionEvent event)
	{
		
		List<ProdutoVO> produtos;
		String command = "select * from Produto where ";
		ResultSet r;
		Connection c = SqlConnection.getConn();
		if (rbNom.isSelected())
			command += "Nome_produto like '%" + txtNom.getText() + "%'";
		else if (rbCod.isSelected())
		{
			try
			{
				command += "Cod_produto=" + Integer.parseInt(txtCod.getText());
			}
			catch (Exception e)
			{
				Dialog("Codigo invalido.");
				try 
				{
					c.close();
				}
				catch (Exception e1){}
				return;
			}
		}
		else if (rbPos.isSelected())
			command += "Posicao like '%" + txtPos.getText() + "%'";
		else if (rbQtde.isSelected())
		{
			try
			{
				if (CbQtde.getValue() == "Menor")
					command += "Qtde_produto < " + Integer.parseInt(txtQtde.getText());
				else if (CbQtde.getValue() == "Maior")
					command += "Qtde_produto > " + Integer.parseInt(txtQtde.getText());
				else if (CbQtde.getValue() == "Igual")
					command += "Qtde_produto = " + Integer.parseInt(txtQtde.getText());
				else
				{
					Dialog("Selecione um modificador de pesquisa (Maior, menor ou igual).");
					return;
				}
			}
			catch (Exception e)
			{
				Dialog("Quantidade invalida.");
				try 
				{
					c.close();
				}
				catch (Exception e1){}
				return;
			}
		}
		else if (rbPre.isSelected())
		{
			try
			{
				if (CbValor.getValue() == "Menor")
					command += "Valor_produto < " + Double.parseDouble(txtPre.getText().replace(',', '.'));
				else if (CbValor.getValue() == "Maior")
					command += "Valor_produto > " + Double.parseDouble(txtPre.getText().replace(',', '.'));
				else if (CbValor.getValue() == "Igual")
					command += "Valor_produto = " + Double.parseDouble(txtPre.getText().replace(',', '.'));
				else
				{
					Dialog("Selecione um modificador de pesquisa (Maior, menor ou igual).");
					return;
				}
			}
			catch (Exception e)
			{
				Dialog("Valor invalido.");
				try 
				{
					c.close();
				}
				catch (Exception e1){}
				return;
			}
		}
		else if (rbTem.isSelected())
		{
			try
			{
				if (CbCod.getValue() == "Menor")
					command += "Tempo_reposicao < " + Integer.parseInt(txtTem.getText());
				else if (CbCod.getValue() == "Maior")
					command += "Tempo_reposicao > " + Integer.parseInt(txtTem.getText());
				else if (CbCod.getValue() == "Igual")
					command += "Tempo_reposicao = " + Integer.parseInt(txtTem.getText());
				else
				{
					Dialog("Selecione um modificador de pesquisa (Maior, menor ou igual).");
					return;
				}
			}
			catch (Exception e)
			{
				Dialog("Quantidade invalida.");
				try 
				{
					c.close();
				}
				catch (Exception e1){}
				return;
			}
		}

		r = Support.executeQ(command, c);
		fillTable(r);
	}
}
