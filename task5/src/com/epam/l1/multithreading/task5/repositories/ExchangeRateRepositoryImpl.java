package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;

import java.util.List;
import java.util.Optional;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository{
    @Override
    public Optional<ExchangeRate> findByFromAndTo(Currency from, Currency to) {
        return Optional.empty();
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
