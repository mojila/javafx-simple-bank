package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Antrian;
import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Teller implements Initializable {
    private Session session;
    private Inisialisasi data;
    private Alert alert;

    @FXML
    private ChoiceBox<Integer> inputNomorRekeningAntrian;

    @FXML
    private TextField inputNamaAntrian;

    @FXML
    private ComboBox<Integer> inputAntrian;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        session = new Session();
        data = new Inisialisasi();

        data.getDataNasabah().forEach((Integer key, Nasabah value) -> {
            inputNomorRekeningAntrian.getItems().add(value.getNomorRekening());
        });

        data.getDataAntrian().forEach((key, value) -> {
            inputAntrian.getItems().add(value.getNomorRekening());
        });

        inputAntrian.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            data.getDataAntrian().forEach((Integer key, Antrian value) -> {
                if (value.getNomorRekening() == newValue) session.setNomorRekeningAntrian(newValue);
            });
        });

        inputNomorRekeningAntrian.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            data.getDataNasabah().forEach((Integer key, Nasabah value) -> {
                if (value.getNomorRekening() == newValue) inputNamaAntrian.setText(value.getNama());
            });
        });
    }

    @FXML
    void onMasukkanClick() {
        data.antrianBaru(inputNomorRekeningAntrian.getValue(), inputNamaAntrian.getText());
        inputAntrian.getItems().add(inputNomorRekeningAntrian.getValue());
    }

    @FXML
    void onTarikTunaiClick() {
        if (session.getNomorRekeningAntrian() == 0 || inputAntrian.getItems().toArray().length == 0) {
            session.setNomorRekeningAntrian(0);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tidak bisa dilanjutkkan");
            alert.setHeaderText("Data tidak ada atau belum memilih");
            alert.setContentText("Ulangi Lagi atau keluar");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader withdraw = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/withdraw.fxml"));
                Parent rootWithdraw = withdraw.load();

                Stage stageWithdraw = new Stage();
                stageWithdraw.setScene(new Scene(rootWithdraw));
                stageWithdraw.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onTransferClick() {
        if (session.getNomorRekeningAntrian() == 0 || inputAntrian.getItems().toArray().length == 0) {
            session.setNomorRekeningAntrian(0);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tidak bisa dilanjutkkan");
            alert.setHeaderText("Data tidak ada atau belum memilih");
            alert.setContentText("Ulangi Lagi atau keluar");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader transfer = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/transfer.fxml"));
                Parent rootTransfer = transfer.load();

                Stage stageTransfer = new Stage();
                stageTransfer.setScene(new Scene(rootTransfer));
                stageTransfer.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onTabungClick() {
        if (session.getNomorRekeningAntrian() == 0 || inputAntrian.getItems().toArray().length == 0) {
            session.setNomorRekeningAntrian(0);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tidak bisa dilanjutkkan");
            alert.setHeaderText("Data tidak ada atau belum memilih");
            alert.setContentText("Ulangi Lagi atau keluar");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader deposit = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/deposit.fxml"));
                Parent rootDeposit = deposit.load();

                Stage stageDeposit = new Stage();
                stageDeposit.setScene(new Scene(rootDeposit));
                stageDeposit.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onLanjutClick() {
        if (session.getNomorRekeningAntrian() == 0 || inputAntrian.getItems().toArray().length == 0) {
            session.setNomorRekeningAntrian(0);
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tidak bisa dilanjutkkan");
            alert.setHeaderText("Data tidak ada atau belum memilih");
            alert.setContentText("Ulangi Lagi atau keluar");
            alert.showAndWait();
        } else {
            try {
                inputAntrian.getItems().remove(inputAntrian.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onKeluarClick() {
        Stage window = (Stage) inputNamaAntrian.getScene().getWindow();

        try {
            session.setNomorRekeningAntrian(0);

            window.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
