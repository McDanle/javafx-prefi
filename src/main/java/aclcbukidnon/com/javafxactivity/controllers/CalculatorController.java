package aclcbukidnon.com.javafxactivity.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private Label display;

    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    @FXML
    private void handleNumberButtonClick(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (startNewInput) {
            currentInput.setLength(0); // clear input
            startNewInput = false;
        }

        currentInput.append(value);
        display.setText(currentInput.toString());
    }

    @FXML
    private void handleOperatorButtonClick(javafx.event.ActionEvent event) {
        String newOperator = ((Button) event.getSource()).getText();

        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
            operator = newOperator;
            startNewInput = true;
        }
    }

    @FXML
    private void handleEqualButtonClick() {
        if (currentInput.length() == 0 || operator.isEmpty()) return;

        double secondOperand = Double.parseDouble(currentInput.toString());
        double result = 0;

        switch (operator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "*": result = firstOperand * secondOperand; break;
            case "/":
                if (secondOperand == 0) {
                    display.setText("Error");
                    return;
                }
                result = firstOperand / secondOperand; break;
        }

        display.setText(String.valueOf(result));
        currentInput.setLength(0);
        currentInput.append(result);
        startNewInput = true;
        operator = "";
    }

    @FXML
    private void handleClearButtonClick() {
        currentInput.setLength(0);
        display.setText("0");
        firstOperand = 0;
        operator = "";
        startNewInput = true;
    }

    @FXML
    private void handleBackspaceButtonClick() {
        if (currentInput.length() > 0) {
            currentInput.setLength(currentInput.length() - 1);
            display.setText(currentInput.length() > 0 ? currentInput.toString() : "0");
        }
    }
}
