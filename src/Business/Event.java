package Business;

/**
 * Created by Ejer on 30-05-2017.
 */
public class Event {

    private int id;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private String by;
    private int postnummer;
    private int telefonnummer;
    private String dato;
    private String kommentar;

    public Event(int id, String fornavn, String efternavn, String adresse, String by, int postnummer, int telefonnummer, String dato, String kommentar) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", fornavn='" + fornavn + '\'' +
                ", efternavn='" + efternavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", by='" + by + '\'' +
                ", postnummer=" + postnummer +
                ", telefonnummer=" + telefonnummer +
                ", dato='" + dato + '\'' +
                ", kommentar='" + kommentar + '\'' +
                '}';
    }
}
