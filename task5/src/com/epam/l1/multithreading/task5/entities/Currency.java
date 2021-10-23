package com.epam.l1.multithreading.task5.entities;

import java.math.BigDecimal;

public class Currency {
    private String code; // USD, SUM
    private BigDecimal value;

    public Currency() {
    }

    public Currency(String code, BigDecimal value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
