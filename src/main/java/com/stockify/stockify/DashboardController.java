package com.stockify.stockify;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Pane categoriesPane;

    @FXML
    private VBox nav;
    @FXML
    private AnchorPane dash;

    @FXML
    private Pane inventoryPane;

    @FXML
    private Pane ordersPane;

    @FXML
    private Pane suppliersPane;


    Button lastClickedButton;

    @FXML
    void categoriesClicked(ActionEvent event) {
        showPane(categoriesPane);
        setCurrentButton((Button) event.getSource());
        resetLastClickedButton((Button) event.getSource());
        lastClickedButton = (Button) event.getSource();
    }

    @FXML
    void inventoryClicked(ActionEvent event) {
        showPane(inventoryPane);
        setCurrentButton((Button) event.getSource());
        resetLastClickedButton((Button) event.getSource());
        lastClickedButton = (Button) event.getSource();
    }

    @FXML
    void ordersClicked(ActionEvent event) {
        showPane(ordersPane);
        setCurrentButton((Button) event.getSource());
        resetLastClickedButton((Button) event.getSource());
        lastClickedButton = (Button) event.getSource();
    }

    @FXML
    void suppliersClicked(ActionEvent event) {
        showPane(suppliersPane);
        setCurrentButton((Button) event.getSource());
        resetLastClickedButton((Button) event.getSource());
        lastClickedButton = (Button) event.getSource();
    }

    private void showPane(Pane pane) {
        dash.getChildren().forEach(node -> {
            if (node instanceof Pane) {
                node.setVisible(false);
            }
        });
        pane.setVisible(true);
    }
    private void resetLastClickedButton(Button button){
        if (lastClickedButton != null && lastClickedButton.getText() != "Logout" && !lastClickedButton.equals(button)) {
            lastClickedButton.setStyle("-fx-text-fill: black");
            ImageView imageView =(ImageView) lastClickedButton.getGraphic();
            imageView.setImage(new Image(Login.class.getResourceAsStream("images/"+lastClickedButton.getText()+".png")));
        }
    }
    private void setCurrentButton(Button button){
        button.setStyle("-fx-text-fill: #1570ef");
        ImageView imageView =(ImageView) button.getGraphic();
        imageView.setImage(new Image(Login.class.getResourceAsStream("images/"+button.getText()+"-b.png")));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentButton((Button) nav.getChildren().get(0));
        lastClickedButton = (Button) nav.getChildren().get(0);
    }
}
