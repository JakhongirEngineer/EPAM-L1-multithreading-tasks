package com.epam.l1.multithreading.task5.repositories;


import com.epam.l1.multithreading.task5.entities.Currency;

import java.util.Optional;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Override
    public Optional<Currency> findByCode(String code) {
        return Optional.empty();
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
