package com.ubb.en.attendaceapp.controller;

import com.ubb.en.attendaceapp.AttendanceApplication;
import com.ubb.en.attendaceapp.model.Call;
import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.model.User;
import com.ubb.en.attendaceapp.service.AttendanceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CallViewController implements Initializable {
    @FXML
    ListView<Call> callView;

    @FXML
    ListView<User> adminView;

    @FXML
    Button openCallView;

    @FXML
    Button extractAttendance;

    @FXML
    Button openFile;

    @FXML
    TextField filePath;

    private final Team team;

    private final AttendanceService attendanceService;

    public CallViewController(Team team) {
        this.team = team;
        attendanceService = new AttendanceService(team.getName() + ".csv", team.getMembers());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Call> calls = FXCollections.observableArrayList();
        calls.addAll(team.getCallHistory().stream().sorted(Comparator.comparingLong(t -> t.getDate().getTime())).collect(Collectors.toList()));
        callView.setItems(calls);
        callView.getSelectionModel().selectFirst();

        ObservableList<User> admins = FXCollections.observableArrayList();
        admins.addAll(team.getAdmins());
        adminView.setItems(admins);
        adminView.getSelectionModel().selectFirst();

        filePath.setDisable(true);
        filePath.setText(team.getName() + ".csv");

        openFile.setOnAction(event -> {
            attendanceService.openFile();
        });

        extractAttendance.setOnAction(event -> {
            attendanceService.addDate(callView.getSelectionModel().getSelectedItem().getDate(), callView.getSelectionModel().getSelectedItem().getConnectedUsers());
        });

        openCallView.setOnAction(event -> {
            Stage stage = new Stage();
            Parent programRoot;
            Callback<Class<?>, Object> controllerFactory = type -> {
                if(type == UsersViewController.class){
                    return new UsersViewController(callView.getSelectionModel().getSelectedItem());
                }
                else {
                    try {
                        return type.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            try {
                FXMLLoader loader = new FXMLLoader(AttendanceApplication.class.getResource("users-view.fxml"));
                loader.setControllerFactory(controllerFactory);
                programRoot = loader.load();
                Scene callScene = new Scene(programRoot);
                stage.setTitle("Users connected to call");
                stage.setScene(callScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
