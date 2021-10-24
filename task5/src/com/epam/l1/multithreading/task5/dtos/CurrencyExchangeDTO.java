package com.epam.l1.multithreading.task5.dtos;

import com.epam.l1.multithreading.task5.entities.Currency;

import java.math.BigDecimal;

public class CurrencyExchangeDTO {
    private Currency from;
    private Currency to;
    private String userFileName;
    private String currencyFileName;
    private BigDecimal toAmount;
    private String exchangeRatesFileName;

    public CurrencyExchangeDTO() {
    }

    public CurrencyExchangeDTO(Currency from, Currency to, String userFileName, String currencyFileName, BigDecimal toAmount, String exchangeRatesFileName) {
        this.from = from;
        this.to = to;
        this.userFileName = userFileName;
        this.currencyFileName = currencyFileName;
        this.toAmount = toAmount;
        this.exchangeRatesFileName = exchangeRatesFileName;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public String getCurrencyFileName() {
        return currencyFileName;
    }

    public void setCurrencyFileName(String currencyFileName) {
        this.currencyFileName = currencyFileName;
    }

    public BigDecimal getToAmount() {
        return toAmount;
    }

    public void setToAmount(BigDecimal toAmount) {
        this.toAmount = toAmount;
    }

    public String getExchangeRatesFileName() {
        return exchangeRatesFileName;
    }

    public void setExchangeRatesFileName(String exchangeRatesFileName) {
        this.exchangeRatesFileName = exchangeRatesFileName;
    }
}
