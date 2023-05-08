package webweeg.start.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import webweeg.start.model.FahradKomponent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static webweeg.start.dateiBase.komponentConnectHelper.getVerbindung;

public class KomponentTableControler implements Initializable {
    @FXML public Button butRefresch;
    @FXML
    public Button butAdd;
    @FXML
    public Button butEdit;
    @FXML
    public Button butDel;
    @FXML
    public HBox boxSelFahrad;
    @FXML
    public Button butNeueRad;
    @FXML
    public Button butApStart;
    @FXML
    public ListView ListaAleKomponente;
    @FXML
    private TableView<FahradKomponent> fahradKomponentsList;

    @FXML
    private TableColumn<FahradKomponent, Integer> colKompId;
    @FXML
    private TableColumn<FahradKomponent, String> colKompTyp;
    @FXML
    private TableColumn<FahradKomponent, String> colTyp;
    @FXML
    private TableColumn<FahradKomponent, String> colKlase;
    @FXML
    private TableColumn<FahradKomponent, String> colName;
    @FXML
    private TableColumn<FahradKomponent, String> colMarke;
    @FXML
    private TableColumn<FahradKomponent, String> colFarbe;
    @FXML
    private TableColumn<FahradKomponent, Double> colPreis;
    @FXML
    private TableColumn<FahradKomponent, Double> colGewicht;
    @FXML
    private TableColumn<FahradKomponent, String> colMaterial;

    //JavaFX Build GUI ohne funktioniert nicht ich muss noch in zukunft verbessern
    private Stage stage;
    private Scene scene;
    private Parent root;

    String query = null;
    Connection verbindung = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    FahradKomponent fahradKomponent = null;

    ObservableList<FahradKomponent> fahradKomponents = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //Dise mehoden für javaFX GUI
    public void zurück(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void refreschTable() {
        try {

            fahradKomponents.clear();

            query = "SELECT * FROM FahradKomponent";
            preparedStatement = verbindung.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
             FahradKomponent fk = new FahradKomponent(
                        resultSet.getInt("komponentId"),
                        resultSet.getString("fahradKomponentTyp"),
                        resultSet.getString("fahradKlase"),
                        resultSet.getString("fahradTyp"),
                        resultSet.getString("name"),
                        resultSet.getString("marke"),
                        resultSet.getString("material"),
                        resultSet.getString("farbe"),
                        resultSet.getDouble("preis"),
                        resultSet.getDouble("gewicht"));
             fahradKomponents.add(fk);
               // System.out.println(fk);
            }
            fahradKomponentsList.setItems(fahradKomponents);
        } catch (SQLException e) {
            Logger.getLogger(KomponentTableControler.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    void loadDate() throws SQLException {
        verbindung = getVerbindung();
        refreschTable();
        colKompId.setCellValueFactory(new PropertyValueFactory<>("komponentId"));
        colKompTyp.setCellValueFactory(new PropertyValueFactory<>("fahradKomponentTyp"));
        colTyp.setCellValueFactory(new PropertyValueFactory<>("fahradTyp"));
        colKlase.setCellValueFactory(new PropertyValueFactory<>("fahradKlase"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMarke.setCellValueFactory(new PropertyValueFactory<>("marke"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colFarbe.setCellValueFactory(new PropertyValueFactory<>("farbe"));
        colPreis.setCellValueFactory(new PropertyValueFactory<>("preis"));
        colGewicht.setCellValueFactory(new PropertyValueFactory<>("gewicht"));

    }

    public void neueKomponent(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/addKomponent.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 800, 700);
            stage.setScene(scene);
            stage.show();
    }

    public void editirenKomponent(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/webweeg/start/addKomponent.fxml"));
        Parent root = loader.load();
        AddKomponentControler controler = loader.getController();
        controler.setKomponent(fahradKomponentsList.getSelectionModel().getSelectedItem());
        controler.InitializeFelder();
        System.out.println(fahradKomponentsList.getSelectionModel().getSelectedItem());
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();}

    private void createFahradKomponentList(ListView <FahradKomponent>ListaAleKomponente) {
        var Lista = new ListCell<Object>() {
            protected void updateItem(FahradKomponent fahradKomponent, boolean empty) {
                super.updateItem(fahradKomponent, empty);
                if (empty || fahradKomponent == null) {
                    setText(null);
                } else {
                    setText("%s (%d &s)".formatted(fahradKomponent.getName(), fahradKomponent.getMarke(), fahradKomponent.getPreis()));
                }
            }

        };
    }

    public void fahradBauen(ActionEvent click) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/mein-fahrad.fxml"));
        stage = (Stage) ((Node)click.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 700);
        stage.setScene(scene);
        stage.show();
    }







    /*private ObjectProperty<FahradKomponent> selectFahradKomponents;

    public KomponentTableControler(){
        selectFahradKomponents = new SimpleObjectProperty<>();
    }
    public FahradKomponent getSelectFahradKomponents(){
        return selectFahradKomponents.get();
    }
    public void setSelectFahradKomponents(FahradKomponent fahradKomponent){
        selectFahradKomponents.set(fahradKomponent);
    }
    public ObjectProperty<FahradKomponent> selectFahradKomponentProperyty(){
        return selectFahradKomponents;
    }

    @FXML
    private void loadDate() throws SQLException {

        connection("FahradKomponent");
        refreschTable();

        colKompId.setCellValueFactory(new PropertyValueFactory<>("komponentId"));
        colKompTyp.setCellValueFactory(new PropertyValueFactory<>("fahradKomponentTyp"));
        colTyp.setCellValueFactory(new PropertyValueFactory<>("fahradTyp"));
        colKlase.setCellValueFactory(new PropertyValueFactory<>("fahradKlase"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMarke.setCellValueFactory(new PropertyValueFactory<>("marke"));
        colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        colFarbe.setCellValueFactory(new PropertyValueFactory<>("farbe"));
        colPreis.setCellValueFactory(new PropertyValueFactory<>("preis"));
        colGewicht.setCellValueFactory(new PropertyValueFactory<>("gewicht"));
    }

    public void zurück(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connection(String baza) {
        Connection connection = null;
        try {
            // Wskazanie jaki rodzaj bazy danych będzie wykorzystany, tu sqlite
            Class.forName("org.sqlite.JDBC");
            // Połączenie, wskazujemy rodzaj bazy i jej nazwę
            connection = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
            System.out.println("Ich bin mit DB browsswer verbunden " + baza);
        } catch (Exception e) {
            System.err.println("Fehler mit verbindung mit DB browsswer: \n" + e.getMessage());
            return null;
        }
        return connection;
    }
    @FXML
    private void refreschTable() throws SQLException {
            fahradKomponents.clear();

            query = "SELECT * FROM * FahradKomponent";
            preparedStatement = verbindung.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer komponentId = resultSet.getInt("komponentId");
                String fahradKomponentTyp = resultSet.getString("fahradKomponentTyp");
                String fahradTyp = resultSet.getString("fahradTyp");
                String fahradKlase = resultSet.getString("fahradKlase");
                String name = resultSet.getString("name");
                String marke = resultSet.getString("marke");
                String material = resultSet.getString("material");
                String farbe = resultSet.getString("farbe");
                Double preis = resultSet.getDouble("preis");
                Double gewicht = resultSet.getDouble("gewicht");

                fahradKomponentsList.setItems(fahradKomponents);

                System.out.print("In unserem Lager finden Sie komponenten:\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }


    }
private Stage stage;
    private Scene scene;
    private Parent root;
    public void zurück(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/webweeg/start/hello-fahrad.fxml"));
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root, 600, 700);
    stage.setScene(scene);
    stage.show();
}
    private ObjectProperty<FahradKomponent> selectedFahradKomponent;

    public KomponentTableControler() {
        // die Observable Property kann direkt im Konstruktor erzeugt werden
        selectedFahradKomponent = new SimpleObjectProperty<>();
    }

    // Observable Property hat 3 Elemente:
    // getter
    public FahradKomponent getSelectedFahradKomponent() {
        // das Objekt aus der Property liefern
        return selectedFahradKomponent.get();
    }

    // setter
    public void setSelectedFahradKomponent(FahradKomponent fahradKomponent) {
        selectedFahradKomponent.set(fahradKomponent);
    }

    // die Property selber
    public ObjectProperty<FahradKomponent> selectedFahradProperty() {
        return selectedFahradKomponent;
    }


    @Override public void initialize(URL location, ResourceBundle resources) {

        try {
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent");

            while (result.next()){
                Integer komponentId = result.getInt("komponentId");
                String fahradKomponentTyp = result.getString("fahradKomponentTyp");
                String fahradTyp = result.getString("fahradTyp");
                String fahradKlase = result.getString("fahradKlase");
                String name = result.getString("name");
                String marke = result.getString("marke");
                String material = result.getString("material");
                String farbe = result.getString("farbe");
                Double preis = result.getDouble("preis");
                Double gewicht = result.getDouble("gewicht");

                colKompId.setCellValueFactory(new PropertyValueFactory<>("komponentId"));
                colKompTyp.setCellValueFactory(new PropertyValueFactory<>("fahradKomponentTyp"));
                colTyp.setCellValueFactory(new PropertyValueFactory<>("fahradTyp"));
                colKlase.setCellValueFactory(new PropertyValueFactory<>("fahradKlase"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colMarke.setCellValueFactory(new PropertyValueFactory<>("marke"));
                colMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
                colFarbe.setCellValueFactory(new PropertyValueFactory<>("farbe"));
                colPreis.setCellValueFactory(new PropertyValueFactory<>("preis"));
                colGewicht.setCellValueFactory(new PropertyValueFactory<>("gewicht"));

                fahradKomponentsList.getSelectionModel().selectedItemProperty().addListener(
                        (o, oldVal, newVal) -> {
                            System.out.println("ListView Selection Änderung: "
                                    + (newVal != null ? newVal.toString() : "null"));
                            setSelectedFahradKomponent((FahradKomponent) newVal);
                            // wir könnten hier alle weiteren Anpassungen machen,
                            // wollen das aber über Property Binding (siehe Variante 2) machen
                        }
                );

                butEdit.disableProperty().bind(Bindings.isNotNull(selectedFahradKomponent));
                butDel.disableProperty().bind(Bindings.isNull(selectedFahradKomponent));

                boxSelFahrad.visibleProperty().bind(Bindings.isNull(selectedFahradKomponent));
                colKompId.textProperty().bind(Bindings.selectString(selectedFahradKomponent, "komponentId"));

                System.out.print("In unserem Lager finden Sie komponenten:\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);

            }} catch (Exception e) {
            System.out.println("ich kann nicht Datei finden " + e.getMessage());;
        }
    }

    private static Connection connection(String baza) {
        Connection connection = null;
        try {
            // Wskazanie jaki rodzaj bazy danych będzie wykorzystany, tu sqlite
            Class.forName("org.sqlite.JDBC");
            // Połączenie, wskazujemy rodzaj bazy i jej nazwę
            connection = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
            System.out.println("Ich bin mit DB browsswer verbunden " + baza);
        } catch (Exception e) {
            System.err.println("Fehler mit verbindung mit DB browsswer: \n" + e.getMessage());
            return null;
        }
        return connection;
    }
    private static ResultSet executeSelect(String selectQuery){
        try {
            Connection verbindung = connection("fahradStart.db");
            Statement statement = verbindung.createStatement();
            return  statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    //Zaczynamy raz jeszcze...
}