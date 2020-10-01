package model;

import java.util.ArrayList;

public class Product {
    private int product_nummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private ArrayList<OVChipkaart> ov;

    public Product(int product_nummer, String naam, String beschrijving, double prijs){
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public double getPrijs() {
        return prijs;
    }

    public void addOv(OVChipkaart ov) {
        this.ov.add(ov);
    }


    public int getProduct_nummer() {
        return product_nummer;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getNaam() {
        return naam;
    }

    public ArrayList<OVChipkaart> getOv() {
        return ov;
    }
}
