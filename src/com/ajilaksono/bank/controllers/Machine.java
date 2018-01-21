package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Machine implements Initializable {

    private Session session;

    @FXML
    private Label namaNasabahLabel;

    @FXML
    private Button keluarButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Inisialisasi data = new Inisialisasi();
        session = new Session();

        data.getDataNasabah().forEach((Integer key, Nasabah value) -> {
            if (value.getNomorRekening() == session.getNomorRekening()) {
                namaNasabahLabel.setText(value.getNama());
            }
        });
    }

    @FXML
    void onTransferClick() {
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

    @FXML
    void onKeluarClick() {
        Stage window = (Stage) keluarButton.getScene().getWindow();

        try {
            session.setNomorRekening(0);

            window.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onTarikTunaiClick() {
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
