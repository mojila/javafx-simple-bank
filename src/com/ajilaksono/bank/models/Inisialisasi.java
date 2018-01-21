package com.ajilaksono.bank.models;

import java.util.HashMap;
import java.util.Map;

public class Inisialisasi {
    private static Map<Integer, Nasabah> dataNasabah;
    private static Map<Integer, Antrian> dataAntrian;
    private Nasabah[] nasabah = new Nasabah[100];
    private Antrian[] antrian = new Antrian[100];
    private static int jumlahNasabah = 2;
    private static int jumlahAntrian = 2;

    public void init() {
        dataNasabah = new HashMap<>();
        dataAntrian = new HashMap<>();

        // initNasabah
        nasabah[0] = new Nasabah(201, 0, "Moch. Aji Laksono", "010100", "Silver");
        nasabah[1] = new Nasabah(202, 0, "Nuril Ratu Qurani", "010100", "Gold");

        // init Antrian
        antrian[0] = new Antrian(1, 201, "Moch. Aji Laksono");
        antrian[1] = new Antrian(2, 202, "Nuril Ratu Qurani");

        for (int i = 0; i < 2; i++) {
            dataNasabah.put(i+1, nasabah[i]);
            dataAntrian.put(i+1, antrian[i]);
        }
    }

    public Map<Integer, Antrian> getDataAntrian() {
        return dataAntrian;
    }

    public Map<Integer, Nasabah> getDataNasabah() {
        return dataNasabah;
    }

    public Boolean nasabahBaru(int nomorRekening, String nama, String pin, String namaJenis) {
        try {
            Nasabah nasabahBaru = new Nasabah(nomorRekening, 0, nama, pin, namaJenis);
            jumlahNasabah++;
            dataNasabah.put(jumlahNasabah, nasabahBaru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean antrianBaru(int nomorRekening, String nama) {
        try {
            Antrian antrianBaru = new Antrian(jumlahNasabah+1, nomorRekening, nama);
            jumlahAntrian++;
            dataAntrian.put(jumlahAntrian, antrianBaru);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getJumlahNasabah() {
        return jumlahNasabah;
    }

    public int getJumlahAntrian() {
        return jumlahAntrian;
    }
}
