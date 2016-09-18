package com.dubyniak.bohdan.linearprogramming.controllers;

import com.dubyniak.bohdan.linearprogramming.interfaces.impls.LinearProgrammingTaskImpl;
import com.dubyniak.bohdan.linearprogramming.objects.Inequality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField textFieldK1;

    @FXML
    private TextField textFieldK2;

    @FXML
    private TextField getTextFieldK2;

    @FXML
    private TextField textFieldYKoef;

    @FXML
    private TextField textFieldSign;

    @FXML
    private TextField textFieldB;

    @FXML
    private ListView listView = new ListView();

    private LinearProgrammingTaskImpl task;

    private Stage dialog;
    private Parent root;
    private ObservableList<Inequality> list;

    public MainController() {
        task = new LinearProgrammingTaskImpl();
        list = FXCollections.observableList(task.getTaskData().getLimits());
        listView.setItems(list);
    }

    public void plusButtonClicked(ActionEvent actionEvent) throws IOException {
        if (dialog == null) {
            dialog = new Stage();
            root = FXMLLoader.load(getClass().getResource("../fxml/dialog.fxml"));
            dialog.setTitle("Add limit");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            dialog.setScene(new Scene(root, 409, 103));
        }
        dialog.show();
    }

    public void okButtonClicked(ActionEvent actionEvent) {
        task.getTaskData().addLimit(new Inequality(
                Double.parseDouble(textFieldK1.getText()),
                Double.parseDouble(textFieldK2.getText()),
                Double.parseDouble(textFieldYKoef.getText()),
                "",
                Double.parseDouble(textFieldB.getText())
        ));
        list.add(task.getTaskData().getLimits().get(task.getTaskData().getLimits().size() - 1));
//        listView.getItems().add(task.getTaskData().getLimits().get(task.getTaskData().getLimits().size() - 1));
        dialog.hide();
    }

}
