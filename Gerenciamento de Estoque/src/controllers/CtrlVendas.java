package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class CtrlVendas {
    @FXML
    private AnchorPane childPane;

	public void ShowVendas() throws IOException{
        AnchorPane pnlOne = FXMLLoader.load(this.getClass().getResource("./ShowVendas.fxml"));
        childPane.getChildren().setAll(pnlOne);
	}
	public static void main(String[] args) {
	}
}
