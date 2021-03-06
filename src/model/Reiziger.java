package model;

import java.sql.Date;
import java.util.List;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    private List<OVChipkaart> ov;

    public Reiziger(int id, String v, String t, String a, Date g) {
        this.id = id;
        this.voorletters = v;
        this.tussenvoegsel = t;
        this.achternaam = a;
        this.geboortedatum = g;

    }

    public List<OVChipkaart> getOv() {
        return ov;
    }

    public void addOV(OVChipkaart ovc) {
        ov.add(ovc);
    }

    public int getId(){
        return id;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getNaam(){
        return voorletters+" "+tussenvoegsel+" "+achternaam;
    }

    @Override
    public String toString() {
        String tv;
        if(tussenvoegsel == null){
            tv = "";

        }else{
            tv = tussenvoegsel;
        }
        return
                "id = " + id +
                " naam:'" + voorletters +" "+ tv +" "+ achternaam +
                ", geboortedatum: " + geboortedatum;
    }
}
