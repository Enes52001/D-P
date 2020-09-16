package model;

import java.sql.Date;

public class OVChipkaart {
    private int kaartnummer;
    private Date geldig_tot;
    private int klasse;
    private Double saldo;
    private Reiziger reiziger;

    public OVChipkaart(int kaartummer, Date geldig_tot, int klasse, Double saldo){
        this.kaartnummer = kaartummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public int getReiziger_id() {
        return reiziger.getId();
    }


    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
}
