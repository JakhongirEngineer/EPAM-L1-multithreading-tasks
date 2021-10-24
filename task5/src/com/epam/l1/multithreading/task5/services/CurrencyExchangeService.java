package com.epam.l1.multithreading.task5.services;

import com.epam.l1.multithreading.task5.dtos.CurrencyExchangeDTO;
import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;
import com.epam.l1.multithreading.task5.entities.User;
import com.epam.l1.multithreading.task5.exceptions.ExchangeNotSupported;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateNotFound;
import com.epam.l1.multithreading.task5.repositories.CurrencyRepository;
import com.epam.l1.multithreading.task5.repositories.ExchangeRateRepository;
import com.epam.l1.multithreading.task5.repositories.UserRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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

    public synchronized boolean exchange(CurrencyExchangeDTO currencyExchangeDTO) throws ExchangeNotSupported {

        try {
            List<String> userDataLineByLine = userRepository.readUserData(currencyExchangeDTO.getUserFileName());
            User user = populateUserEntityWithData(userDataLineByLine);

            Optional<ExchangeRate> optionalExchangeRate = exchangeRateRepository.findByFromAndTo(currencyExchangeDTO.getFrom(), currencyExchangeDTO.getTo(), currencyExchangeDTO.getCurrencyFileName());



        } catch (IOException | ExchangeRateNotFound e) {
            e.printStackTrace();
        }
        return true;
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
