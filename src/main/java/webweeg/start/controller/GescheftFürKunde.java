package webweeg.start.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class GescheftFürKunde {

    private Stage stage;
    private Scene scene;

    private Parent root;



    public void fahradBauen(ActionEvent click) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/mein-fahrad.fxml"));
        stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }



        public void cancel(ActionEvent event) {
        }
}
