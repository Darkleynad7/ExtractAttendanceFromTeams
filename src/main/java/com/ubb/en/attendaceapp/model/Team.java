package com.ubb.en.attendaceapp.model;

import java.util.List;

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
}
