package webweeg.start.model;

import java.util.List;
import jakarta.xml.bind.annotation.XmlType;
@XmlType(name = "FahradKomponent", propOrder = {"komponentId", "fahradKomponentTyp", "fahradTyp", "fahradKlase",
"name", "marke",  "material", "farbe", "preis", "gewicht"})

public class FahradKomponent {

    private int komponentId;
    private String fahradKomponentTyp;
    private String fahradTyp;
    private String fahradKlase;
    private String name;
    private String marke;
    private String material;
    private String farbe;
    private Double preis;
    private Double gewicht;

    public FahradKomponent(Integer komponentId,
                           FahradKomponentTyp fahradKomponentTyp, FahradKlase name, FahradTyp marke, String material,
                           String farbe, String fahradKlase, String fahradTyp, double preis, double gewicht) {
        this.komponentId = komponentId;
        this.fahradKomponentTyp = String.valueOf(fahradKomponentTyp);
        this.fahradTyp = String.valueOf(fahradTyp);
        this.fahradKlase = String.valueOf(fahradKlase);
        this.name = String.valueOf(name);
        this.marke = String.valueOf(marke);
        this.material = String.valueOf(material);
        this.farbe = String.valueOf(farbe);
        this.preis = Double.parseDouble(String.valueOf(preis));
        this.gewicht = Double.parseDouble(String.valueOf(gewicht));
    }

    public FahradKomponent(int komponentId, String fahradKomponentTyp, String fahradTyp, String fahradKlase, String name, String marke, String material, String farbe, Double preis, Double gewicht) {
        this.komponentId = komponentId;
        this.fahradKomponentTyp = fahradKomponentTyp;
        this.fahradTyp = fahradTyp;
        this.fahradKlase = fahradKlase;
        this.name = name;
        this.marke = marke;
        this.material = material;
        this.farbe = farbe;
        this.preis = preis;
        this.gewicht = gewicht;
    }

    public FahradKomponent() {}

    public int getKomponentId() {
        return komponentId;
    }

    public void setKomponentId(int komponentId) {
        this.komponentId = komponentId;
    }

    public String getFahradKomponentTyp() {
        return fahradKomponentTyp;
    }

    public void setFahradKomponentTyp(String fahradKomponentTyp) {
        this.fahradKomponentTyp = fahradKomponentTyp;
    }

    public String getFahradTyp() {
        return fahradTyp;
    }

    public void setFahradTyp(String fahradTyp) {
        this.fahradTyp = fahradTyp;
    }

    public String getFahradKlase() {
        return fahradKlase;
    }

    public void setFahradKlase(String fahradKlase) {
        this.fahradKlase = fahradKlase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public Double getGewicht() {
        return gewicht;
    }

    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FahradKomponent [komponentId= ").append(getKomponentId());
        builder.append(", fahradKomponentTyp= ").append(getFahradKomponentTyp());
        builder.append(", fahradTyp= ").append((getFahradTyp()));
        builder.append(", fahradKlase= ").append((getFahradKlase()));
        builder.append(", name = ").append(getName());
        builder.append(", marke= ").append(getMarke());
        builder.append(", material= ").append(getMaterial());
        builder.append(", farbe= ").append(getFarbe());
        builder.append(", preis= ").append(getPreis());
        builder.append(", gewicht= ").append(getGewicht());
        return builder.toString();
    }

    public void add(List<FahradKomponent> fahradKomponents) {
    }

}