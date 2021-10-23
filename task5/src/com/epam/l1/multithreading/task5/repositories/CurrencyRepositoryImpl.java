package com.epam.l1.multithreading.task5.repositories;


import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.utils.FileHandlerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    public boolean delete(Currency currency) {
        return false;
    }

    @Override
    public boolean deleteByCode(String code) {
        return false;
    }

    @Override
    public Currency create(Currency currency) {
        return null;
    }

    @Override
    public Currency update(Currency currency) {
        return null;
    }
}
