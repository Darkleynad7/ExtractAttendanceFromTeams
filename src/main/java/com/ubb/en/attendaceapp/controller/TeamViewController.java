package com.ubb.en.attendaceapp.controller;

import com.ubb.en.attendaceapp.AttendanceApplication;
import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.repository.TeamRepository;
import com.ubb.en.attendaceapp.service.TeamService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamViewController implements Initializable {

    @FXML
    public ListView<Team> TeamsListView;

    @FXML
    private Button SelectTeamButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TeamRepository teamRepository = new TeamRepository();
        TeamService teamService = new TeamService(teamRepository);
        ObservableList<Team> teams = FXCollections.observableArrayList();

        teams.addAll(teamService.getAll());
        TeamsListView.setItems(teams);
        TeamsListView.getSelectionModel().selectFirst();

        SelectTeamButton.setOnAction(event -> {
            Stage stage = new Stage();
            Parent programRoot;
            Callback<Class<?>, Object> controllerFactory = type -> {
                if(type == CallViewController.class){
                    return new CallViewController(TeamsListView.getSelectionModel().getSelectedItem());
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
                FXMLLoader loader = new FXMLLoader(AttendanceApplication.class.getResource("call-view.fxml"));
                loader.setControllerFactory(controllerFactory);
                programRoot = loader.load();
                Scene callScene = new Scene(programRoot);
                stage.setTitle("Call History for Team");
                stage.setScene(callScene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
