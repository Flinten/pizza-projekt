package Business.Produkter;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Ejer on 24-05-2017.
 */
public class FaxeLille implements Tilbehør {
    String navn = "Faxe kondi lille";
    String produktType = "Sodavand";
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
    @Override
    public String toString(){
        return "Faxe kondi";
    }
    public SimpleDoubleProperty getPrisPropertie() {

        SimpleDoubleProperty s = new SimpleDoubleProperty();
        setPris(pris);

        return s;
    }

    public String getKviteringOpsætning(){

        return navn+"\t\t"+pris;
    }
}
