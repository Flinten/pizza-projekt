package Business;

/**
 * Created by Ejer on 30-05-2017.
 */
public class Kunde {

    private String fornavn;
    private String efternavn;
    private String adresse;
    private String by;
    private int postnummer;
    private int telefonnummer;

    public Kunde(String fornavn, String efternavn, String adresse, String by, int postnummer, int telefonnummer) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.by = by;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
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

    @Override
    public String toString() {
        return "Kunde{" +
                "fornavn='" + fornavn + '\'' +
                ", efternavn='" + efternavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", by='" + by + '\'' +
                ", postnummer=" + postnummer +
                ", telefonnummer=" + telefonnummer +
                '}';
    }
}
