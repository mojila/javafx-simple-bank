package com.ajilaksono.bank.models;

public class Antrian {
    private int nomorUrut, nomorRekening;
    private String nama;

    public Antrian(int nomorUrut, int nomorRekening, String nama) {
        this.nomorUrut = nomorUrut;
        this.nomorRekening = nomorRekening;
        this.nama = nama;
    }

    public int getNomorUrut() {
        return nomorUrut;
    }

    public int getNomorRekening() {
        return nomorRekening;
    }

    public String getNama() {
        return nama;
    }

}
