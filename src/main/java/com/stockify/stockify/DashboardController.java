package com.stockify.stockify;
import com.stockify.stockify.models.Processes;
import com.stockify.stockify.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label inStockLabel;
    @FXML
    private Label toBeRecievedLabel;
    @FXML
    private TableView productsTable;

    @FXML
    private TableColumn<Product, Integer> productIdColumn = new TableColumn<>("ID");

    @FXML
    private TableColumn<Product, String> productNameColumn = new TableColumn<>("Name");

    @FXML
    private TableColumn<Product, Integer> productCategoryIdColumn = new TableColumn<>("Category_id");

    @FXML
    private TableColumn<Product, Integer> productSupplierIdColumn = new TableColumn<>("Supplier_id");

    @FXML
    private TableColumn<Product, Double> productPriceColumn = new TableColumn<>("Price");

    @FXML
    private TableColumn<Product, Integer> productQuantityColumn = new TableColumn<>("Quantity");


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

    public void refreshProductsTable() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(Processes.getAllProducts());
        productsTable.setItems(products);
        inStockLabel.setText(String.valueOf(Processes.getInStock()));
        toBeRecievedLabel.setText(String.valueOf(Processes.getToBeReceived()));
    }

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
    void logoutClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void addProductClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add-product-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Stockify");
        stage.setScene(new Scene(root, 570, 370));
        stage.setResizable(false);
        stage.getIcons().add(new Image(Login.class.getResourceAsStream("images/logo.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        refreshProductsTable();
    }
    @FXML
    void deleteProductClicked(ActionEvent event) {
        Product product = (Product) productsTable.getSelectionModel().getSelectedItem();
        if(product == null){
            Snackbar.show("Please select a product to delete",false);
            return;
        }
        int id = product.getId();
        try {
            Processes.deleteProduct(id);
            productsTable.getItems().removeAll(productsTable.getSelectionModel().getSelectedItem());
            Snackbar.show("Product deleted successfully",true);
        } catch (Exception e) {
            Snackbar.show("Error deleting product",false);
            e.printStackTrace();
        }
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

    private void setProductsTable(){
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCategoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        productSupplierIdColumn.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, String> event) -> {
            Product product = event.getRowValue();
            String newValue = event.getNewValue();
            if (newValue == null || newValue.trim().isEmpty()) {
                Snackbar.show("Name cannot be empty!", false);
                return;
            }
            product.setName(event.getNewValue());
            boolean isDone = Processes.updateProduct(product);
            if(isDone){
                Snackbar.show("Name updated successfully!", true);
            } else {
                Snackbar.show("Name update failed!", false);
            }
        });
        productPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        productPriceColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, Double> event) -> {
            Product product = event.getRowValue();
            Double newValue = event.getNewValue();
            if (newValue == null || newValue <= 0) {
                Snackbar.show("Price cannot be negative or empty!", false);
                return;
            }
            product.setPrice(event.getNewValue());
            boolean isDone = Processes.updateProduct(product);
            if(isDone){
                Snackbar.show("Price updated successfully!", true);
            } else {
                Snackbar.show("Price update failed!", false);
            }
        });
        productQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productQuantityColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, Integer> event) -> {
            Product product = event.getRowValue();
            Integer newValue = event.getNewValue();
            if (newValue == null || newValue < 0) {
                Snackbar.show("Quantity cannot be negative!", false);
                return;
            }
            product.setQuantity(event.getNewValue());
            boolean isDone = Processes.updateProduct(product);
            if(isDone){
                Snackbar.show("Quantity updated successfully!", true);
            } else {
                Snackbar.show("Quantity update failed!", false);
            }
        });
        Product[] products = Processes.getAllProducts();
        ObservableList<Product> data = FXCollections.observableArrayList(products);
        productsTable.setItems(data);
    }

    private void setLabels(){
        inStockLabel.setText(String.valueOf(Processes.getInStock()));
        toBeRecievedLabel.setText(String.valueOf(Processes.getToBeReceived()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCurrentButton((Button) nav.getChildren().get(1));
        lastClickedButton = (Button) nav.getChildren().get(1);
        setProductsTable();
        setLabels();
    }
}
