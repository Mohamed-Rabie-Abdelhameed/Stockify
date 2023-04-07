package com.stockify.stockify;

import com.stockify.stockify.models.Category;
import com.stockify.stockify.models.Processes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {

    @FXML
    private TextField name;

    @FXML
    void onCancelClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void onConfirmClicked(ActionEvent event) {
        if(name.getText().isEmpty()) {
            Snackbar.show("Please fill all the fields", false);
        } else {
            try {
                Category newCategory = new Category(0, name.getText());
                Processes.addCategory(newCategory);
                Snackbar.show("Category added successfully", true);
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.show("An error occurred", false);
            }
        }
    }

}
