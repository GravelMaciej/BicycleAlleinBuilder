package webweeg.start.dateiBase;

import webweeg.start.model.FahradKlase;
import webweeg.start.model.FahradKomponent;
import webweeg.start.model.FahradKomponentTyp;
import webweeg.start.model.FahradTyp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class FahradHelperKomponentRepositoryDB {

    private static final String SelFaKompSQL = "select komponentId, fahradKomponentTyp, fahradTyp" +
            "fahradKlase, name, marke, farbe, preis, gewicht " +
            "from FahradKomponent";

    private static final String InsFaKompSQL = "INSERT INTO FahradKomponent(komponentId, fahradKomponentTyp, fahradTyp, fahradKlase, name, marke, material, farbe, preis, gewicht)VALUES" +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UpFaKompSQL = "UPDATE FahradKomponent SET komponentId = ?," +
            "fahrafdKomponentTyp = ?, fahradTyp = ?, fahradKlase = ?, name = ?, farbe = ?, preis = ?, gewicht = ?" +
            "where komponentId" ;

    private static final String DelFaKompSQL = "DELETE FROM FahradKomponent where komponentId = ?";
    private static FahradKomponent fahradKomponent;


    public List<FahradKomponent>selectAll() {
        List<FahradKomponent> fahradKomponents = new ArrayList<>();
        try {
            Connection verbindung = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
            System.out.println("Ich bin mit DB browsswer verbunden " + "FahradKomponent.DB");
            Statement status = verbindung.createStatement();
            ResultSet result = status.executeQuery(SelFaKompSQL);
            System.out.println("Lista" + FahradKomponentsResult(result));
            while (result.next()){
                FahradKomponent fahradKomponent = FahradKomponentsResult(result);
                fahradKomponent.add(fahradKomponents);
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB-Fehler beim Abrufen der FahradKomponent", e);
        }
        return fahradKomponents;
    }


    private FahradKomponent FahradKomponentsResult(ResultSet result) throws SQLException{
        //FahradKomponent fahradKomponent = new FahradKomponent(resultSet.getInt("komponentId"), resultSet.getString("komponent"), resultSet.getDate("birth"), resultSet.getString("adress"), resultSet.getString("email"));
        fahradKomponent.setKomponentId(result.getInt("komponentId"));
        fahradKomponent.setFahradKomponentTyp(result.getString("fahradKomponentTyp"));
        fahradKomponent.setFahradTyp(result.getString("fahradTyp"));
        fahradKomponent.setFahradKlase(result.getString("fahradKlase"));
        fahradKomponent.setName(result.getString("name"));
        fahradKomponent.setMarke(result.getString("marke"));
        fahradKomponent.setMaterial(result.getString("material"));
        fahradKomponent.setFarbe(result.getString("farbe"));
        fahradKomponent.setPreis(result.getDouble("preis"));
        fahradKomponent.setGewicht(result.getDouble("gewicht"));
        return fahradKomponent;
    }

    public FahradKomponent selectByKomponentId(int komponentId) throws SQLException {
        Connection verbindung = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
        System.out.println("Ich will mit disem Methode Komponenten anschauen");
        PreparedStatement preStatus = verbindung.prepareStatement(SelFaKompSQL);
        preStatus.setInt(1,komponentId);
        ResultSet result = preStatus.executeQuery();
        if(result.next()){
            return FahradKomponentsResult(result);
        }else {
            throw new SQLException("Wir haben keine komponent mi komponentId " + komponentId + " ist nicht gefunden - existiert nicht");
        }
    }

    public static void insertFahradKomponent(FahradKomponent fahradKomponent) {
        FahradHelperKomponentRepositoryDB.fahradKomponent = fahradKomponent;
        try{
            Connection verbindung = DriverManager.getConnection("jdbc:sqlite:fahradStart.db");
            System.out.println("Am Dise Fall ich will neue Fahrad Komponent laden und speichern...");
            PreparedStatement insertStatus = verbindung.prepareStatement(InsFaKompSQL);
            insertStatus.executeUpdate();
            System.out.println("Neue Komponent hinzufühgt ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
