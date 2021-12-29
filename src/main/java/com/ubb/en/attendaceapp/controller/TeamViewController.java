package com.ubb.en.attendaceapp.controller;

import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.repository.TeamRepository;
import com.ubb.en.attendaceapp.service.TeamService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

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
    }
}
