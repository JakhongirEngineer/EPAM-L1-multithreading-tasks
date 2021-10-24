package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;
import com.epam.l1.multithreading.task5.utils.FileHandlerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository{
    @Override
    public Optional<ExchangeRate> findByFromAndTo(Currency from, Currency to, String fileName) throws IOException {
        List<String> exchangeRates = FileHandlerUtils.readFileLineByLine(fileName);



    }

    @Override
    public Optional<List<ExchangeRate>> findAll() {
        return Optional.empty();
    }

    @Override
    public boolean deleteByFromAndTo(Currency from, Currency to) {
        return false;
    }

    @Override
    public ExchangeRate create(ExchangeRate exchangeRate) {
        return null;
    }

    @Override
    public ExchangeRate update(ExchangeRate exchangeRate) {
        return null;
    }
}
