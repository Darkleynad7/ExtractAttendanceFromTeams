package com.ubb.en.attendaceapp.utils;

import com.ubb.en.attendaceapp.model.Call;
import com.ubb.en.attendaceapp.model.Team;
import com.ubb.en.attendaceapp.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class HardcodeResources {
    private static List<String> FIRST_NAMES = new ArrayList<>(List.of("ALEXANDRU", "ALEXANDRA", "ANDREI", "ANDREEA", "ALIN", "ALINA", "ANA", "BOGDAN", "CAMELIA", "CRISTIAN", "CRISTINA", "DANIEL", "DAN", "ELENA", "GEORGE", "GHEORGHE", "GEORGETA", "IOAN", "IOANA", "IRINA", "MARIA", "MARIAN", "MONICA", "OCTAVIAN", "OTILIA", "PAUL", "PETRU", "PAULA", "ROBERT", "RAUL", "RALUCA", "SEBASTIAN"));
    private static List<String> LAST_NAMES = new ArrayList<>(List.of("ARDELEAN", "ACIU", "BACIU", "COMSA", "COMAN", "DUMA", "DANULESCU", "DUMBRAVA", "ENACHE", "FLOREA", "GOICEANU", "HEBENDEAN", "IVANON", "LASCU", "MACOVEI", "MARINESCU", "MORAR", "MUNTEANU", "OLTU", "PANA", "POPESCU", "RADULESCU", "SAMOILESCU", "TOPEA", "URSEA", "VOICU"));

    public static List<User> hardCodeUsers(Integer noOfUsers){
        List<User> users = new ArrayList<>();
        for(int i = 0; i < noOfUsers; i++){
            User user = new User();
            Random random = new Random();
            user.setFirstName(FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size())));
            user.setLastName(LAST_NAMES.get(random.nextInt(LAST_NAMES.size())));
            user.setEmail(user.getFirstName().toLowerCase(Locale.ROOT) + "." + user.getLastName().toLowerCase(Locale.ROOT) + "@stud.ubbcluj.ro");
            if(users.stream().map(User::getEmail).collect(Collectors.toList()).contains(user.getEmail())){
                i--;
            }
            else users.add(user);
        }
        return users;
    }

    public static List<Team> hardCodeTeams(Integer noOfTeams){
        List<Team> teams = new ArrayList<>();
        for(int i = 0; i < noOfTeams; i++){
            Team team = new Team();
            team.setID((long) i);
            team.setName("Team " + i);
            Random random = new Random();
            team.setMembers(hardCodeUsers(random.nextInt(40 - 30) + 30));
            team.setAdmins(Collections.singletonList(team.getMembers().get(0)));
            team.setCallHistory(hardCodeCalls(random.nextInt(20 - 10) + 10, team.getMembers()));
            teams.add(team);
        }
        return teams;
    }

    private static List<Call> hardCodeCalls(Integer noOfCalls, List<User> members) {
        List<Call> calls = new ArrayList<>();
        for(int i = 0; i < noOfCalls; i++){
            Call call = new Call();
            Random random = new Random();
            call.setName("Call " + i);
            try {
                call.setDate(generateDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/10/2021"), new Date()));
            } catch (ParseException ignored){}
            int seriesLength = random.nextInt(members.size() - 10) + 10;
            Collections.shuffle(members);
            call.setConnectedUsers(members.subList(0, seriesLength));
            calls.add(call);
        }
        return calls;
    }

    public static Date generateDate(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }
}
