// Álisson Kawachi RA: 081160011
// Ivan Zanutto RA081160003



package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
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
		launch();
	}
}
