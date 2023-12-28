package com.example.proiect_java;

public class Client {
    private int idClienti;
    private String nume;
    private String prenume;
    private String cnp;
    private String numarTelefon;

    public Client(int idClienti, String nume, String prenume, String cnp, String numarTelefon) {
        this.idClienti = idClienti;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.numarTelefon = numarTelefon;
    }

    public int getIdClienti() {
        return idClienti;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }
}
