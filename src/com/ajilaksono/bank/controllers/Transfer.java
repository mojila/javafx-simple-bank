package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Transfer implements Initializable {
    private Session session;
    private Inisialisasi data;
    private Alert alert;

    @FXML
    private TextField inputAmountTransfer;
    @FXML
    private ChoiceBox<Integer> inputRekeningTujuan;
    @FXML
    private TextField inputNamaTujuan;

    @FXML
    private TextField inputSaldoTransfer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        session = new Session();

        if (session.getNomorRekeningAntrian() != 0) {
            data.getDataNasabah().forEach((key, value) -> {
                if (value.getNomorRekening() != session.getNomorRekeningAntrian()) {
                    inputRekeningTujuan.getItems().add(value.getNomorRekening());
                }
            });
        } else if (session.getNomorRekening() != 0) {
            data.getDataNasabah().forEach((key, value) -> {
                if (value.getNomorRekening() != session.getNomorRekening()) {
                    inputRekeningTujuan.getItems().add(value.getNomorRekening());
                }
            });
        }

        inputRekeningTujuan.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> data.getDataNasabah().forEach((key, value) -> {
            if (value.getNomorRekening() == newValue) {
                inputNamaTujuan.setText(value.getNama());
            }
        }));

        data.getDataNasabah().forEach((Integer key, Nasabah value) -> {
            if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                inputSaldoTransfer.setText("Rp. " + value.getSaldo());
            }
        });
    }

    private void transfer(int amount) {
        data.getDataNasabah().forEach((key, value) -> {
            if (value.getNomorRekening() == inputRekeningTujuan.getValue()) {
                value.deposit(amount);
            }
        });
    }

    @FXML
    void onKirimClick() {
        int amount = Integer.parseInt(inputAmountTransfer.getText());

        data.getDataNasabah().forEach((key, value) -> {
            if (value.getNomorRekening() == session.getNomorRekening() || value.getNomorRekening() == session.getNomorRekeningAntrian()) {
                if (value.getSaldo() != 0 && value.getSaldo() >= amount) {
                    if (session.getNomorRekening() != 0 && amount <= value.getLimit()) {
                        try {
                            value.withdraw(amount);
                            transfer(amount);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Transfer berhasil");
                            alert.setHeaderText("Silahkan pilih layanan lain atau keluar");
                            alert.showAndWait();

                            Stage window = (Stage) inputSaldoTransfer.getScene().getWindow();
                            window.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (session.getNomorRekeningAntrian() != 0) {
                        try {
                            value.withdraw(amount);
                            transfer(amount);
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Transfer berhasil");
                            alert.setHeaderText("Silahkan pilih layanan lain atau keluar");
                            alert.showAndWait();

                            Stage window = (Stage) inputSaldoTransfer.getScene().getWindow();
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
                    alert.setTitle("Tidak bisa transfer");
                    alert.setHeaderText("Saldo Kurang");
                    alert.showAndWait();
                    alert.setContentText("Ulangi Lagi atau keluar");
                }
            }
        });
    }
}
