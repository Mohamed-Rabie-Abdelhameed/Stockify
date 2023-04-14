package com.stockify.stockify;

import com.stockify.stockify.models.Order;
import com.stockify.stockify.models.Processes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddOrderController {
    @FXML
    private TextField deliveryDate;

    @FXML
    private TextField orderDate;

    @FXML
    private TextField productId;

    @FXML
    private TextField quantity;

    @FXML
    private TextField status;

    @FXML
    void onCancelClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmClicked(ActionEvent event) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date order_Date = format.parse(orderDate.getText());
        Date delivery_Date = format.parse(deliveryDate.getText());
        if(productId.getText().isEmpty() || quantity.getText().isEmpty() || status.getText().isEmpty() || orderDate.getText().isEmpty() || deliveryDate.getText().isEmpty()) {
            Snackbar.show("Please fill all the fields", false);
        } else {
            try {
                Order newOrder = new Order(0, order_Date,delivery_Date,status.getText(),Integer.parseInt(productId.getText()), Integer.parseInt(quantity.getText()));
                Processes.addOrder(newOrder);
                Snackbar.show("Order added successfully", true);
                resetFields();
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.show("An error occurred", false);
            }
        }
    }

    void resetFields() {
        productId.setText("");
        quantity.setText("");
        status.setText("");
        orderDate.setText("");
        deliveryDate.setText("");
    }
}
