package com.ajilaksono.bank.models;

public class Session {
    public static int nomorRekening;
    public static int nomorRekeningAntrian;

    public int getNomorRekeningAntrian() {
        return nomorRekeningAntrian;
    }

    public void setNomorRekeningAntrian(int nomorRekeningAntrian) {
        Session.nomorRekeningAntrian = nomorRekeningAntrian;
    }

    public int getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(int nomorRekening) {
        Session.nomorRekening = nomorRekening;
    }
}
