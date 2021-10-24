package com.epam.l1.multithreading.task5.services;

import com.epam.l1.multithreading.task5.dtos.CurrencyExchangeDTO;
import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;
import com.epam.l1.multithreading.task5.entities.User;
import com.epam.l1.multithreading.task5.exceptions.ExchangeNotSupported;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateNotFound;
import com.epam.l1.multithreading.task5.exceptions.InsufficientMoneyForExchange;
import com.epam.l1.multithreading.task5.repositories.CurrencyRepository;
import com.epam.l1.multithreading.task5.repositories.ExchangeRateRepository;
import com.epam.l1.multithreading.task5.repositories.UserRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;


public class CurrencyExchangeService{
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    private static final Logger LOGGER = Logger.getLogger(CurrencyExchangeService.class.getName());

    public CurrencyExchangeService(UserRepository userRepository,
                                   CurrencyRepository currencyRepository,
                                   ExchangeRateRepository exchangeRateRepository) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public synchronized boolean exchange(CurrencyExchangeDTO currencyExchangeDTO) throws ExchangeNotSupported {

        try {
            Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository.findByFromAndTo(currencyExchangeDTO.getFrom(), currencyExchangeDTO.getTo(), currencyExchangeDTO.getExchangeRatesFileName());
            ExchangeRate exchangeRate = optionalExchangeRate.orElseThrow();
            List<String> userDataLineByLine = userRepository.readUserData(currencyExchangeDTO.getUserFileName());
            User user = populateUserEntityWithData(userDataLineByLine);
            LOGGER.info("userId: " + user.getId() + " userFirstName: " + user.getFirstName() + " exchanges FROM: " + currencyExchangeDTO.getFrom().getCode() + " TO: " + currencyExchangeDTO.getTo().getCode() + " with rate: " + exchangeRate.getRate());

            exchangeHelper(user, currencyExchangeDTO, exchangeRate.getRate());
        } catch (IOException | ExchangeRateNotFound | InsufficientMoneyForExchange e) {
            e.printStackTrace();
        }
        return true;
    }

    private  void exchangeHelper(User user, CurrencyExchangeDTO currencyExchangeDTO, double rate) throws InsufficientMoneyForExchange {
        Currency from = currencyExchangeDTO.getFrom();
        Currency to = currencyExchangeDTO.getTo();
        BigDecimal toAmount = currencyExchangeDTO.getToAmount();

        BigDecimal fromBalance = user.getBalance().getOrDefault(from, new BigDecimal(0));

        BigDecimal toAmountMultipliedByRate = new BigDecimal(String.valueOf(toAmount.multiply(new BigDecimal(rate))));
        int comparisonResult = fromBalance.compareTo(toAmountMultipliedByRate);

        if (comparisonResult < 0) {
            throw new InsufficientMoneyForExchange("money is insufficient for exchange");
        }

        user.getBalance().put(from, user.getBalance().get(from).subtract(toAmountMultipliedByRate) );
        user.getBalance().put(to, user.getBalance().getOrDefault(to, new BigDecimal(0)).add(toAmountMultipliedByRate));
        LOGGER.info(user.getFirstName() + " " + user.getLastName() + " exchanged FROM " + currencyExchangeDTO.getFrom().getCode() + " amount: " + toAmountMultipliedByRate + " TO " + currencyExchangeDTO.getTo().getCode() + " amount: " + toAmount);
        try {
            userRepository.update(user, currencyExchangeDTO.getUserFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // implementation is specific to the information structured in the file namely user1.txt, user2.txt and so on
    private User populateUserEntityWithData(List<String> userDataLineByLine){
        User user = new User();
        for (int i = 0; i < userDataLineByLine.size(); i++) {
            if ( userDataLineByLine.get(i).equals("metadata_start")) {
                //id:1
                int id = Integer.parseInt(userDataLineByLine.get(++i).split(":")[1]);
                //firstName:John
                String firstname = userDataLineByLine.get(++i).split(":")[1];
                //lastName:Dao
                String lastName = userDataLineByLine.get(++i).split(":")[1];
                //password:12345
                String password = userDataLineByLine.get(++i).split(":")[1];

                user.setId(id);
                user.setFirstName(firstname);
                user.setLastName(lastName);
                user.setPassword(password);
                //metadata_end needs to be skipped
                i++;
            }
            if (userDataLineByLine.get(i).equals("balance_start")){
                i++; // skip balance_start line
                Map<Currency, BigDecimal> balanceMap = new ConcurrentHashMap<>();
                while (!userDataLineByLine.get(i).equals("balance_end")){
                    // USD:3939
                    String balanceLine = userDataLineByLine.get(i++);
                    Currency currency = new Currency(balanceLine.split(":")[0]);
                    BigDecimal value = new BigDecimal(balanceLine.split(":")[1]);
                    balanceMap.put(currency,value);
                }
                user.setBalance(balanceMap);
            }
        }
        return user;
    }
}
