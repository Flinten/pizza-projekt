package Business;

import Business.Produkter.Tilbehør;
import Database.MySqlConnection;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Ejer on 23-05-2017.
 */
public class SeLagerStatus {
    private String navn;
    private int nuværendeBeholdning;
    private int optimalBeholdning;


    public SeLagerStatus(String navn, int optimalBeholdning, int nuværendeBeholdning) {
        this.navn = navn;
        this.optimalBeholdning = optimalBeholdning;
        this.nuværendeBeholdning = nuværendeBeholdning;
    }

    public SeLagerStatus() {
    }

    public void ændreLagerStatus(ObservableList<Tilbehør> list){

        String query = "UPDATE table_Lager SET ";


        MySqlConnection mySqlConnection = new MySqlConnection();

        for(int i = 0; i < list.size(); i++){
            if (list.get(i).getNavn().equals("Cola Zero lille")){
                Statement st = null;
                try {
                    st = mySqlConnection.connect().createStatement();
                    ResultSet rs = st.executeQuery(query);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public int getOptimalBeholdning() {
        return optimalBeholdning;
    }

    public void setOptimalBeholdning(int optimalBeholdning) {
        this.optimalBeholdning = optimalBeholdning;
    }


    public int getNuværendeBeholdning() {
        return nuværendeBeholdning;
    }

    public void setNuværendeBeholdning(int nuværendeBehodning) {
        this.nuværendeBeholdning = nuværendeBehodning;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
