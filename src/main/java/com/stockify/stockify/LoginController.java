package com.stockify.stockify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void login(ActionEvent event) {
        System.out.printf("Email: %s", email.getText());

    }

}