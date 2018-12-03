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
import library.VendaDAO;
import library.VendaVO;

public class CtrlVendas {
    @FXML
    private AnchorPane childPane;
    
    @FXML
    private TextField txtVen;
    
    @FXML
    private TextField txtCli;
    
    @FXML
    private TextField txtPro;
    
    @FXML
    private TextField txtPre;
    
    @FXML
    private TextField txtDat;
    
    @FXML
    private TextField txtQtde;
    
	public void ShowVendas() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowVendas.fxml"));
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
		VendaVO venda = new VendaVO();
		try
		{
			if (!venda.Cod_venda(Integer.parseInt(txtVen.getText())))
			{
				Dialog("O codigo de venda nao pode ser negativo.");
				return;
			}
			if (!VendaDAO.CheckCodVenda(venda.Cod_venda()))
			{
				Dialog("Este codigo de venda ja existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo de venda deve ser um numero valido.");
			return;
		}
		
		try
		{
			if (!venda.Cod_cliente(Integer.parseInt(txtCli.getText())))
			{
				Dialog("O codigo de cliente nao pode ser negativo");
				return;
			}
			if (!VendaDAO.CheckCodCliente(venda.Cod_cliente()))
			{
				Dialog("Este codigo de cliente nao existe.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("O codigo de cliente deve ser um numero valido.");
			return;
		}

		try
		{
			if (!venda.Cod_produto(Integer.parseInt(txtPro.getText())))
			{
				Dialog("O codigo de produto nao pode ser negativo");
				return;
			}
			if (!VendaDAO.CheckCodProduto(venda.Cod_produto()))
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
			if (!venda.Qtde_vendida(Integer.parseInt(txtQtde.getText())))
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
			if (!venda.Valor_venda(Double.parseDouble(txtPre.getText().replace(',', '.'))))
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
			if (!venda.Data_venda((new SimpleDateFormat("dd/MM/yyyy")).parse(txtDat.getText())))
			{
				Dialog("A Data não pode estar no futuro.");
				return;
			}
		}
		catch (Exception e)
		{
			Dialog("A data deve estar num formato valido.");
			return;
		}
		
		/*try
		{
			
		}
		catch (Exception e)
		{
			
		}*/
		if (!VendaDAO.insert(venda))
		{
			Dialog("Não foi possivel salvar");
			return;
		}
		Dialog("Sucesso");
		txtCli.setText("");
		txtPro.setText("");
		txtPre.setText("");
		txtQtde.setText("");
		txtVen.setText("");
		txtDat.setText("");
	}
	
	@FXML
	protected void btnPesquisarHandler(ActionEvent event)
	{
		int cod = 0;
		try
		{
			cod = Integer.parseInt(txtVen.getText());
		}
		catch (Exception e)
		{
			Dialog("O codigo de venda deve ser um numero valido.");
			return;
		}

		List<VendaVO> vendas = VendaDAO.search(cod);
		if (vendas == null)
		{
			Dialog("Erro na conexao com o banco de dados.");
		}
		if (vendas.isEmpty())
		{
			Dialog("Nao foi encontrada nenhuma venda associada com esse codigo.");
			return;
		}
		VendaVO venda = new VendaVO();
		venda = vendas.get(0);
		txtCli.setText(""+venda.Cod_cliente());
		txtPro.setText(""+venda.Cod_produto());
		txtPre.setText(""+venda.Valor_venda());
		txtQtde.setText(""+venda.Qtde_vendida());
		txtDat.setText((new SimpleDateFormat("dd/MM/yyyy")).format(venda.Data_venda()));
	}
}
