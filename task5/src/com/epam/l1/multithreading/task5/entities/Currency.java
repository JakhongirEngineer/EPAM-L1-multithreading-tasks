package com.epam.l1.multithreading.task5.entities;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return Objects.equals(getCode(), currency.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
