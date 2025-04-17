package aclcbukidnon.com.javafxactivity.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLightController {

    private enum TrafficLightColor {
        STOP,
        HOLD,
        GO,
    }

    private TrafficLightColor currentColor = TrafficLightColor.STOP;
    private Timeline timeline;

    @FXML
    private Circle redLight;
    @FXML
    private Circle yellowLight;
    @FXML
    private Circle greenLight;

    @FXML
    public void initialize() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> onTimerChange())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        updateLights();
    }

    public void onTimerChange() {
        switch (currentColor) {
            case STOP:
                currentColor = TrafficLightColor.HOLD;
                break;
            case HOLD:
                currentColor = TrafficLightColor.GO;
                break;
            case GO:
                currentColor = TrafficLightColor.STOP;
                break;
        }

        updateLights();
    }

    private void updateLights() {
        redLight.setFill(currentColor == TrafficLightColor.STOP ? javafx.scene.paint.Color.RED : javafx.scene.paint.Color.web("#575757"));
        yellowLight.setFill(currentColor == TrafficLightColor.HOLD ? javafx.scene.paint.Color.YELLOW : javafx.scene.paint.Color.web("#575757"));
        greenLight.setFill(currentColor == TrafficLightColor.GO ? javafx.scene.paint.Color.LIME : javafx.scene.paint.Color.web("#575757"));
    }
}
