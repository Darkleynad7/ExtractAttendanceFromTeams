package com.ubb.en.attendaceapp.model;

import java.util.Arrays;

public class User {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " --- " + email;
    }

    public static String modelToString(User user){
        return user.firstName + "-" + user.lastName + "-" + user.email;
    }

    public static User stringToModel(String line){
        String[] parts = line.split("-");
        User user = new User();
        user.setFirstName(parts[0]);
        user.setLastName(parts[1]);
        user.setEmail(parts[2]);
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User other = (User) obj;
            return other.firstName.equals(this.firstName) && other.lastName.equals(this.lastName);
        }
        return false;
    }
}
