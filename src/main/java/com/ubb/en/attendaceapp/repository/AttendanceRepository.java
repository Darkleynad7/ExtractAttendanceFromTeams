package com.ubb.en.attendaceapp.repository;

import com.ubb.en.attendaceapp.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.Desktop;
import java.util.stream.IntStream;

public class AttendanceRepository {
    private Map<Date, List<User>> userForDates;
    private List<User> allUsers;
    private String filePath;

    public AttendanceRepository(String filePath, List<User> allUsers){
        this.filePath = "src/main/resources/com/ubb/en/attendaceapp/" + filePath;
        userForDates = new HashMap<>();
        this.allUsers = allUsers;
        File file = new File(this.filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile() throws IOException {
        Desktop.getDesktop().open(new File(filePath));
    }

    public void addNewDate(Date date, List<User> users){
        readFile();
        userForDates.put(date, users);
        writeToFile();
    }

    public List<Date> getDates(){
        readFile();
        return new ArrayList<>(userForDates.keySet());
    }

    private void writeToFile(){
        File csvOutputFile = new File(this.filePath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.print(",");
            pw.print(userForDates.keySet().stream().sorted(Comparator.comparingLong(Date::getTime)).map(simpleDateFormat::format).collect(Collectors.joining(",")));
            pw.println(",TOTAL");
            final int[] row = {2};
            allUsers.stream().sorted(Comparator.comparing(u -> u.getLastName() + u.getFirstName())).forEach(u -> {
                pw.print(u.getLastName() + " " + u.getFirstName() + ",");
                pw.print(userForDates.keySet().stream().sorted(Comparator.comparingLong(Date::getTime)).map(d -> {
                    if(userForDates.get(d).contains(u))
                        return "1";
                    else return "0";
                }).collect(Collectors.joining(",")));
                pw.println(",=SUM(A" + row[0] + ":" + (char) ((int) 'A' + userForDates.keySet().size()) + row[0] + ")");
                row[0]++;
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFile(){
        File csvInputFile = new File(this.filePath);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
        try(Scanner scanner = new Scanner(csvInputFile)){
            if(scanner.hasNextLine()){
                String dateString = scanner.nextLine();
                dateString = dateString.substring(1, dateString.length() - 6);
                String[] parts = dateString.split(",");
                userForDates.clear();
                Arrays.stream(parts).map(s -> {
                    try {
                        return simpleDateFormat.parse(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).forEach(d -> userForDates.put(d, new ArrayList<>()));
            }
            if(scanner.hasNextLine()){
                allUsers.forEach(u -> {
                    String[] parts = scanner.nextLine().split(",");
                    final int[] i = {1};
                    if(parts.length > 1)
                        userForDates.keySet().forEach(d -> {
                            if(Objects.equals(parts[i[0]], "1"))
                                userForDates.get(d).add(u);
                            i[0]++;
                        });
                    else i[0]++;

                });
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
