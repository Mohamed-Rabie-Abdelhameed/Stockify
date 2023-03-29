module com.stockify.stockify {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.stockify.stockify to javafx.fxml;
    exports com.stockify.stockify;
}