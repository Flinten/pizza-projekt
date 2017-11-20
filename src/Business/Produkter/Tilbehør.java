package Business.Produkter;

//lavet af kadir

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Ejer on 24-05-2017.
 */
public interface Tilbehør {
    String getNavn();
    String getProduktType();
    double getPris();
    void setNavn(String navn);
    void setProduktType(String produktType);
    void setPris(double pris);
    SimpleDoubleProperty getPrisPropertie();

    String getKviteringOpsætning();
}
