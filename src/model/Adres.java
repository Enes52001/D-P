package model;

public class Adres {
    private int adres_id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private static Reiziger reiziger;

    public Adres(int adres_id, String postcode, String huisnr, String straat, String woonplaats){
        this.adres_id = adres_id;
        this.postcode = postcode;
        this.huisnummer = huisnr;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public static void setReiziger(Reiziger reiziger) {
        Adres.reiziger = reiziger;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public int getAdres_id() {
        return adres_id;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }


    @Override
    public String toString() {
        return "Adres{" +
                "adres_id=" + adres_id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", reiziger_id=" + reiziger.getId() +
                '}';
    }
}
