package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> findByFromAndTo(Currency from, Currency to);

    Optional<List<ExchangeRate>> findAll();

    boolean deleteByFromAndTo(Currency from, Currency to);

    ExchangeRate create(ExchangeRate exchangeRate);

    ExchangeRate update(ExchangeRate exchangeRate);

}
