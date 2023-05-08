package webweeg.start.dateiBase;

import java.sql.ResultSet;

import static webweeg.start.dateiBase.komponentConnectHelper.executeSelect;

public class KomponenteAnschauen {
    static void schowKomponente() {
        try {
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent");
            System.out.println("\n In unserem Lager finden Sie solchie Fahrrad Komponente: \n");
            while (result.next()) {
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


                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }
        } catch (Exception e) {
            System.out.println("ich kann nicht Datei finden " + e.getMessage());
        }
    }
    //Das ist eine Metode wo ich kann Alle Rahmen Komponente anschauen
    public static void schowRahmenKomponente(){
        try{
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE fahradKomponentTyp = 'RAHMEN'");
            System.out.println("\n In unserem Lager finden Sie solchie Rahmen -Fahrrad Komponente: \n");
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

                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }if(result == null){
                System.out.println("Enchuldingung. Moment dann wir haben keine Rahmen. Biite prüfen zuerst Morgen. Danke für verstendnis.");
            }
        }catch (Exception e){
            System.out.println("Wir könen nicht antworten " + e.getMessage());
        }
    }
    //Das ist Metode wo ich kann Alle Antrieb Komponente anschauen
    public static void schowAntriebKomponente(){
        try{
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE fahradKomponentTyp = 'ANTRIEB'");
            System.out.println("\n In unserem Lager finden Sie solchie  Antrieb - Fahrrad Komponente: \n");
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

                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }if(result == null){
                System.out.println("Enchuldingung. Moment dann wir haben keine Rahmen. Biite prüfen zuerst Morgen. Danke für verstendnis.");
            }
        }catch (Exception e){
            System.out.println("Wir könen nicht antworten " + e.getMessage());
        }
    }
    //Das ist Metode wo ich kann Alle komponente von Andare z.b. Sattel, sattelstüzte anschauen
    public static void schowAndareKomponente(){
        try{
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE fahradKomponentTyp = 'ANDARE'");
            System.out.println(" In unserem Lager sind solchie Komponente");
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

                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }if(result == null) {
                System.out.println("Enchuldingung. Moment dann wir haben keine Rahmen. Biite prüfen zuerst Morgen. Danke für verstendnis.");
            }
        }catch (Exception e){
            System.out.println("Wir könen nicht antworten " + e.getMessage());
        }
    }
    //Das ist eine Metode wo ich kann Laufrad Komponente anschauen
    public static void schowLaufradKomponente(){
        System.out.println("\n In unserem Lager finden Sie solchie Laufrad - Fahrrad Komponente: \n");
        try{
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE fahradKomponentTyp = 'LAUFRAD'");
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

                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }if(result == null){
                System.out.println("Enchuldingung. Moment dann wir haben keine Rahmen. Biite prüfen zuerst Morgen. Danke für verstendnis.");
            }
        }catch (Exception e){
            System.out.println("Wir könen nicht antworten " + e.getMessage());
        }
    }

    //Das ist eine Metode wo ich kanna Bremsen Komponente anschauen
    public static void schowBremsenKomponente(){
        try{
            System.out.println("\n In unserem Lager finden Sie solchie Bremsen - Fahrrad Komponente: \n");
            ResultSet result = executeSelect("SELECT * FROM FahradKomponent WHERE fahradKomponentTyp = 'BREMSEN'");
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

                System.out.println("\n" + komponentId + " " + fahradTyp + " " + fahradKlase + " " + name + " " +
                        " " + marke + " " + material + " " + farbe + " " + preis + " " + gewicht);
            }if(result == null){
                System.out.println("Enchuldingung. Moment dann wir haben keine Rahmen. Biite prüfen zuerst Morgen. Danke für verstendnis.");
            }
        }catch (Exception e){
            System.out.println("Wir könen nicht antworten " + e.getMessage());
        }
    }

}
