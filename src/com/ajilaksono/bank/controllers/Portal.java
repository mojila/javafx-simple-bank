package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Antrian;
import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Portal implements Initializable {
    private Inisialisasi data;
    private Session session;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        session = new Session();

        data.init();

        try {
            if (data.nasabahBaru(203, "Adaw", "010102", "Platinum")) {
                getDataNasabah();
                if(data.antrianBaru(203, "Adaw")) {
                    getDataAntrian();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataNasabah() {
        System.out.println(data.getJumlahNasabah() + " Data :");

        for (Map.Entry<Integer, Nasabah> entry:data.getDataNasabah().entrySet()) {
            int key = entry.getKey();
            Nasabah nasabah = entry.getValue();
            System.out.println(key + ". " + nasabah.getNama() + " Limit: " + nasabah.getLimit());
        }
    }
    private void getDataAntrian() {
        System.out.println(data.getJumlahAntrian() + " Data :");

        for (Map.Entry<Integer, Antrian> entry:data.getDataAntrian().entrySet()) {
            int key = entry.getKey();
            Antrian antrian = entry.getValue();
            System.out.println(key + ". No. Antrian: " + antrian.getNomorUrut() + " Nama: " + antrian.getNama());
        }
    }

    // FXML Event

    @FXML
    void onATMClick() {
        if (session.getNomorRekening() != 0) {
            try {
                FXMLLoader auth = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/machine.fxml"));
                Parent rootMachine = auth.load();

                Stage stageMachine = new Stage();
                stageMachine.setScene(new Scene(rootMachine));
                stageMachine.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader auth = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/auth.fxml"));
                Parent rootAuth = auth.load();

                Stage stageAuth = new Stage();
                stageAuth.setScene(new Scene(rootAuth));
                stageAuth.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onDaftarNasabahClick() {
        try {
            FXMLLoader nasabah = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/nasabah.fxml"));
            Parent rootNasabah = nasabah.load();

            Stage stageNasabah = new Stage();
            stageNasabah.setScene(new Scene(rootNasabah));
            stageNasabah.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onTellerClick() {
        try {
            FXMLLoader teller = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/teller.fxml"));
            Parent rootTeller = teller.load();

            Stage stageTeller = new Stage();
            stageTeller.setScene(new Scene(rootTeller));
            stageTeller.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

