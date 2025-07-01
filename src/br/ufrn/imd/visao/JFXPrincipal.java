package br.ufrn.imd.visao;

import javafx.application.Application;
import javafx.stage.Stage;

public class JFXPrincipal extends Application {

	@Override
	public void start(Stage primaryStage) {
		Menu menu = new Menu();
		menu.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
