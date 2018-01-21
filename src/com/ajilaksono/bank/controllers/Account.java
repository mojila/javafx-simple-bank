package com.ajilaksono.bank.controllers;

import com.ajilaksono.bank.models.Inisialisasi;
import com.ajilaksono.bank.models.Nasabah;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Account implements Initializable {

    @FXML
    private TextField inputNomorRekeningBaru;
    @FXML
    private TextField inputNamaBaru;
    @FXML
    private PasswordField inputPinBaru;
    @FXML
    private ChoiceBox<String> inputJenisBaru;

    private Map<Integer, Nasabah> nasabahMap;
    private Inisialisasi data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = new Inisialisasi();
        nasabahMap = data.getDataNasabah();
        getDataNasabah();
        inputJenisBaru.getItems().addAll("Silver", "Platinum", "Gold");
        inputJenisBaru.setValue("Silver");
    }

    private void getDataNasabah() {
        System.out.println(data.getJumlahNasabah() + " Data :");

        for (Map.Entry<Integer, Nasabah> entry:this.nasabahMap.entrySet()) {
            int key = entry.getKey();
            Nasabah nasabah = entry.getValue();
            System.out.println(key + ". " + nasabah.getNama() + " Limit: " + nasabah.getLimit());
        }
    }

    @FXML
    void buatNasabah() {
        try {
            Integer nomorRekening = Integer.parseInt(inputNomorRekeningBaru.getText());
            String jenis = inputJenisBaru.getValue();

            if (data.nasabahBaru(nomorRekening, inputNamaBaru.getText(), inputPinBaru.getText(), jenis)) {
                getDataNasabah();
            }

            Stage window = (Stage) inputPinBaru.getScene().getWindow();
            window.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
