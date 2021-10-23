package com.epam.l1.multithreading.task5.entities;

import java.util.Set;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private Set<Currency> currencies;

    public User() {
    }

    public User(String firstName, String lastName, String password, Set<Currency> currencies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.currencies = currencies;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }
}
