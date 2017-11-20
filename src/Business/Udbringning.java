package Business;

import Database.MySqlConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ejer on 23-05-2017.
 */
public class Udbringning {
    private int id;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private String by;
    private int postnummer;
    private int telefonnummer;
    private Date dato;
    private String kommentar;

    public Udbringning() {
    }

    public Udbringning(String fornavn, String efternavn, String adresse, String by, int postnummer, int telefonnummer, Date dato, String kommentar) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.by = by;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
        this.dato = dato;
        this.kommentar = kommentar;
        indsætDataTilDatabasen(fornavn,efternavn,adresse,by,postnummer,telefonnummer,dato,kommentar);
    }

    public Udbringning(int id, String fornavn, String efternavn, String adresse, String by, int postnummer, int telefonnummer, Date dato, String kommentar) {
        this.id = id;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.by = by;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
        this.dato = dato;
        this.kommentar = kommentar;
    }

    private void indsætDataTilDatabasen(String fornavn, String efternavn, String adresse, String by, int postnummer, int telefonnummer, Date dato, String kommentar){
        MySqlConnection mySqlConnection = new MySqlConnection();
        try {
            Statement st = mySqlConnection.connect().createStatement();
            st.executeUpdate("INSERT INTO table_Event(event_Fornavn,event_Efternavn,event_Adresse,event_By,event_Postnummer,event_Telefonnummer,event_Dato, event_Kommentar)"+
                    "VALUES('"+fornavn+"', '"+efternavn+"', '"+adresse+"', '"+by+"', '"+postnummer+"', '"+telefonnummer+"', '"+dato+"', '"+kommentar+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void sletEvent(){
        String query = "DELETE FROM table_Event WHERE event_Id ="+id;
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

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}
