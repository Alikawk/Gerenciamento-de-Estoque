package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class CtrlMain {
	@FXML
	private AnchorPane childPane;

	public void ShowVendas() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ScreenVendas.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowGerenciar() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ScreenGerenciar.fxml"));
			childPane.getChildren().setAll(pnlOne);
			childPane.getParent(); // TODO set title
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ShowConsultar() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/screenConsultar.fxml"));
			childPane.getChildren().setAll(pnlOne);
			childPane.getParent(); // TODO set title
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowFornecedores() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ScreenFornecedores.fxml"));
			childPane.getChildren().setAll(pnlOne);
			childPane.getParent(); // TODO set title
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ShowClientes() throws IOException {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ScreenClientes.fxml"));
			childPane.getChildren().setAll(pnlOne);
			childPane.getParent(); // TODO set title
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	

	public void ShowCompras() {
		try {
			AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("../application/ScreenCompras.fxml"));
			childPane.getChildren().setAll(pnlOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
}

