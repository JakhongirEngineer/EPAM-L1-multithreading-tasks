package com.epam.l1.multithreading.task5;

import com.epam.l1.multithreading.task5.dtos.CurrencyExchangeDTO;
import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.exceptions.ExchangeNotSupported;
import com.epam.l1.multithreading.task5.repositories.*;
import com.epam.l1.multithreading.task5.services.CurrencyExchangeService;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CurrencyExchangeApplication {
    private static final String basePathName;
    private static final String exchangeRatesFileName;
    private static final String supportedCurrenciesFileName;
    private static final String user1FileName;
    private static final String user2FileName;
    private static final String user3FileName;
    private static final String user4FileName;

    static {
        basePathName = "task5/src/resources/";
        exchangeRatesFileName = new File(basePathName +"exchangeRates.txt").getAbsolutePath();
        supportedCurrenciesFileName = new File(basePathName +"supported_currencies.txt").getAbsolutePath();
        user1FileName = new File(basePathName + "user1.txt").getAbsolutePath();
        user2FileName = new File(basePathName + "user2.txt").getAbsolutePath();
        user3FileName = new File(basePathName + "user3.txt").getAbsolutePath();
        user4FileName = new File( basePathName +"user4.txt").getAbsolutePath();
    }

    public static void main(String[] args) throws ExchangeNotSupported {

        UserRepository userRepository = new UserRepositoryImpl();
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();

        CurrencyExchangeService currencyExchangeService =
                new CurrencyExchangeService(userRepository,currencyRepository,exchangeRateRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Currency EUR = new Currency("EUR");
        Currency USD = new Currency("USD");
        Currency SUM = new Currency("SUM");


        CurrencyExchangeDTO currencyExchangeDTO = new CurrencyExchangeDTO();
        currencyExchangeDTO.setCurrencyFileName(supportedCurrenciesFileName);
        currencyExchangeDTO.setUserFileName(user1FileName);
        currencyExchangeDTO.setFrom(SUM);
        currencyExchangeDTO.setTo(USD);
        currencyExchangeDTO.setToAmount(new BigDecimal(5)); // wanting to get 10 dollars
        currencyExchangeDTO.setExchangeRatesFileName(exchangeRatesFileName);

        executorService.submit(() -> currencyExchangeService.exchange(currencyExchangeDTO));

//        // for user1
//        executorService.submit(() -> currencyExchangeService.exchange(1,EUR,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(1,EUR,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(1,USD,EUR));
//        executorService.submit(() -> currencyExchangeService.exchange(1,USD,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(1,SUM,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(1,SUM,EUR));
//
//        // for user2
//        executorService.submit(() -> currencyExchangeService.exchange(2,EUR,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(2,EUR,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(2,USD,EUR));
//        executorService.submit(() -> currencyExchangeService.exchange(2,USD,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(2,SUM,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(2,SUM,EUR));
//
//        // for user3
//        executorService.submit(() -> currencyExchangeService.exchange(3,EUR,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(3,EUR,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(3,USD,EUR));
//        executorService.submit(() -> currencyExchangeService.exchange(3,USD,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(3,SUM,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(3,SUM,EUR));
//
//        // for user4
//        executorService.submit(() -> currencyExchangeService.exchange(4,EUR,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(4,EUR,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(4,USD,EUR));
//        executorService.submit(() -> currencyExchangeService.exchange(4,USD,SUM));
//        executorService.submit(() -> currencyExchangeService.exchange(4,SUM,USD));
//        executorService.submit(() -> currencyExchangeService.exchange(4,SUM,EUR));

        executorService.shutdown();
    }
}
