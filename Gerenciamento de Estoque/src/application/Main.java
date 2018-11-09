// Álisson Kawachi RA: 081160011
// Ivan Zanutto RA081160003

package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import library.*;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("./ScreenMain.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		    primaryStage.setResizable(false);
		    primaryStage.setTitle("Estoque");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		/*ComprasVO c = new ComprasVO();
		c.Fornecedor = "teste";
		c.Produto = "teste3";
		c.Preco = 200.0;
		c.Quantidade = 50;
		c.Tempo = 2;
		System.out.println(ComprasDAO.insert(c));*/
		Connection co = null;
		try {
			co = SqlConnection.getConn();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet r = Support.executeCommand("select * from compras", co);
		try {
			r.next();
			System.out.println(r.getString("produto"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		launch();
	}
}
