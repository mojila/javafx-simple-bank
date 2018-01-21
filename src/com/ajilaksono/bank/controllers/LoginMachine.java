package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import com.ajilaksono.bank.models.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginMachine implements Initializable {

    private Inisialisasi data;
    private Session session;
    private Alert alert;

    @FXML
    private PasswordField inputPinLogin;

    @FXML
    private ChoiceBox<Integer> inputNomorRekeningLogin;

    @FXML
    private TextField inputNamaLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        session = new Session();
        alert = new Alert(Alert.AlertType.WARNING);

        for (Map.Entry<Integer, Nasabah> entry:data.getDataNasabah().entrySet()) {
            Nasabah nasabah = entry.getValue();
            inputNomorRekeningLogin.getItems().add(nasabah.getNomorRekening());
        }

        inputNomorRekeningLogin.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            for (Map.Entry<Integer, Nasabah> entry:data.getDataNasabah().entrySet()) {
                Nasabah nasabah = entry.getValue();

                if (nasabah.getNomorRekening() == newValue) {
                    inputNamaLogin.setText(nasabah.getNama());
                    break;
                }
            }
        });
    }

    @FXML
    void onMasukClick() {
        Stage window = (Stage) inputNamaLogin.getScene().getWindow();

        if (loggedIn(inputNomorRekeningLogin.getValue(), inputPinLogin.getText())) {
            session.setNomorRekening(inputNomorRekeningLogin.getValue());

            try {
                FXMLLoader auth = new FXMLLoader(getClass().getResource("/com/ajilaksono/bank/views/machine.fxml"));
                Parent rootMachine = auth.load();

                Stage stageMachine = new Stage();
                stageMachine.setScene(new Scene(rootMachine));
                stageMachine.show();
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert.setTitle("Tidak dapat Masuk");
            alert.setHeaderText("PIN Salah");
            alert.showAndWait();
            alert.setContentText("Ulangi Lagi");
        }
    }

    private Boolean loggedIn(Integer nomorRekening, String pin) {
        for (Map.Entry<Integer, Nasabah> entry:data.getDataNasabah().entrySet()) {
            Nasabah nasabah = entry.getValue();

            if(nasabah.getNomorRekening() == nomorRekening) if (nasabah.getPin().equals(pin)) {
                return true;
            }
        }
        return false;
    }
}