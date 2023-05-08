package webweeg.start.dateiBase;

import webweeg.start.model.FahradKomponent;

import java.sql.*;

public class Rower {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:fahradStart.db";

    private static Connection connect;
    private Statement status;

    public Rower() {
        try {
            Class.forName(Rower.DRIVER);
        }catch (ClassNotFoundException e){
            System.err.println("Driver JDBC ist nicht gefunden! ");
            e.printStackTrace();
        }
        try {
            connect = DriverManager.getConnection(DB_URL);
            status = connect.createStatement();
            System.out.println("Du bist mit DB verbinden");
        }catch (SQLException e){
            System.err.println("Das ist Problem mit Verbindung mit DB. ");
            e.printStackTrace();
        }
    }
    public static void closeConnection(){
        try{
            connect.close();
            System.out.println("Verbindung ist geschlossen");
        }catch (SQLException e){
            System.err.println("Es ist Problem mit Verbindung zuschlissen! ");
        }
    }

    public static void insertFahradkomponent(FahradKomponent fahradKomponent) {
        try {PreparedStatement instStmt = connect.prepareStatement("INSERT INTO FahradKomponent VALUES(NULL, ?,?,?,?,?,?,?,?,?);");
            instStmt.setString(3, fahradKomponent.getFahradTyp());
            instStmt.setString(4, fahradKomponent.getFahradKlase());
            instStmt.setString(5, fahradKomponent.getName());
            instStmt.setString(6, fahradKomponent.getMarke());
            instStmt.setString(7, fahradKomponent.getMaterial());
            instStmt.setString(8, fahradKomponent.getFarbe());
            instStmt.setDouble(9, fahradKomponent.getPreis());
            instStmt.setDouble(10, fahradKomponent.getGewicht());
        } catch (SQLException e) {
            System.err.println("Ich kann nicht neue Fahrrad KOmponent dazu geben");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

       insertFahradkomponent("1", "ANTRIEB", "RENNRAD", "PREMIUM", "Ultegra 6700", "Shimano", "Alu-Carbon", "Grau", 650.0, 1765.24);

    }

    private static void insertFahradkomponent(String s, String antrieb, String rennrad, String premium, String s1, String shimano, String s2, String grau, double v, double v1) {
    }

    public static Double readPreis(ResultSet rp, String preis) {
        try {
            Double val = rp.getDouble(preis);
            if (! rp.wasNull()) {
                return val;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public static Double readgewicht(ResultSet rg, String gewicht) {
        try {
            Double val = rg.getDouble(gewicht);
            if (! rg.wasNull()) {
                return val;
            }
        } catch (SQLException e) {
        }
        return null;
    }




}
