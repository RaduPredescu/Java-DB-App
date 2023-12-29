package com.example.proiect_java;

public class Magazin {
    private int idMagazin;
    private String numeMagazin;
    private String locatie;
    private String tipProduse;

    public Magazin(int idMagazin, String numeMagazin, String locatie, String tipProduse) {
        this.idMagazin = idMagazin;
        this.numeMagazin = numeMagazin;
        this.locatie = locatie;
        this.tipProduse = tipProduse;
    }

    public int getIdMagazin() {
        return idMagazin;
    }

    public String getNumeMagazin() {
        return numeMagazin;
    }

    public String getLocatie() {
        return locatie;
    }

    public String getTipProduse() {
        return tipProduse;
    }
}
