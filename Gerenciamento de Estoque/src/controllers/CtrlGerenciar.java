package controllers;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.CompraDAO;
import library.ProdutoDAO;
import library.ProdutoVO;
import library.ProdutoDAO;
import library.ProdutoVO;

public class CtrlGerenciar {
    @FXML
    private AnchorPane childPane;
    
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
    private TextField txtPrec;
    
    @FXML
    private TextField txtQtde;
    
    

	public void ShowGerenciar() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowGerenciar.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	public static void main(String[] args)
	{
	}
	
	public static void Dialog(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	@FXML
	protected void btnAdicionarHandler(ActionEvent event) 
	{
		ProdutoVO produto = new ProdutoVO();
		try
		{
			if (!produto.Cod_produto(Integer.parseInt(txtCod.getText())))
			{
				Dialog("O codigo do produto nao pode ser negativo.");
				return;
			}
			if(!ProdutoDAO.CheckCodProduto(produto.Cod_produto()))
			{
				Dialog("Este codigo de produto ja existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo do produto deve ser um numero valido.");
			return;
		}
		
		if (!produto.Nome_produto(txtNom.getText()))
		{
			Dialog("O nome do produto nao deve ser vazio.");
			return;
		}
		
		if (!produto.Posicao(txtPos.getText()))
		{
			Dialog("A posicao nao deve ser vazia.");
			return;
		}
		
		try
		{
			if (!produto.Tempo_reposicao(Integer.parseInt(txtTem.getText())))
			{
				Dialog("O tempo nao pode ser negativo");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O tempo deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!produto.Valor_produto(Double.parseDouble(txtPre.getText().replace(',', '.'))))
			{
				Dialog("O valor nao pode ser zero ou negativo.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O valor deve ser um numero valido.");
			return;
		}
		
		/*try
		{
			if (!produto.Qtde_produto(Integer.parseInt(txtQtde.getText())))
			{
				Dialog("A quantidade nao pode ser negativa.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("A quantidade deve ser um numero valido.");
			return;
		}*/
		
		if (!ProdutoDAO.insert(produto))
		{
			Dialog("Não foi possivel salvar");
			return;
		}
		Dialog("Sucesso");
		txtPos.setText("");
		txtNom.setText("");
		txtPre.setText("");
		txtQtde.setText("");
		txtCod.setText("");
		txtTem.setText("");
	}
	
	@FXML
	protected void btnAtualizarHandler(ActionEvent event) 
	{
		ProdutoVO produto = new ProdutoVO();
		try
		{
			if (!produto.Cod_produto(Integer.parseInt(txtCod.getText())))
			{
				Dialog("O codigo do produto nao pode ser negativo.");
				return;
			}
			if (!CompraDAO.CheckCodProduto(produto.Cod_produto()))
			{
				Dialog("Este codigo de produto nao existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo do produto deve ser um numero valido.");
			return;
		}
		
		if (!produto.Nome_produto(txtNom.getText()))
		{
			Dialog("O nome do produto nao deve ser vazio.");
			return;
		}
		
		if (!produto.Posicao(txtPos.getText()))
		{
			Dialog("A posicao nao deve ser vazia.");
			return;
		}
		
		try
		{
			if (!produto.Tempo_reposicao(Integer.parseInt(txtTem.getText())))
			{
				Dialog("O tempo nao pode ser negativo");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O tempo deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!produto.Valor_produto(Double.parseDouble(txtPre.getText().replace(',', '.'))))
			{
				Dialog("O valor nao pode ser zero ou negativo.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O valor deve ser um numero valido.");
			return;
		}
		
		/*try
		{
			if (!produto.Qtde_produto(Integer.parseInt(txtQtde.getText())))
			{
				Dialog("A quantidade nao pode ser negativa.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("A quantidade deve ser um numero valido.");
			return;
		}*/

		if (!ProdutoDAO.update(produto))
		{
			Dialog("Não foi possivel atualizar");
			return;
		}
		Dialog("Sucesso");
		txtPos.setText("");
		txtNom.setText("");
		txtPre.setText("");
		txtQtde.setText("");
		txtCod.setText("");
		txtTem.setText("");
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
			Dialog("O codigo do produto deve ser um numero valido.");
			return;
		}

		List<ProdutoVO> produtos = ProdutoDAO.search(cod);
		if (produtos == null)
		{
			Dialog("Erro na conexao com o banco de dados.");
		}
		if (produtos.isEmpty())
		{
			Dialog("Nao foi encontrado nenhum produto associado com esse codigo.");
			return;
		}
		ProdutoVO produto = new ProdutoVO();
		produto = produtos.get(0);
		txtPos.setText(""+produto.Posicao());
		txtNom.setText(""+produto.Nome_produto());
		txtPre.setText(""+produto.Valor_produto());
		txtQtde.setText(""+produto.Qtde_produto());
		txtTem.setText(""+produto.Tempo_reposicao());
	}
}
