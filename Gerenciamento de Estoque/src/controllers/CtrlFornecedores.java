package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.CompraDAO;
import library.CompraVO;
import library.FornecedorDAO;
import library.FornecedorVO;
import library.ProdutoDAO;

public class CtrlFornecedores {
    
	@FXML
    private AnchorPane childPane;
	
	@FXML
	private TextField txtNom;
	
	@FXML
	private TextField txtCod;



	public void ShowFornecedores() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowFornecedores.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	
	public static void Dialog(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	@FXML
	protected void btnAdicionarHandler(ActionEvent event) 
	{
		FornecedorVO forn = new FornecedorVO();
		try
		{
			if (!forn.Cod_fornecedor(Integer.parseInt(txtCod.getText())))
			{
				Dialog("O codigo do fornecedor nao pode ser negativo.");
				return;
			}
			if(!FornecedorDAO.CheckCodFornecedor(forn.Cod_fornecedor()))
			{
				Dialog("Este codigo de fornecedor ja existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo do fornecedor deve ser um numero valido.");
			return;
		}
		
		if (!forn.Nome_fornecedor(txtNom.getText()))
		{
			Dialog("O nome do fornecedor nao pode ser vazio");
			return;
		}

		if (!FornecedorDAO.insert(forn))
		{
			Dialog("Não foi possivel salvar");
			return;
		}
		Dialog("Sucesso");
		txtCod.setText("");
		txtNom.setText("");
	}
	
	@FXML
	protected void btnAtualizarHandler(ActionEvent event) 
	{
		FornecedorVO forn = new FornecedorVO();
		try
		{
			if (!forn.Cod_fornecedor(Integer.parseInt(txtCod.getText())))
			{
				Dialog("O codigo do fornecedor nao pode ser negativo.");
				return;
			}
			if (!CompraDAO.CheckCodFornecedor(forn.Cod_fornecedor()))
			{
				Dialog("Este codigo de fornecedor nao existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo do fornecedor deve ser um numero valido.");
			return;
		}
		
		if (!forn.Nome_fornecedor(txtNom.getText()))
		{
			Dialog("O nome do fornecedor nao pode ser vazio");
			return;
		}

		if (!FornecedorDAO.update(forn))
		{
			Dialog("Não foi possivel salvar");
			return;
		}
		Dialog("Sucesso");
		txtCod.setText("");
		txtNom.setText("");
	}
	
	@FXML
	protected void btnPesquisarHandler(ActionEvent event)
	{
		int cod = 0;
		try
		{
			cod = Integer.parseInt(txtCod.getText());
		}
		catch (Exception e)
		{
			Dialog("O codigo de compra deve ser um numero valido.");
			return;
		}

		List<FornecedorVO> forns = FornecedorDAO.search(cod);
		if (forns == null)
		{
			Dialog("Erro na conexao com o banco de dados.");
		}
		if (forns.isEmpty())
		{
			Dialog("Nao foi encontrado nenhum fornecedor associado com esse codigo.");
			return;
		}
		FornecedorVO forn = new FornecedorVO();
		forn = forns.get(0);
		txtCod.setText(""+forn.Cod_fornecedor());
		txtNom.setText(forn.Nome_fornecedor());
	}
}
