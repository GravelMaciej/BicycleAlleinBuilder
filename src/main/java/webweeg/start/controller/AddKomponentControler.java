package webweeg.start.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import webweeg.start.dateiBase.komponentConnectHelper;
import webweeg.start.model.FahradKomponent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddKomponentControler implements Initializable{

    public FahradKomponent getKomponent() {
        return komponent;
    }

    public void setKomponent(FahradKomponent komponent) {
        this.komponent = komponent;
        System.out.println("set" + komponent);
    }

    private FahradKomponent komponent;
    @FXML
    public Button speichern;
    public Button SetUpdateFahradKomponent;
    @FXML
    private TextField FeldkomponentId;
    @FXML
    private TextField FeldFahradKlase;
    @FXML
    private TextField FeldFahradTyp;
    @FXML
    private TextField FeldFahradKomponentTyp;
    @FXML
    private TextField FeldName;
    @FXML
    private TextField FeldMarke;
    @FXML
    private TextField FeldMaterial;
    @FXML
    private TextField FeldFarbe;
    @FXML
    private TextField FeldPreis;
    @FXML
    private TextField FeldGewicht;

    private static final String DeleteKomponent = "DELETE FROM FahradKomponent WHERE komponentId = ?";

    private Stage stage;
    private Scene scene;

    private Parent root;






    String query = null;
    Connection verbindung = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    FahradKomponent fahradKomponent = null;
    @FXML
    private boolean UpdateFahradKomponent;


    private Throwable ex;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            komponentConnectHelper.getVerbindung();
            System.out.println("Witamy w nowym ...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void InitializeFelder(){
        if (komponent != null){
            System.out.println("Hier");
            FeldkomponentId.setText(Integer.toString(komponent.getKomponentId()));
            FeldFahradKomponentTyp.setText(komponent.getFahradKomponentTyp());
            FeldFahradTyp.setText(komponent.getFahradTyp());
            FeldFahradKlase.setText(komponent.getFahradKlase());
            FeldName.setText(komponent.getName());
            FeldMarke.setText(komponent.getMarke());
            FeldMaterial.setText(komponent.getMaterial());
            FeldFarbe.setText(komponent.getFarbe());
            FeldPreis.setText(Double.toString(komponent.getPreis()));
            FeldGewicht.setText(Double.toString(komponent.getGewicht()));
        }
    }


 /*   public void speichern(MouseEvent event) throws SQLException, ClassNotFoundException {
        verbindung = komponentConnectHelper.getVerbindung();
        String fahradKomponentTyp = resultSet.getString("fahradKomponentTyp");
        String fahradTyp = resultSet.getString("fahradTyp");
        String fahradKlase = resultSet.getString("fahradKlase");
        String name = resultSet.getString("name");
        String marke = resultSet.getString("marke");
        String material = resultSet.getString("material");
        String farbe = resultSet.getString("farbe");
        Double preis = resultSet.getDouble("preis");
        Double gewicht = resultSet.getDouble("gewicht");
        if(fahradKomponentTyp.isEmpty() || fahradTyp.isEmpty() || fahradKlase.isEmpty() || name.isEmpty() || marke.isEmpty()
        || material.isEmpty() || farbe.isEmpty() || preis.isInfinite() || gewicht.isInfinite()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Bitte noch mall alles prüffen");
            alert.setContentText("Alle Felder sind Pflicht");
            alert.showAndWait();
        }else {
            getQuery();
            insert();
            clean();
        }
    }

    private void clean(){
        fahradKomponentTyp.setText(null);
        fahradTyp.setText(null);
        fahradKlase.setText(null);
        name.setText(null);
        marke.setText(null);
        material.setText(null);
        farbe.setText(null);
        preis.setText(null);
        gewicht.setText(null);
    }

    // MIt disem methode ich prüfe am wichtigste detail in DB...Alles muss stimmen. komponentID ist null, weil in Table ist autoinkrement
    private void getQuery(){
        if(!isUpdateFahradKomponent){
            query = "INSERT INTO FahradKomponent( fahradKomponentTyp, fahradTyp, fahradKlase, name, marke, material, farbe, preis, gewicht)VALUES\" +\n" +
                    "            \"VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)\"";
        }else {
            query = "UPDATE FahradKomponent SET  fahrafdKomponentTyp = ?, fahradTyp = ?, fahradKlase = ?, name = ?, marke = ?, material ?,  farbe = ?, preis = ?, gewicht = ?" +
                    "where komponentId = ?" ;
        }
    }
*/


    public void zurück(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,800, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void speichern(MouseEvent event) {

        try {

        if (FeldFahradKomponentTyp.getText().isEmpty() ||FeldFahradTyp.getText().isEmpty() || FeldFahradKlase.getText().isEmpty() || FeldName.getText().isEmpty() ||FeldMarke.getText().isEmpty() || FeldMaterial.getText().isEmpty() ||
            FeldFarbe.getText().isEmpty() || FeldPreis.getText().isEmpty() || FeldGewicht.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alle Felder bitte ausfülenn!!");
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            verbindung = komponentConnectHelper.getVerbindung();
            System.out.println("Ich bin mit DB verbinden..");

            getQuery();
            insert();
            AllesEntfernen();
        }
        } catch (SQLException e)
        {throw new RuntimeException(e);}
    }

    void setTextField(int komponentId, String fahradKomponentTyp, String fahradTyp, String fahradKlase, String name, String marke, String material,
                      String farbe, Double preis, Double gewicht) {

        komponentId = Statement.RETURN_GENERATED_KEYS;
        FeldFahradKomponentTyp.setText(fahradKomponentTyp);
        FeldFahradTyp.setText(fahradTyp);
        FeldFahradKlase.setText(fahradKlase);
        FeldName.setText(name);
        FeldMarke.setText(marke);
        FeldMaterial.setText(material);
        FeldFarbe.setText(farbe);
        FeldPreis.setText((String.valueOf(preis)));
        FeldGewicht.setText(String.valueOf(gewicht));
    }
    private void getQuery(){
            if(komponent == null){
                query = "INSERT INTO FahradKomponent(fahradKomponentTyp, fahradTyp,  fahradKlase, name, marke, material, farbe, preis, gewicht)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }else{
                query = "UPDATE FahradKomponent SET fahradKomponentTyp = ?, fahradTyp = ?, fahradKlase = ?, name = ?, marke = ?, material = ?, farbe = ?, preis = ?, gewicht = ? WHERE komponentId = ?";
            }
        }


    @FXML
    private void insert() throws SQLException {
        try{
            preparedStatement = verbindung.prepareStatement(query);
            System.out.println(FeldFahradKomponentTyp.getText());
            preparedStatement.setString(1, FeldFahradKomponentTyp.getText());
            preparedStatement.setString(2, FeldFahradTyp.getText());
            preparedStatement.setString(3, FeldFahradKlase.getText());
            preparedStatement.setString(4, FeldName.getText());
            preparedStatement.setString(5, FeldMarke.getText());
            preparedStatement.setString(6, FeldMaterial.getText());
            preparedStatement.setString(7, FeldFarbe.getText());
            preparedStatement.setDouble(8, Double.valueOf(FeldPreis.getText()));
            preparedStatement.setDouble(9,Double.valueOf(FeldGewicht.getText()));
            if(komponent != null){
                preparedStatement.setInt(10, komponent.getKomponentId());
            } preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.err.println("Ich kann nicht neue Fahrrad Komponnet dazu geben weil...");
            e.printStackTrace();
        }
    }

    @FXML
    private void AllesEntfernen() {
        FeldkomponentId.setText(null);
        FeldFahradKomponentTyp.setText(null);
        FeldFahradTyp.setText(null);
        FeldFahradKlase.setText(null);
        FeldName.setText(null);
        FeldMarke.setText(null);
        FeldMaterial.setText(null);
        FeldFarbe.setText(null);
        FeldPreis.setText(null);
        FeldGewicht.setText(null);

    }
    @FXML
    public static void del(int komponentId) throws SQLException {
        try (Connection verbin = komponentConnectHelper.getVerbindung()){
            PreparedStatement dK = verbin.prepareStatement(DeleteKomponent);
            dK.setInt(1, komponentId );
            int recordsAffected = dK.executeUpdate();
            // wenn es keinen Datensatz mit der Id mehr gegeben hat
            if (recordsAffected == 0) {
                System.out.println("Komponent mit " + komponentId + "ist gelöscht");
            }
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ich kann nicht löschen");
            alert.setContentText("Wo wor gehen jetzt");
            alert.show();
        }
    }


}
