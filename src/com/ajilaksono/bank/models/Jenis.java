package com.ajilaksono.bank.models;

public class Jenis {
    private String namaJenis;
    private int limit;

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;

        switch (namaJenis) {
            case "Silver":
                this.limit = 5000000;
                break;
            case "Platinum":
                this.limit = 10000000;
                break;
            case "Gold":
                this.limit = 30000000;
                break;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
