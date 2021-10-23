package com.epam.l1.multithreading.task5;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.repositories.*;
import com.epam.l1.multithreading.task5.services.CurrencyExchangeService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CurrencyExchangeApplication {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepositoryImpl();
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();

        CurrencyExchangeService currencyExchangeService =
                new CurrencyExchangeService(userRepository,currencyRepository,exchangeRateRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Currency EUR = new Currency("EUR");
        Currency USD = new Currency("USD");
        Currency SUM = new Currency("SUM");

        // for user1
        executorService.submit(() -> currencyExchangeService.exchange(1,EUR,USD));
        executorService.submit(() -> currencyExchangeService.exchange(1,EUR,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(1,USD,EUR));
        executorService.submit(() -> currencyExchangeService.exchange(1,USD,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(1,SUM,USD));
        executorService.submit(() -> currencyExchangeService.exchange(1,SUM,EUR));

        // for user2
        executorService.submit(() -> currencyExchangeService.exchange(2,EUR,USD));
        executorService.submit(() -> currencyExchangeService.exchange(2,EUR,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(2,USD,EUR));
        executorService.submit(() -> currencyExchangeService.exchange(2,USD,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(2,SUM,USD));
        executorService.submit(() -> currencyExchangeService.exchange(2,SUM,EUR));

        // for user3
        executorService.submit(() -> currencyExchangeService.exchange(3,EUR,USD));
        executorService.submit(() -> currencyExchangeService.exchange(3,EUR,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(3,USD,EUR));
        executorService.submit(() -> currencyExchangeService.exchange(3,USD,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(3,SUM,USD));
        executorService.submit(() -> currencyExchangeService.exchange(3,SUM,EUR));

        // for user4
        executorService.submit(() -> currencyExchangeService.exchange(4,EUR,USD));
        executorService.submit(() -> currencyExchangeService.exchange(4,EUR,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(4,USD,EUR));
        executorService.submit(() -> currencyExchangeService.exchange(4,USD,SUM));
        executorService.submit(() -> currencyExchangeService.exchange(4,SUM,USD));
        executorService.submit(() -> currencyExchangeService.exchange(4,SUM,EUR));
    }
}
