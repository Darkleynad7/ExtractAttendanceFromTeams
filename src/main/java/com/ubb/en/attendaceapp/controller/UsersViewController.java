package com.ubb.en.attendaceapp.controller;

import com.ubb.en.attendaceapp.model.Call;
import com.ubb.en.attendaceapp.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersViewController implements Initializable {

    @FXML
    public ListView<User> usersListView;

    @FXML
    public TextField lastName;

    @FXML
    public TextField email;

    @FXML
    public TextField firstName;

    @FXML
    public Button extractAttendance;

    @FXML
    public Button goBack;

    private final Call call;

    public UsersViewController(Call call) {
        this.call = call;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<User> users = FXCollections.observableArrayList();
        users.addAll(call.getConnectedUsers());
        usersListView.setItems(users);
        usersListView.getSelectionModel().selectFirst();

        lastName.setDisable(true);
        firstName.setDisable(true);
        email.setDisable(true);

        lastName.setText(usersListView.getSelectionModel().getSelectedItems().get(0).getLastName());
        firstName.setText(usersListView.getSelectionModel().getSelectedItems().get(0).getFirstName());
        email.setText(usersListView.getSelectionModel().getSelectedItems().get(0).getEmail());

        usersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            lastName.setText(newValue.getLastName());
            firstName.setText(newValue.getFirstName());
            email.setText(newValue.getEmail());
        });

        goBack.setOnAction(event -> {
            ((Stage) goBack.getScene().getWindow()).close();
        });
    }
}
