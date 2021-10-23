package com.epam.l1.multithreading.task5.entities;

public class Currency {
    private String code; // USD, SUM

    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
