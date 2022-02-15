package com.ubb.en.attendaceapp.service;

import com.ubb.en.attendaceapp.model.User;
import com.ubb.en.attendaceapp.repository.AttendanceRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AttendanceService {
    private AttendanceRepository attendanceRepository;

    public AttendanceService(String filePath, List<User> userList){
        attendanceRepository = new AttendanceRepository(filePath, userList);
    }

    public void addDate(Date date, List<User> userList){
        attendanceRepository.addNewDate(date, userList);
    }

    public void openFile() {
        try {
            attendanceRepository.openFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Date> getDates() {
        return attendanceRepository.getDates();
    }
}
