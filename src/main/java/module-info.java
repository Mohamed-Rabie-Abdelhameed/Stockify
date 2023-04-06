module com.stockify.stockify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.stockify.stockify to javafx.fxml;
    exports com.stockify.stockify;
    exports com.stockify.stockify.models;
//    opens com.stockify.stockify.models to javafx.fxml;
    opens com.stockify.stockify.models to javafx.base;
}