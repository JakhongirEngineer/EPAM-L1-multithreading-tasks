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

        // data for user1
        CurrencyExchangeDTO user1FromSUMToUSD = new CurrencyExchangeDTO();
        user1FromSUMToUSD.setCurrencyFileName(supportedCurrenciesFileName);
        user1FromSUMToUSD.setUserFileName(user1FileName);
        user1FromSUMToUSD.setFrom(SUM);
        user1FromSUMToUSD.setTo(USD);
        user1FromSUMToUSD.setToAmount(new BigDecimal(5)); // wanting to get 10 dollars
        user1FromSUMToUSD.setExchangeRatesFileName(exchangeRatesFileName);

        executorService.submit(() -> currencyExchangeService.exchange(user1FromSUMToUSD));

        CurrencyExchangeDTO user1FromSUMToEUR = copyCurrencyExchangeRateDTO(user1FromSUMToUSD);
        user1FromSUMToEUR.setTo(EUR);
        executorService.submit(() -> currencyExchangeService.exchange(user1FromSUMToEUR));

        CurrencyExchangeDTO user1FromUSDToEUR = copyCurrencyExchangeRateDTO(user1FromSUMToEUR);
        user1FromUSDToEUR.setFrom(USD);
        executorService.submit(() -> currencyExchangeService.exchange(user1FromUSDToEUR));

        // data for user2
        CurrencyExchangeDTO user2FromUSDToEUR = copyCurrencyExchangeRateDTO(user1FromUSDToEUR);
        user2FromUSDToEUR.setUserFileName(user2FileName);

        executorService.submit(() -> currencyExchangeService.exchange(user2FromUSDToEUR));

        CurrencyExchangeDTO user2FromSUMToEUR = copyCurrencyExchangeRateDTO(user1FromSUMToUSD);
        user2FromSUMToEUR.setUserFileName(user2FileName);
        executorService.submit(() -> currencyExchangeService.exchange(user2FromSUMToEUR));

        executorService.shutdown();
    }

    private static CurrencyExchangeDTO copyCurrencyExchangeRateDTO(CurrencyExchangeDTO dto){
        CurrencyExchangeDTO newDTO = new CurrencyExchangeDTO();
        newDTO.setToAmount(dto.getToAmount());
        newDTO.setFrom(dto.getFrom());
        newDTO.setTo(dto.getTo());
        newDTO.setCurrencyFileName(dto.getCurrencyFileName());
        newDTO.setExchangeRatesFileName(dto.getExchangeRatesFileName());
        newDTO.setUserFileName(dto.getUserFileName());

        return newDTO;
    }
}
