package com.epam.l1.multithreading.task5.repositories;


import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.exceptions.CurrencyAlreadyExists;
import com.epam.l1.multithreading.task5.exceptions.CurrencyNotFound;
import com.epam.l1.multithreading.task5.utils.FileHandlerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    @Override
    public Optional<Currency> findByCode(String code, String fileName) throws IOException {
        List<String> currencies = FileHandlerUtils.readFileLineByLine(fileName);
        Optional<Currency> currency = Optional.empty();
        for (String line : currencies){
            if (line.equals(code)){
                currency = Optional.of(new Currency(line));
                break;
            }
        }
        return currency;
    }

    @Override
    public boolean delete(Currency currency, String fileName) throws IOException  {
        List<String> currencies = FileHandlerUtils.readFileLineByLine(fileName);
        List<String> updatedCurrencies = currencies.stream()
                                            .filter(line -> !line.equals(currency.getCode()))
                                            .collect(Collectors.toList());
        FileHandlerUtils.overrideFileContent(fileName, updatedCurrencies);
        return true;
    }

    @Override
    public boolean deleteByCode(String code,String fileName) throws IOException {
        return delete(new Currency(code), fileName);
    }

    @Override
    public Currency create(Currency currency, String fileName) throws IOException, CurrencyAlreadyExists {
        List<String> currencies = FileHandlerUtils.readFileLineByLine(fileName);
        for (String curr : currencies){
            if (currency.getCode().equals(curr)){
                throw new CurrencyAlreadyExists(currency.getCode() + " has already created.");
            }
        }

        currencies.add(currency.getCode());
        FileHandlerUtils.overrideFileContent(fileName, currencies);
        return currency;
    }

    @Override
    public boolean update(Currency oldCurrency,Currency newCurrency,  String fileName) throws IOException, CurrencyNotFound, CurrencyAlreadyExists {
        List<String> currencies = FileHandlerUtils.readFileLineByLine(fileName);
        List<String> oldCurrencyInTheFile = currencies.stream().filter(curr -> curr.equals(oldCurrency.getCode())).collect(Collectors.toList());
        List<String> newCurrencyInTheFile = currencies.stream().filter(curr -> curr.equals(newCurrency.getCode())).collect(Collectors.toList());

        if (oldCurrencyInTheFile.size() == 0 ) {
            throw new CurrencyNotFound(oldCurrency.getCode() + " is not found");
        }

        if (newCurrencyInTheFile.size() > 0) {
            throw new CurrencyAlreadyExists(newCurrency.getCode() + " is already exists");
        }

        for (int i = 0; i < currencies.size(); i++){
            if (currencies.get(i).equals(oldCurrency.getCode())){
                currencies.set(i, newCurrency.getCode());
                break;
            }
        }

        FileHandlerUtils.overrideFileContent(fileName, currencies);
        return true;
    }
}
