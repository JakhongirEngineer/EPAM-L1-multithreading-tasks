package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateAlreadyExists;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateNotFound;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> findByFromAndTo(Currency from, Currency to, String fileName) throws IOException, ExchangeRateNotFound;

    Optional<List<ExchangeRate>> findAll(String fileName) throws IOException;

    ExchangeRate create(ExchangeRate exchangeRate, String fileName) throws IOException, ExchangeRateAlreadyExists;
}
