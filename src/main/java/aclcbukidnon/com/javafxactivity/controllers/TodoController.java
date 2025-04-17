package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    private final ObservableList<String> todos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        todos.add("Remove Me");

        todoList.setItems(todos);

        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        todoList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selected = todoList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    onTodoListItemClick(selected);
                }
            }
        });
    }

    private void onTodoListItemClick(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle("Update Todo");
        dialog.setHeaderText("Edit your todo:");
        dialog.setContentText("Todo:");

        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
            if (!text.trim().isEmpty() && selectedIndex >= 0) {
                todos.set(selectedIndex, text.trim());
            }
        });
    }

    @FXML
    protected void onCreateClick() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Create New Todo");
        dialog.setHeaderText("Add a new todo:");
        dialog.setContentText("Todo:");

        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            if (!text.trim().isEmpty()) {
                todos.add(text.trim());
            }
        });
    }

    @FXML
    protected void onDeleteClick() {
        int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("No Selection");
            warning.setHeaderText("No todo selected");
            warning.setContentText("Please select a todo to delete.");
            warning.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Confirmation");
        confirm.setHeaderText("Are you sure you want to delete this todo?");
        confirm.setContentText("This action cannot be undone.");

        var result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            todos.remove(selectedIndex);
        }
    }

    @FXML
    protected void onListEdit() {
    }
}
