package com.ajilaksono.bank.models;

public class Antrian extends Nasabah {
    private int nomorUrut;

    public Antrian(int nomorUrut, int nomorRekening, String nama) {
        super(nomorRekening, nama);
        this.nomorUrut = nomorUrut;
    }

    public int getNomorUrut() {
        return nomorUrut;
    }

    @Override
    public int getNomorRekening() {
        return super.getNomorRekening();
    }

    @Override
    public String getNama() {
        return super.getNama();
    }
}
