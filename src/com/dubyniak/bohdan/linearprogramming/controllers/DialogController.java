package com.dubyniak.bohdan.linearprogramming.controllers;

import com.dubyniak.bohdan.linearprogramming.objects.Inequality;
import com.dubyniak.bohdan.linearprogramming.objects.TaskData;
import com.dubyniak.bohdan.linearprogramming.start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DialogController {
    static TaskData taskData;

    @FXML
    TextField txtK1;

    @FXML
    TextField txtK2;

    @FXML
    TextField txtYKoef;

    @FXML
    TextField txtSign;

    @FXML
    TextField txtB;

    public void okButtonClicked(ActionEvent actionEvent) {
        ListView<String> listView = (ListView<String>) Main.mainRoot.lookup("#listView");
        taskData.getLimits().add(new Inequality(
                Double.parseDouble(txtK1.getText()),
                Double.parseDouble(txtK2.getText()),
                Double.parseDouble(txtYKoef.getText()),
                txtSign.getText(),
                Double.parseDouble(txtB.getText())
        ));
        listView.getItems().add(taskData.getLimits().get(taskData.getLimits().size() - 1).toString());
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        txtK1.clear();
        txtK2.clear();
        txtYKoef.clear();
        txtSign.clear();
        txtB.clear();
        txtK1.requestFocus();
    }

}
