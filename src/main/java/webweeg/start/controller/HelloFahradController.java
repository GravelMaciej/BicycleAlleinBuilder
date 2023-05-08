package webweeg.start.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class HelloFahradController {

    private Stage stage;
    private Scene scene;
    private Parent root;

 /*   @FXML
    public void openLager(ActionEvent event) throws IOException {
        URL urlToFxml = getClass().getResource("komponent-table.fxml");
        FXMLLoader loader = new FXMLLoader(urlToFxml);
        Parent sceneRoot = loader.load();
        Stage komponentStage = new Stage();
        Scene scene = new Scene(sceneRoot, 600, 400);
        komponentStage.setScene(scene);
        komponentStage.setTitle("In unserem Lager finden Sie...");
        komponentStage.showAndWait();

    }*/

    public void linkAktoin(ActionEvent actionEvent) {
        System.out.println("Ich will noch mehr machen");
    }

    public void meinFahrad(ActionEvent actionEvent) {
        System.out.println("Ich will neue Fahrrad bauen.");
    }

    /*public Connection openLager(ActionEvent actionEvent) throws IOException {
        String baza = "fahradStart.db";
            Connection verbindung = null;
            try {
                // Wskazanie jaki rodzaj bazy danych będzie wykorzystany, tu sqlite
                Class.forName("org.sqlite.JDBC");
                // Połączenie, wskazujemy rodzaj bazy i jej nazwę
                verbindung = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
                System.out.println("Ich bin mit DB browsswer verbunden " + baza);
            } catch (Exception e) {
                System.err.println("Fehler mit verbindung mit DB browsswer: \n" + e.getMessage());
                return null;
            }
            return verbindung;
        }
    */
    public void NeuKomponenteDazu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/addKomponent.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void openLager(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/komponent-table.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void fahradBauen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/mein-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void zurück(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

}