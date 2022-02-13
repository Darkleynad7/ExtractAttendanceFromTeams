package com.ubb.en.attendaceapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private Long ID;
    private String name;
    private List<User> admins;
    private List<User> members;
    private List<Call> callHistory;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Call> getCallHistory() {
        return callHistory;
    }

    public void setCallHistory(List<Call> callHistory) {
        this.callHistory = callHistory;
    }

    @Override
    public String toString() {
        return name + " --- " + this.members.size() + " members";
    }

    public static String modelToString(Team team){
        return team.ID + "!" + team.name + "!"
                + team.admins.stream().map(User::modelToString).collect(Collectors.joining("?")) + "!"
                + team.members.stream().map(User::modelToString).collect(Collectors.joining("?")) + "!"
                + team.callHistory.stream().map(Call::modelToString).collect(Collectors.joining("?"));
    }

    public static Team stringToModel(String line){
        String[] parts = line.split("!");
        Team team = new Team();
        team.setID(Long.parseLong(parts[0]));
        team.setName(parts[1]);
        team.setAdmins(Arrays.stream(parts[2].split("\\?")).map(User::stringToModel).collect(Collectors.toList()));
        team.setMembers(Arrays.stream(parts[3].split("\\?")).map(User::stringToModel).collect(Collectors.toList()));
        team.setCallHistory(Arrays.stream(parts[4].split("\\?")).map(Call::stringToModel).collect(Collectors.toList()));
        return team;
    }
}
