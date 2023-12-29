package com.example.proiect_java;

public class Tranzactie {
    private int idTranzactie;
    private int idClient;
    private int idMagazin;
    private double sumaTranzactie;
    private String dataTranzactie;

    public Tranzactie(int idTranzactie, int idClient, int idMagazin, double sumaTranzactie, String dataTranzactie) {
        this.idTranzactie = idTranzactie;
        this.idClient = idClient;
        this.idMagazin = idMagazin;
        this.sumaTranzactie = sumaTranzactie;
        this.dataTranzactie = dataTranzactie;
    }

    public int getIdTranzactie() {
        return idTranzactie;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdMagazin() {
        return idMagazin;
    }

    public double getSumaTranzactie() {
        return sumaTranzactie;
    }

    public String getDataTranzactie() {
        return dataTranzactie;
    }

}

