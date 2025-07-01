package br.ufrn.imd.visao;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Menu extends Application {
	
	private Stage stage;
	private BorderPane tlPrincipal;
	
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.setTitle("Teste Battleship");
		initPrincipal();
	}

	private void initPrincipal() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("/br/ufrn/imd/visao/TelaPrincipal.fxml"));
		try {
			tlPrincipal = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(tlPrincipal);
		scene.getStylesheets().add(getClass().getResource("/br/ufrn/imd/util/TelaEstilo.css").toExternalForm());
		
		stage.setScene(scene);
		stage.show();
		
	}
}
