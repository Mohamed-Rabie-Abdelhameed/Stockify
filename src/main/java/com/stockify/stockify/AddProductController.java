package com.stockify.stockify;

import com.stockify.stockify.models.Processes;
import com.stockify.stockify.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController {
    @FXML
    private TextField categoryId;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField supplierId;

    @FXML
    void onCancelClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmClicked(ActionEvent event) {
        if (name.getText().isEmpty() || categoryId.getText().isEmpty() || supplierId.getText().isEmpty() || price.getText().isEmpty() || quantity.getText().isEmpty()) {
            Snackbar.show("Please fill all the fields", false);
        } else {
            try {
                Product newProduct = new Product(0, name.getText(), Integer.parseInt(categoryId.getText()), Integer.parseInt(supplierId.getText()), Double.parseDouble(price.getText()), Integer.parseInt(quantity.getText()));
                Processes.addProduct(newProduct);
                Snackbar.show("Product added successfully", true);
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.show("An error occurred", false);
            }
        }

    }

}
