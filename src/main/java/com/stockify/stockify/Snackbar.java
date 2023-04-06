package com.stockify.stockify;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Snackbar {

    public static void show(String message, boolean isGood) {
        final int DISPLAY_DURATION = 5000; // Display duration in milliseconds
        final int FADE_DURATION = 2500; // Fade out duration in milliseconds

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Snackbar.class.getResource("Snackbar.fxml"));
            AnchorPane pane = loader.load();
            Label messageLabel = (Label) loader.getNamespace().get("messageLabel");
            messageLabel.setText(message);

            StackPane stackPane = (StackPane) pane.getChildren().get(0);
            Rectangle background = (Rectangle) stackPane.getChildren().get(0);
            background.setArcWidth(10); // Modify the corner radius
            background.setArcHeight(10); // Modify the corner radius
            if (isGood) {
                background.setFill(Color.GREEN);
            } else {
                background.setFill(Color.RED);
            }

            stackPane.setBackground(null); // Set the StackPane background to null

            Scene scene = new Scene(pane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initOwner(null);
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double snackbarHeight = pane.getPrefHeight();
            double snackbarWidth = pane.getPrefWidth();
            double x = (screenBounds.getWidth() - snackbarWidth) / 2;
            double y = screenBounds.getHeight() - snackbarHeight - 10; // 10 pixels padding from the bottom of the screen
            stage.setX(x);
            stage.setY(y);

            TranslateTransition shakeAnimation = new TranslateTransition(Duration.millis(30), pane);
            shakeAnimation.setFromX(0);
            shakeAnimation.setToX(10);
            shakeAnimation.setCycleCount(6);
            shakeAnimation.setAutoReverse(true);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(FADE_DURATION), pane);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            shakeAnimation.setOnFinished(event -> {
                fadeOut.setOnFinished(fadeEvent -> stage.close());
                fadeOut.play();
            });

            stage.show();
            shakeAnimation.play();

            PauseTransition delay = new PauseTransition(Duration.millis(DISPLAY_DURATION));
            delay.setOnFinished(event -> shakeAnimation.stop());
            delay.setOnFinished(event -> {
                fadeOut.setOnFinished(fadeEvent -> stage.close());
                fadeOut.play();
            });
            delay.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}