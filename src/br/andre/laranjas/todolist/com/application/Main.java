package br.andre.laranjas.todolist.com.application;

import java.util.List;

import br.andre.laranjas.todolist.com.io.TarefaIO;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TarefaIO.createFiles();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Index.fxml"));
			Scene scene = new Scene(root, 934, 500);
			scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("To do List");
			Image icone = new Image(getClass().getResource("../imagens/check.png").toExternalForm());
			primaryStage.getIcons().add(icone);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
