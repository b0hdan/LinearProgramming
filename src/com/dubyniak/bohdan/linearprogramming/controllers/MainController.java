package com.dubyniak.bohdan.linearprogramming.controllers;

import com.dubyniak.bohdan.linearprogramming.interfaces.impls.LinearProgrammingTaskImpl;
import com.dubyniak.bohdan.linearprogramming.objects.MathematicalExpression;
import com.dubyniak.bohdan.linearprogramming.objects.Point;
import com.dubyniak.bohdan.linearprogramming.start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private LinearProgrammingTaskImpl task;
    private Stage dialog, output;
    private Parent outputRoot;
    private ListView<String> listView;

    @FXML
    private TextField txtPurposeK1;

    @FXML
    private TextField txtPurposeK2;

    @FXML
    private TextField txtPurposeYKoef;

    @FXML
    private TextField txtYK1;

    @FXML
    private TextField txtYK2;

    public MainController() {
        task = new LinearProgrammingTaskImpl();
        DialogController.taskData = task.getTaskData();
    }

    public void plusButtonClicked(ActionEvent actionEvent) throws IOException {
        if (dialog == null) {
            dialog = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/dialog.fxml"));
            dialog.setTitle("Add limit");
            dialog.setResizable(false);
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

    public void solveButtonClicked(ActionEvent actionEvent) throws IOException {
        task.getTaskData().setPurposeFunction(new MathematicalExpression(
                Double.parseDouble(txtPurposeK1.getText()),
                Double.parseDouble(txtPurposeK2.getText()),
                Double.parseDouble(txtPurposeYKoef.getText())
        ));
        task.getTaskData().setY(
                Double.parseDouble(txtYK1.getText()),
                Double.parseDouble(txtYK2.getText())
        );
        task.solve();

        if (output == null) {
            output = new Stage();
            outputRoot = FXMLLoader.load(getClass().getResource("../fxml/output.fxml"));
            output.setTitle("Result");
            output.setResizable(false);
            output.initModality(Modality.APPLICATION_MODAL);
            output.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            output.setScene(new Scene(outputRoot, 445, 289));
        }
        output.show();
        ListView<String> lvAllPoints = (ListView<String>) outputRoot.lookup("#lvAllPoints");
        ListView<String> lvAreaPoints = (ListView<String>) outputRoot.lookup("#lvAreaPoints");
        Label lblMin = (Label) outputRoot.lookup("#lblMin");
        Label lblMax = (Label) outputRoot.lookup("#lblMax");
        for (Point point : task.getListOfAllPoints())
            lvAllPoints.getItems().add(point.toString());
        for (Point point : task.getListOfAreaPoints())
            lvAreaPoints.getItems().add(point.toString());
        if (task.isMinPoint())
            lblMin.setText(String.format("Minimal value: %.2f, " +
                    "point: " + task.getPointOfMinValue() + ".", task.getMinValue()));
        else
            lblMin.setText("");
        if (task.isMinPoint())
            lblMax.setText(String.format("Maximal value: %.2f, " +
                    "point: " + task.getPointOfMaxValue() + ".", task.getMaxValue()));
        else
            lblMax.setText("");
    }

}
