package com.epam.l1.multithreading.task5.entities;

import java.math.BigDecimal;
import java.util.Map;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private Map<Currency, BigDecimal> balance;

    public User() {
    }

    public User(int id, String firstName, String lastName, String password, Map<Currency, BigDecimal> balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Map<Currency, BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(Map<Currency, BigDecimal> balance) {
        this.balance = balance;
    }
}
