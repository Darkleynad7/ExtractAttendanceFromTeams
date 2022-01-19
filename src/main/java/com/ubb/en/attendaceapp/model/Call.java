package com.ubb.en.attendaceapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Call {
    private String name;
    private Date date;
    private List<User> connectedUsers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<User> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
        return name + " --- " + simpleDateFormat.format(date) + " --- " + this.connectedUsers.size() + " members";
    }
}
