package ru.job4j.emailcombain;

import java.util.List;

public class User {
    private String name;
    private List<String> emails;

    public User() {

    }

    public User(String name, List<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
