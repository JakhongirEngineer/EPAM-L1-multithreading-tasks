package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.ExchangeRate;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateAlreadyExists;
import com.epam.l1.multithreading.task5.exceptions.ExchangeRateNotFound;
import com.epam.l1.multithreading.task5.utils.FileHandlerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository{
    @Override
    public Optional<ExchangeRate> findByFromAndTo(Currency from, Currency to, String fileName) throws IOException, ExchangeRateNotFound {
        List<String> exchangeRates = FileHandlerUtils.readFileLineByLine(fileName);
        //example: from:SUM,to:EUR,rate:0.00009
        String neededLine = null;
        for (String lineInExchangeRateFile : exchangeRates){
            if (lineInExchangeRateFile.contains(from.getCode()) && lineInExchangeRateFile.contains(to.getCode())){
                neededLine = lineInExchangeRateFile;
                break;
            }
        }
        if (neededLine == null){
            throw new ExchangeRateNotFound("given combination of exchange rates NOT FOUND");
        }
        // [from:SUM, to:EUR, rate:0.00009]
        String[] lineSplitByComma = neededLine.split(",");
        String rateInString = lineSplitByComma[2].split(":")[1];
        double rate = Double.parseDouble(rateInString);

        ExchangeRate exchangeRate = new ExchangeRate(from, to, rate);
        return Optional.of(exchangeRate);
    }

    @Override
    public Optional<List<ExchangeRate>> findAll(String fileName) throws IOException {
        List<String> exchangeRatesInTheFile = FileHandlerUtils.readFileLineByLine(fileName);
        // String -> from:SUM,to:EUR,rate:0.00009
        List<ExchangeRate> exchangeRates = exchangeRatesInTheFile.stream()
                .map(line -> convertLineToExchangeRate(line))
                .collect(Collectors.toList());
        return Optional.of(exchangeRates);
    }

    private ExchangeRate convertLineToExchangeRate(String line){
        // line -> from:SUM,to:EUR,rate:0.00009
        String[] splitLine = line.split(",");

        Currency from = new Currency(splitLine[0].split(":")[1]);
        Currency to = new Currency(splitLine[1].split(":")[1]);
        double rate = Double.parseDouble(splitLine[2].split(":")[1]);

        return new ExchangeRate(from,to,rate);
    }

    @Override
    public ExchangeRate create(ExchangeRate exchangeRate, String fileName) throws IOException, ExchangeRateAlreadyExists {
        String fromCode = exchangeRate.getFrom().getCode();
        String toCode = exchangeRate.getTo().getCode();
        double rate = exchangeRate.getRate();

        List<String> exchangeRatesInFile = FileHandlerUtils.readFileLineByLine(fileName);

        for (String line : exchangeRatesInFile){
            if (line.contains(fromCode) && line.contains(toCode)){
                throw new ExchangeRateAlreadyExists("exchange rate already exists");
            }
        }
        // from:code,to:code,rate:double
        exchangeRatesInFile.add("from:" + fromCode + ",to:" + toCode + ",rate:" + rate);
        FileHandlerUtils.overrideFileContent(fileName,exchangeRatesInFile);
        return exchangeRate;
    }
}
