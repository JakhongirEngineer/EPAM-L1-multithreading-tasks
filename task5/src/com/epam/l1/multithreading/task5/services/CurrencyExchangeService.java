package com.epam.l1.multithreading.task5.services;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.repositories.CurrencyRepository;
import com.epam.l1.multithreading.task5.repositories.ExchangeRateRepository;
import com.epam.l1.multithreading.task5.repositories.UserRepository;

public class CurrencyExchangeService{
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyExchangeService(UserRepository userRepository,
                                   CurrencyRepository currencyRepository,
                                   ExchangeRateRepository exchangeRateRepository) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public synchronized boolean exchange(int userId, Currency from, Currency to){

        System.out.println("userId: " + userId + " from: " + from.getCode() + " to: " + to.getCode());

        return true;
    }
}
