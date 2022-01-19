package com.ubb.en.attendaceapp.repository;



import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.utils.HardcodeResources;


import java.util.List;

public class TeamRepository {
    private final List<Team> teams;

    public TeamRepository(){
        teams = HardcodeResources.hardCodeTeams(10);
    }

    public List<Team> getAll(){
        return teams;
    }
}
