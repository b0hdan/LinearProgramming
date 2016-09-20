package com.dubyniak.bohdan.linearprogramming.controllers;

import com.dubyniak.bohdan.linearprogramming.interfaces.impls.LinearProgrammingTaskImpl;
import com.dubyniak.bohdan.linearprogramming.start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private LinearProgrammingTaskImpl task;
    private Stage dialog;
    private ListView<String> listView;

    public MainController() {
        task = new LinearProgrammingTaskImpl();
        DialogController.taskData = task.getTaskData();
    }

    public void plusButtonClicked(ActionEvent actionEvent) throws IOException {
        if (dialog == null) {
            dialog = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/dialog.fxml"));
            dialog.setTitle("Add limit");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialog.setScene(new Scene(root, 409, 103));
        }
        dialog.show();
    }

    public void minusButtonClicked(ActionEvent actionEvent) {
        if (listView == null)
            listView = (ListView<String>) Main.mainRoot.lookup("#listView");
        int index = listView.getSelectionModel().getSelectedIndex();
        task.getTaskData().getLimits().remove(index);
        listView.getItems().remove(index);
    }

    public void solveButtonClicked(ActionEvent actionEvent) {
//        task.solve();
//        System.out.println("All points: " + task.getListOfAllPoints());
//        System.out.println("Area points: " + task.getListOfAreaPoints());
//        System.out.println("Minimal value: " + task.getMinValue() + ", point: " + task.getPointOfMinValue());
//        System.out.println("Maximal value: " + task.getMaxValue() + ", point: " + task.getPointOfMaxValue());
    }
}
