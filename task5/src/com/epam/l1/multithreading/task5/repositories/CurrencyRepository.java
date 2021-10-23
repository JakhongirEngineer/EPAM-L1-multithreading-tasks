package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;

import java.util.Optional;

public interface CurrencyRepository {

    Optional<Currency> findByCode(String code);

    boolean delete(Currency currency);

    boolean deleteByCode(String code);

    Currency create(Currency currency);

    Currency update(Currency currency);
}
