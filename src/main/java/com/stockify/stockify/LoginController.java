package com.stockify.stockify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class LoginController {
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    @FXML
    void login(ActionEvent event) {
        String email = this.email.getText();
        String password = this.password.getText();
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields");
        } else if (email.equals("admin") && password.equals("admin")) {
            openDashboard(event);
        } else {
            errorLabel.setText("Invalid credentials");
        }
    }

    private void openDashboard(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard-view.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Stockify");
            stage.setScene(new Scene(root, 1400, 780));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(Login.class.getResourceAsStream("images/logo.png"))));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error connecting to database");
        }
    }
}