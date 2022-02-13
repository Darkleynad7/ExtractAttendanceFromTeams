package com.ubb.en.attendaceapp.repository;



import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.utils.HardcodeResources;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamRepository {
    private final List<Team> teams;

    public void writeToFile(String filePath){
        File file = new File(filePath);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            teams.forEach(t -> printWriter.println(Team.modelToString(t)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String filePath){
        teams.clear();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                teams.add(Team.stringToModel(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TeamRepository(){
        //teams = HardcodeResources.hardCodeTeams(10);
        //writeToFile("src/main/resources/com/ubb/en/attendaceapp/repo.txt");

        teams = new ArrayList<>();
        readFromFile("src/main/resources/com/ubb/en/attendaceapp/repo.txt");
    }

    public List<Team> getAll(){
        return teams;
    }
}
