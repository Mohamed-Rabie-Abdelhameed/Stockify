package com.stockify.stockify;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

public class Dashboard extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Instant start = Instant.now();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard-view.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Stockify");
            stage.setScene(new Scene(root, 1400, 780));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(Login.class.getResourceAsStream("images/logo.png"))));
            stage.show();
            Instant end = Instant.now();
            System.out.println("Time taken to load the dashboard: " + (end.toEpochMilli() - start.toEpochMilli()) + "ms");}
        catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
