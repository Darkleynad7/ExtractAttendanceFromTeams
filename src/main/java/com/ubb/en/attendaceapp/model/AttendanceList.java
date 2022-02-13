package com.ubb.en.attendaceapp.model;

import com.ubb.en.attendaceapp.repository.AttendanceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceList {
    private final Date date;
    private final List<User> userList;

    public AttendanceList(Date date, List<User> userList) {
        this.date = date;
        this.userList = userList;
    }

    public Date getDate() {
        return date;
    }

    public List<User> getUserList() {
        return userList;
    }
}
