package com.ajilaksono.bank.models;

public class Nasabah extends Jenis {
    private int nomorRekening, saldo;
    private String nama, pin;

    Nasabah(int nomorRekening, int saldo, String nama, String pin, String namaJenis) {
        this.nomorRekening = nomorRekening;
        this.saldo = saldo;
        this.nama = nama;
        this.pin = pin;
        super.setNamaJenis(namaJenis);
    }

    public int getNomorRekening() {
        return nomorRekening;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void deposit(int saldo) {
        this.saldo += saldo;
    }

    public void withdraw(int amount) {
        this.saldo -= amount;
    }

    public String getNama() {
        return nama;
    }

    public String getPin() {
        return pin;
    }

    public int getLimit() {
        return super.getLimit();
    }

    public String getNamaJenis() {
        return super.getNamaJenis();
    }

    public void setNamaJenis(String namaJenis) {
        super.setNamaJenis(namaJenis);
    }
}
