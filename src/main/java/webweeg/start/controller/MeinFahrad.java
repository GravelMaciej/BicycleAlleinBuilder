package webweeg.start.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import webweeg.start.dateiBase.KomponenteAnschauen;

import java.io.IOException;
import java.sql.ResultSet;

import static webweeg.start.dateiBase.komponentConnectHelper.executeSelect;

public class MeinFahrad {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void zurück(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void newKundeFenster(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/GescheftFürKunde.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void schowRahmen(ActionEvent actionEvent) {
       KomponenteAnschauen.schowRahmenKomponente();
    }

    public void schowAntrieb(ActionEvent actionEvent) {
        KomponenteAnschauen.schowAntriebKomponente();
    }

    public void schowLaufrad(ActionEvent actionEvent) {
        KomponenteAnschauen.schowLaufradKomponente();
    }

    public void schowBremsen(ActionEvent actionEvent) {
        KomponenteAnschauen.schowBremsenKomponente();
    }

    public void schowAndare(ActionEvent actionEvent) {
        KomponenteAnschauen.schowAndareKomponente();
    }

}
