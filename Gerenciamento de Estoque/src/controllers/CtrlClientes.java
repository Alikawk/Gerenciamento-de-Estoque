package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import library.ClienteDAO;
import library.ClienteVO;
import library.CompraDAO;
import library.ProdutoDAO;
import library.VendaDAO;

public class CtrlClientes {
	
	@FXML
	private AnchorPane childPane;
	
	@FXML
	private TextField txtNom;
	
	@FXML
	private TextField txtCod;


		public void ShowClientes() throws IOException{
	        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowClientes.fxml"));
	        childPane.getChildren().setAll(pnlOne);
		}
		
		public static void Dialog(String message)
		{
			JOptionPane.showMessageDialog(null, message);
		}

		@FXML
		protected void btnAdicionarHandler(ActionEvent event) 
		{
			ClienteVO cliente = new ClienteVO();
			try
			{
				if (!cliente.Cod_cliente(Integer.parseInt(txtCod.getText())))
				{
					Dialog("O codigo do cliente nao pode ser negativo.");
					return;
				}
				if(!ClienteDAO.CheckCodCliente(cliente.Cod_cliente()))
				{
					Dialog("Este codigo de cliente ja existe.");
					return;
				}
			}
			catch (Exception e)
			{
				Dialog("O codigo do cliente deve ser um numero valido.");
				return;
			}
			
			if (!cliente.Nome_cliente(txtNom.getText()))
			{
				Dialog("O nome do cliente nao pode ser vazio");
				return;
			}

			if (!ClienteDAO.insert(cliente))
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
			ClienteVO cliente = new ClienteVO();
			try
			{
				if (!cliente.Cod_cliente(Integer.parseInt(txtCod.getText())))
				{
					Dialog("O codigo do cliente nao pode ser negativo.");
					return;
				}
				if (!VendaDAO.CheckCodCliente(cliente.Cod_cliente()))
				{
					Dialog("Este codigo de produto nao existe.");
					return;
				}
			}
			catch (Exception e)
			{
				Dialog("O codigo do cliente deve ser um numero valido.");
				return;
			}
			
			if (!cliente.Nome_cliente(txtNom.getText()))
			{
				Dialog("O nome do cliente nao pode ser vazio");
				return;
			}

			if (!ClienteDAO.update(cliente))
			{
				Dialog("Não foi possivel atualizar");
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

			List<ClienteVO> clientes = ClienteDAO.search(cod);
			if (clientes == null)
			{
				Dialog("Erro na conexao com o banco de dados.");
			}
			if (clientes.isEmpty())
			{
				Dialog("Nao foi encontrado nenhum cliente associado com esse codigo.");
				return;
			}
			ClienteVO cliente = new ClienteVO();
			cliente = clientes.get(0);
			txtCod.setText(""+cliente.Cod_cliente());
			txtNom.setText(cliente.Nome_cliente());
		}
}
