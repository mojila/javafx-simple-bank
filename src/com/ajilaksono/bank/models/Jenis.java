package com.ajilaksono.bank.models;

public class Jenis {
    private String namaJenis;
    private int limit;
    private int proteksi;

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;

        switch (namaJenis) {
            case "Silver":
                this.limit = 5000000;
                this.proteksi = 10000;
                break;
            case "Platinum":
                this.limit = 10000000;
                this.proteksi = 20000;
                break;
            case "Gold":
                this.limit = 30000000;
                this.proteksi = 40000;
                break;
        }
    }

    public int getLimit() {
        return limit;
    }

    public int getProteksi() {
        return proteksi;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
