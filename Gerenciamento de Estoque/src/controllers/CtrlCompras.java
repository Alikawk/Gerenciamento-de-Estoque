package controllers;

import java.io.IOException;

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
	private TextField txtTem;
    
	public void ShowCompras() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowCompras.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	public static void main(String[] args) {
	}
	
	public static void cadastrar()
	{
		
	}

	@FXML
	protected void btnAdcionarHandler(ActionEvent event) 
	{
		ComprasVO compras = new ComprasVO();
		compras.Fornecedor = txtFor.getText();
		compras.Produto = txtPro.getText();
		try
		{
			compras.Preco = Double.parseDouble(txtPre.getText().replace(',', '.'));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "preço inválido");
			return;
		}
		try
		{
			compras.Quantidade = Integer.parseInt(txtQua.getText());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "quantidade inválida");
			return;
		}
		try
		{
			compras.Tempo = Integer.parseInt(txtTem.getText());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "valor de tempo inválido");
			return;
		}
		ComprasDAO.insert(compras);
		//lblTitle.setText("Cadastro de Pets");
		txtFor.setText("");
		txtPro.setText("");
		txtPre.setText("");
		txtQua.setText("");
		txtTem.setText("");
	}
}
