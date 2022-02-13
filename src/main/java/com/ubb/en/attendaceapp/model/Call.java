package com.ubb.en.attendaceapp.model;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public static String modelToString(Call call){
        return call.name + "||" +  call.date.getTime() + "||" + call.connectedUsers.stream().map(User::modelToString).collect(Collectors.joining("|"));
    }

    public static Call stringToModel(String line){
        String[] parts = line.split("\\|\\|");
        Call call = new Call();
        call.setName(parts[0]);
        call.setDate(new Date(Long.parseLong(parts[1])));
        call.setConnectedUsers(Arrays.stream(parts[2].split("\\|")).map(User::stringToModel).collect(Collectors.toList()));
        return call;
    }
}
