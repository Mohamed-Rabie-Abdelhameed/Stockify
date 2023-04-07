package com.stockify.stockify;

import com.stockify.stockify.models.Processes;
import com.stockify.stockify.models.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSupplierController {

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    void onCancelClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmClicked(ActionEvent event) {
        if (name.getText().isEmpty() || address.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()) {
            Snackbar.show("Please fill all the fields", false);
        } else {
            try {
                Supplier newSupplier = new Supplier(0, name.getText(), address.getText(), phone.getText(), email.getText());
                Processes.addSupplier(newSupplier);
                Snackbar.show("Supplier added successfully", true);
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.show("An error occurred", false);
            }
        }

    }

}