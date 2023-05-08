package webweeg.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import webweeg.start.controller.AddKomponentControler;

import java.io.IOException;
import java.net.URL;

public class HelloFahrad extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
      URL urlToFxml = getClass().getResource("hello-fahrad.fxml");
      FXMLLoader loader = new FXMLLoader(urlToFxml);
      Parent sceneRoot = loader.load();
      Scene scene = new Scene(sceneRoot, 800, 700);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Welcome");
      primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}