package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Withdraw implements Initializable {

    private Session session;
    private Inisialisasi data;
    private Alert alert;

    @FXML
    private TextField inputSaldoWithdraw;

    @FXML
    private TextField inputAmountWithdraw;

    @FXML
    void onSubmitClick() {
        int amount = Integer.parseInt(inputAmountWithdraw.getText());

        data.getDataNasabah().forEach((key, value) -> {
            if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                if (value.getSaldo() != 0 && value.getSaldo() >= amount) {
                    if (session.getNomorRekening() != 0 && amount <= value.getLimit()) {
                        try {
                            value.withdraw(amount);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Tarik tunai berhasil");
                            alert.setHeaderText("Silahkan pilih layanan lain atau keluar");
                            alert.showAndWait();

                            Stage window = (Stage) inputSaldoWithdraw.getScene().getWindow();
                            window.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (session.getNomorRekeningAntrian() != 0) {
                        try {
                            value.withdraw(amount);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Tarik tunai berhasil");
                            alert.setHeaderText("Silahkan pilih layanan lain atau keluar");
                            alert.showAndWait();

                            Stage window = (Stage) inputSaldoWithdraw.getScene().getWindow();
                            window.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Tidak bisa tarik tunai");
                        alert.setHeaderText("Melebihi Batas Rekening " + value.getNamaJenis());
                        alert.setContentText("Ulangi Lagi atau keluar");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Tidak bisa tarik tunai");
                    alert.setHeaderText("Saldo Kurang");
                    alert.showAndWait();
                    alert.setContentText("Ulangi Lagi atau keluar");
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        session = new Session();

        data.getDataNasabah().forEach((Integer key, Nasabah value) -> {
            if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                inputSaldoWithdraw.setText("Rp. " + value.getSaldo());
            }
        });
    }
}
