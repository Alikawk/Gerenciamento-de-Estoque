package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.*;

public class CtrlCompras {
    @FXML
    private AnchorPane childPane;

    @FXML
	private TextField txtFor;
    
    @FXML
	private TextField txtPro;
    
    @FXML
	private TextField txtPre;
    
    @FXML
	private TextField txtQua;
    
    @FXML
    private TextField txtCom;
    
    @FXML
    private TextField txtDat;
    
    
	public void ShowCompras() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowCompras.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	public static void main(String[] args) {
	}
	
	public static void Dialog(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	@FXML
	protected void btnAdicionarHandler(ActionEvent event) 
	{
		CompraVO compra = new CompraVO();
		try
		{
			if (!compra.Cod_compra(Integer.parseInt(txtCom.getText())))
			{
				Dialog("O codigo de compra nao pode ser negativo.");
				return;
			}
			if (!CompraDAO.CheckCodCompra(compra.Cod_compra()))
			{
				Dialog("Este codigo de compra ja existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo de compra deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!compra.Cod_fornecedor(Integer.parseInt(txtFor.getText())))
			{
				Dialog("O codigo de fornecedor nao pode ser negativo");
				return;
			}
			if (!CompraDAO.CheckCodFornecedor(compra.Cod_fornecedor()))
			{
				Dialog("Este codigo de fornecedor nao existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo de fornecedor deve ser um numero valido.");
			return;
		}

		try
		{
			if (!compra.Cod_produto(Integer.parseInt(txtPro.getText())))
			{
				Dialog("O codigo de produto nao pode ser negativo");
				return;
			}
			if (!CompraDAO.CheckCodProduto(compra.Cod_produto()))
			{
				Dialog("Este codigo de produto nao existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo de produto deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!compra.Qtde_comprada(Integer.parseInt(txtQua.getText())))
			{
				Dialog("A quantidade nao pode ser negativa ou zero.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("A quantidade deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!compra.Valor_compra(Double.parseDouble(txtPre.getText().replace(',', '.'))))
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
		
		try
		{
			if (!compra.Data_compra((new SimpleDateFormat("dd/MM/yyyy")).parse(txtDat.getText())))
			{
				Dialog("A Data não pode estar no futuro.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("A data deve estar num formato valido (dia/mes/ano).");
			return;
		}
		
		/*try
		{
			
		}
		catch (Exception e)
		{
			
		}*/
		if (!CompraDAO.insert(compra))
		{
			Dialog("Não foi possivel salvar");
			return;
		}
		Dialog("Sucesso");
		txtFor.setText("");
		txtPro.setText("");
		txtPre.setText("");
		txtQua.setText("");
		txtCom.setText("");
		txtDat.setText("");
	}
	
	@FXML
	protected void btnPesquisarHandler(ActionEvent event)
	{
		int cod = 0;
		try
		{
			cod = Integer.parseInt(txtCom.getText());
		}
		catch (Exception e)
		{
			Dialog("O codigo de compra deve ser um numero valido.");
			return;
		}

		List<CompraVO> compras = CompraDAO.search(cod);
		if (compras == null)
		{
			Dialog("Erro na conexao com o banco de dados.");
		}
		if (compras.isEmpty())
		{
			Dialog("Nao foi encontrada nenhuma compra associada com esse codigo.");
			return;
		}
		CompraVO compra = new CompraVO();
		compra = compras.get(0);
		txtFor.setText(""+compra.Cod_fornecedor());
		txtPro.setText(""+compra.Cod_produto());
		txtPre.setText(""+compra.Valor_compra());
		txtQua.setText(""+compra.Qtde_comprada());
		txtDat.setText((new SimpleDateFormat("dd/MM/yyyy")).format(compra.Data_compra()));
	}
}
