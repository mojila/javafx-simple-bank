package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Deposit implements Initializable {
    Inisialisasi data;
    Session session;
    Alert alert;

    @FXML
    private TextField inputSaldoDeposit;

    @FXML
    private TextField inputAmountDeposit;

    @FXML
    void onTabungClick(ActionEvent event) {
        Integer amount = Integer.parseInt(inputAmountDeposit.getText());

        try {
            data.getDataNasabah().forEach((key, value) -> {
                if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                    value.deposit(amount);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Berhasil menabung");
                    alert.setHeaderText("Silahkan pilih layanan lain atau keluar");
                    alert.showAndWait();
                }
            });

            Stage window = (Stage) inputSaldoDeposit.getScene().getWindow();
            window.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        session = new Session();

        data.getDataNasabah().forEach((key, value) -> {
            if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                inputSaldoDeposit.setText("Rp. " + value.getSaldo());
            }
        });
    }
}
