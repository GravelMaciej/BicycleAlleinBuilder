package webweeg.start.dateiBase;

import javafx.scene.control.Alert;
import webweeg.start.model.FahradKomponent;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static javax.swing.UIManager.getInt;

public class komponentConnectHelper {

    private static Connection verbindung;
    private static String baza;

    private static final String SelFaKompSQL = "SELECT * FROM FahradKomponent komponentId, fahradKomponentTyp, fahradTyp, fahradKlase, name, marke, material, farbe, preis, gewicht " +
            "from FahradKomponent";

    private static final String InsFaKompSQL = "INSERT INTO FahradKomponent(\"fahradKomponentTyp\",\"fahradTyp\",\"fahradKlase\", \"name\", \"marke\", \"material\", \"farbe\", \"preis\", \"gewicht\")VALUES" +
            "( ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private static final String UpFaKompSQL = "UPDATE FahradKomponent SET komponentId = ?,fahrafdKomponentTyp = ?, fahradTyp = ?, fahradKlase = ?, name = ?, farbe = ?, preis = ?, gewicht = ? WHERE komponentId = ?";

    private static final String DelFaKompSQL = "DELETE FROM FahradKomponent WHERE komponentId = ? ";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {



        deleteFahradKomponent(30);
        //System.out.println("Komponent " + SelectId("komponentId == '12'"));
         //int komponentId = Statement.RETURN_GENERATED_KEYS;
         //neueFahradKomponent(new FahradKomponent(komponentId, "LAUFRAD", "GRAVEL", "MEDIUM", "105", "Schimano", "Aluminium", "Schwarz-Weiss",224.0 , 1.832));
        //SQLSelect("INSERT INTO FahradKomponent(\"komponentId\",\"fahradKomponentTyp\",\"fahradTyp\",\"fahradKlase\", \"name\", \"marke\", \"material\", \"farbe\", \"preis\", \"gewicht\")VALUES(18, 'ANTRIEB', 'RENNRAD, GRAVEL, STADRAD, CYCLOCROSS', 'PREMIUM', 'Ultegra DI2 6800', 'Schimano', 'Aluminium-Kunstoff', 'Schwarz-Grau', '1450.0', '1.560'  )");
    }

    public static Connection getVerbindung() throws SQLException {
        Connection verbindung = null;
        try {
            // Wskazanie jaki rodzaj bazy danych będzie wykorzystany, tu sqlite
            Class.forName("org.sqlite.JDBC");
            // Połączenie, wskazujemy rodzaj bazy i jej nazwę
            verbindung = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
            System.out.println("Ich bin mit DB browsswer verbunden " + "FahradKomponent");
        } catch (Exception e) {
            System.err.println("Fehler mit verbindung mit DB browsswer: \n" + e.getMessage());
            return null;
        }
        return verbindung;
    }

    public static Connection conection() throws SQLException, ClassNotFoundException {
        String connUrl = "jdbc:sqlite:fahradStart.db";
        String user = "root", password = "";
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(connUrl, user, password);
            System.out.println("Connection geöffnet");
        return connection;
    }

    public static ResultSet executeSelect(String selectQuery) {
        try {
            Connection verbindung = getVerbindung();
            Statement statement = verbindung.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String SQLSelect(String query){
        try {
            Connection verbindung = getVerbindung();
            Statement statement = verbindung.createStatement();
            statement.executeQuery(query);
            return ("Select * From FahradKomponent Where komponentId == '16'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SelectId(String SelFaKomp){
        try{
            Connection verbindung = getVerbindung();
            Statement statement = verbindung.createStatement();
            statement.executeQuery(SelFaKompSQL);
            System.out.println("Mit disem Id finden Sie " + SelFaKomp.toString());
        }catch (SQLException e){
            System.err.println("Das ist eine Fehler mit Methode");
            e.printStackTrace();
        }

        return SelFaKomp;
    }



    static void selectKomponentPerId(int komponentId) {
        try {
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE komponentId = ? ");
            while (result.next()) {
                //int komponentId = result.getInt("komponentId");
                String fahradKomponentTyp = result.getString("fahradKomponentTyp");
                String fahradTyp = result.getString("fahradTyp");
                String fahradKlase = result.getString("fahradKlase");
                String name = result.getString("name");
                String marke = result.getString("marke");
                String material = result.getString("material");
                String farbe = result.getString("farbe");
                Double preis = result.getDouble("preis");
                Double gewicht = result.getDouble("gewicht");


                System.out.println("Mit disem komponentId finden Sie" + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }
        } catch (Exception e) {
            System.out.println("ich kann nicht Datei finden " + e.getMessage());
        }
    }


    private FahradKomponent fahradKomponentFromResultSet(ResultSet executeSelect) throws SQLException {
        FahradKomponent fahradKomponent = new FahradKomponent();
        fahradKomponent.setKomponentId(executeSelect.getInt("komponentId"));
        fahradKomponent.setFahradKomponentTyp(executeSelect.getString("fahradKomponentTyp"));
        fahradKomponent.setFahradTyp(executeSelect.getString("fahradTyp"));
        fahradKomponent.setFahradKlase(executeSelect.getString("fahradKlase"));
        fahradKomponent.setName(executeSelect.getString("name"));
        fahradKomponent.setMarke(executeSelect.getString("marke"));
        fahradKomponent.setMaterial(executeSelect.getString("material"));
        fahradKomponent.setFarbe(executeSelect.getString("farbe"));
        fahradKomponent.setPreis(executeSelect.getDouble("preis"));
        fahradKomponent.setGewicht(executeSelect.getDouble("gewicht"));
        return fahradKomponent;
    }




    public static int neueFahradKomponent(FahradKomponent fahradKomponent) throws SQLException, ClassNotFoundException {
            Connection connection = null;
            Statement statement = null;
        try {
            String connUrl = "jdbc:sqlite:fahradStart.db";
            String user = "root", password = "";
            Class.forName("org.sqlite.JDBC");
            Connection verbindung = DriverManager.getConnection(connUrl, user, password);
            System.out.println("Connection geöffnet");
            PreparedStatement neueInsert = verbindung.prepareStatement(InsFaKompSQL, RETURN_GENERATED_KEYS);
            fahradKomponentToStatment(neueInsert, fahradKomponent, false);
            neueInsert.executeUpdate();
            ResultSet keys = neueInsert.getGeneratedKeys();
            keys.next();
            int newKomponentId = keys.getInt(1);
            System.out.printf("Neue Komponent, komponentId = %d\n", newKomponentId);
            return newKomponentId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //SQLSelect("INSERT INTO FahradKomponent(fahradKomponentTyp, fahradTyp , fahradKlase ,  name  marke, material, farbe, preis, gewicht)VALUES()");
    }

    private static void fahradKomponentToStatment(PreparedStatement nPstmt, FahradKomponent fahradKomponent, boolean forUpdate) throws SQLException {
        nPstmt.setString(1, fahradKomponent.getFahradKomponentTyp());
        nPstmt.setString(2, fahradKomponent.getFahradTyp());
        nPstmt.setString(3, fahradKomponent.getFahradKlase());
        nPstmt.setString(4,  fahradKomponent.getName());
        nPstmt.setString(5, fahradKomponent.getMarke());
        nPstmt.setString(6, fahradKomponent.getMaterial());
        nPstmt.setString(7, fahradKomponent.getFarbe());
        nPstmt.setDouble(8, fahradKomponent.getPreis());
        nPstmt.setDouble(9, fahradKomponent.getGewicht());
        if (forUpdate) {
            nPstmt.setInt(10, fahradKomponent.getKomponentId());
        }
    }



    public FahradKomponent selectByKomponentId(int komponentId) throws SQLException {
        try {
            verbindung = getVerbindung();
            PreparedStatement kstmt = verbindung.prepareStatement(SelFaKompSQL + "WHERE komponentID");
            kstmt.setInt(1, komponentId);
            ResultSet resultSet = kstmt.executeQuery();
            if (resultSet.next()) {
                return fahradKomponentFromResultSet(resultSet);
            } else {
                throw new SQLDataException();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fehler bei Datei in Basis verbindung!!! ", e);
        }
    }



    public static void updateFahradKomponent(FahradKomponent fahradKomponent) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String connUrl = "jdbc:sqlite:fahradStart.db";
            String user = "root", password = "";
            connection = DriverManager.getConnection(connUrl, user, password);
            PreparedStatement updateStatment = connection.prepareStatement(UpFaKompSQL + "WHERE komponentId");
            fahradKomponentToStatment(updateStatment, fahradKomponent, true);
            int recoordsAffected = updateStatment.executeUpdate();
            if (recoordsAffected == 0) {
                throw new SQLException("In unserem Gescheft " + fahradKomponent.getKomponentId() + " existiert nicht");
            }
            System.out.println("Alles ist in Ordnung ");
        } catch (SQLException e) {
            throw new SQLException("Momentdan wir haben Fehler in Verbindung", e);
        }
        return;
    }


    public static void deleteFahradKomponent(int komponentId) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Statement statement = null;
        try {
            String connUrl = "jdbc:sqlite:fahradStart.db";
            String user = "root", password = "";
            Class.forName("org.sqlite.JDBC");
            Connection verbindung = DriverManager.getConnection(connUrl, user, password);
            System.out.println("Connection geöffnet");
            PreparedStatement deleteKomponent = verbindung.prepareStatement(DelFaKompSQL, komponentId);
            deleteKomponent.setInt(10, komponentId);
            int fahradKomponentAffected = deleteKomponent.executeUpdate();
            System.out.println("Komponent mit " + komponentId + "werde gelöscht");

            PreparedStatement deleteFaKomp = verbindung.prepareStatement("DELETE from fahradKomponentTyp WHERE komponentId = ?");

            deleteFaKomp.setInt(1, komponentId);
            int recordAffected = deleteFaKomp.executeUpdate();

            if (recordAffected == 1) {
                System.out.println("Komponent gelöscht " + komponentId);
            } else if (recordAffected == 0) {
                System.out.println("In unserem Gescheft Komponent mit " + komponentId + "ist nicht jetzt gefunden");
            } else
                System.out.println("???? kann nicht delete sein");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }






    /*    try {
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

                System.out.print("In unserem Lager finden Sie komponenten:\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                         " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);

        }} catch (Exception e) {
            System.out.println("ich kann nicht Datei finden " + e.getMessage());;
        }
        addFahradKomponent();
        updateFAhradKomponent();




        /*SQLSelect("INSERT INTO FahradKomponent(\"komponentId\",\"fahradKomponentTyp\",\"fahradTyp\",\"fahradKlase\", \"name\"" +
                "\"marke\", \"material\", \"farbe\", \"preis\", \"gewicht\")VALUES(5, 'RAHMNEN', 'MTB', 'STANDARD', 'Race', 'Cube', 'Aluminium'," +
                "'Weis', '450.6', '1.2'  )");


        /*SQLSelect("INSERT INTO FahradKomponent(komponentId, fahradKomponentTyp, fahradTyp, fahradKlase, name, marke, material, farbe, preis, gewicht)VALUES" +
                "(12, 'ANTRIEB', 'MTB', 'STAANDARD', 'Tourney', 'Shimano', 'Alu-Kunstof'," +
                "'Schwarz-Grau', '270.0', '2420'  )"); */

    /**
     * Metoda odpowiedzialna za połączenie z bazą
     * jeśli bazy nie ma to zostaje utworzona
     *//*
    private static Connection verbinden(String baza) {
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


    private static void SQLSelect(String query){
        try {
            Connection verbindung = verbinden("fahradStart.db");
            Statement statement = verbindung.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void addFahradKomponent(){
        SQLSelect("INSERT INTO FahradKomponent(komponentId, fahradKomponentTyp, fahradTyp, fahradKlase, name, marke, material, farbe, preis, gewicht)VALUES(" +
                "null, STANDARD ,,?,?,?,?;?,?;?");
    }
    private static void updateFAhradKomponent(){
        SQLSelect("UPDATE FahradKomponent SET komponentId = ?, fahrafdKomponentTyp = ?, fahradTyp = ?, fahradKlase = ?, name = ?, farbe = ?, preis = ?, gewicht = ?" + "where komponentId");
    }


 *//*   public static boolean createTables(){
        String createFahradKomponent = "CREATE TABLE IF NOT EXISTS FahradKomponent(komponentId INTEGER PRIMARY KEY AUTOINCREMENT, fahradKomponentTyp varchar(100), fahradTyp varchar(100), fahradKlase varchar(100), name varchar(100),marke varchar(100), material varchar(100),farbe varchar(100), preis int,  gewicht int)";
        String createMeinFahrad = "CREATE TABLE IF NOT EXISTS MainFahrad(faradId INTEGER PRIMARY KEY AUTOINCREMENT, nameFahrad varchar(100), gewicht int,  preis int)";
        String komponentStatus = "CREATE TABLE KomponentStatus (komponentStautusId INTEGER PRIMARY KEY AUTOINCREMENT, fahradId INTEGER REFERENCES meinfahrad, komponentId INTEGER REFERENCES fahradkomponent, PRIMARY KEY (fahradId, komponentId) )";
        try{
            stat.execute(createFahradKomponent);
        }catch (SQLException e){
            System.out.println("Fehler mit start Tabele");
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
    public static void del(int komponentId) throws SQLException {
        try (Connection verbin = komponentConnectHelper.getVerbindung()){
            PreparedStatement dK = verbin.prepareStatement("DELETE FROM FahradKomponent WHERE komponentId = ?");
            dK.setInt(1, komponentId );
            int recordsAffected = dK.executeUpdate();
            // wenn es keinen Datensatz mit der Id mehr gegeben hat
            if (recordsAffected == 0) {
                System.out.println("Komponent mit " + komponentId + "ist gelöscht");
            }
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ich kann nicht löschen");
            alert.setContentText("Wo hin gehen jetzt");
            alert.show();
        }
    }


}