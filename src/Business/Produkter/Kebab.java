package Business.Produkter;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Ejer on 24-05-2017.
 */
public class Kebab implements Tilbehør {
    String navn = "Kebab";
    String produktType = "Tilbehør";
    double pris = 12;

    @Override
    public String getNavn() {
        return navn;
    }

    @Override
    public String getProduktType() {
        return produktType;
    }

    @Override
    public double getPris() {
        return pris;
    }

    @Override
    public void setNavn(String navn) {

    }

    @Override
    public void setProduktType(String produktType) {

    }

    @Override
    public void setPris(double pris) {

    }
    public SimpleDoubleProperty getPrisPropertie() {

        SimpleDoubleProperty s = new SimpleDoubleProperty();
        setPris(pris);

        return s;
    }
    public String toString(){
        return navn+" "+produktType+" "+pris;
    }

    public String getKviteringOpsætning(){

        return navn+"\t\t\t\t\t"+pris;
    }
}
