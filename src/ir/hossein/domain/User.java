package ir.hossein.domain;

public class User extends Person {
    public User(Integer id, String firstName, String surName, String userName, String password) {
        super(id, firstName, surName, userName, password);
    }

    public User() {
    }
}
