package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.exceptions.CurrencyAlreadyExists;
import com.epam.l1.multithreading.task5.exceptions.CurrencyNotFound;

import java.io.IOException;
import java.util.Optional;

public interface CurrencyRepository {

    Optional<Currency> findByCode(String code, String fileName) throws IOException;

    boolean delete(Currency currency, String fileName) throws IOException ;

    boolean deleteByCode(String code, String fileName) throws IOException ;

    Currency create(Currency currency, String fileName) throws IOException, CurrencyAlreadyExists;

    boolean update(Currency oldCurrency,Currency newCurrency,  String fileName) throws IOException, CurrencyNotFound, CurrencyAlreadyExists  ;
}
