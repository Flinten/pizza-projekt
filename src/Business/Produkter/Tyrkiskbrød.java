package Business.Produkter;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Ejer on 28-05-2017.
 */
public class Tyrkiskbrød implements Tilbehør {

    String navn = "Tyrkisk brød";
    String produktType = "Mad";
    double pris = 30;

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

    @Override
    public SimpleDoubleProperty getPrisPropertie() {
        return null;
    }

    public String toString(){
        return navn;
    }

    public String getKviteringOpsætning(){

        return navn+"\t\t\t"+pris;
    }
}
