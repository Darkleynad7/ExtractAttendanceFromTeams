package com.ubb.en.attendaceapp.service;

import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List<Team> getAll(){
        return teamRepository.getAll();
    }
}
