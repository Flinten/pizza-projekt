package Business;

import Database.MySqlConnection;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ejer on 22-05-2017.
 */
public class Ansat {
    private int id;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private String by;
    private int postnummer;
    private int telefonnummer;
    private int loginId;
    private String kodeord;

    public Ansat() {
    }

    public Ansat(int id, String fornavn, String efternavn, String adresse, String by,
                 int postnummer, int telefonnummer, int loginId, String kodeord) {
        this.id = id;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.by = by;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
        this.loginId = loginId;
        this.kodeord = kodeord;
    }

    public Ansat(String fornavn, String efternavn, String adresse, String by,
                 int postnummer, int telefonnummer, int loginId, String kodeord) {

        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.by = by;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
        this.loginId = loginId;
        this.kodeord = kodeord;
        //opretter ansat i databasen med det samme se metode længere nede
        indsætDataTilDatabasen(fornavn,efternavn,adresse,by,postnummer,telefonnummer,loginId,kodeord);
    }

    // lave en statement der insætter ny ansat i databasen
    private void indsætDataTilDatabasen(String fornavn, String efternavn, String adresse, String by,
                                        int postnummer, int telefonnummer, int loginId, String kodeord){
        MySqlConnection mySqlConnection = new MySqlConnection();
        try {
            Statement st = mySqlConnection.connect().createStatement();
            st.executeUpdate("INSERT INTO table_Ansat(ansat_login_Id, ansat_Login_Kodeord, ansat_Fornavn, " +
                    "ansat_Efternavn, ansat_Adresse, ansat_By, ansat_Postnummer, ansat_Telefon)"+
            "VALUES('"+loginId+"', '"+kodeord+"', '"+fornavn+"', '"+efternavn+"', '"+adresse+"', '"+by+"', '"+
                    postnummer+"', '"+telefonnummer+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void sletAnsat(int index){
        String query = "DELETE FROM table_Ansat WHERE ansat_Id ="+index;
        MySqlConnection mySqlConnection = new MySqlConnection();
        try {
            Statement st = mySqlConnection.connect().createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
