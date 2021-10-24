package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.Currency;
import com.epam.l1.multithreading.task5.entities.User;
import com.epam.l1.multithreading.task5.exceptions.UnableToCreateFile;
import com.epam.l1.multithreading.task5.utils.FileHandlerUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserRepositoryImpl implements UserRepository{

    @Override
    public User create(User user, String fileName) throws IOException, UnableToCreateFile {
       List<String> lines = populateListWithUser(user);

       FileHandlerUtils.createNewFile(lines,fileName);

       return user;
    }

    @Override
    public List<String> readUserData(String fileName) throws IOException {
        return FileHandlerUtils.readFileLineByLine(fileName);
    }

    @Override
    public boolean update(User user, String fileName) throws IOException {
        List<String> newContent = populateListWithUser(user);
        FileHandlerUtils.overrideFileContent(fileName, newContent);
        return true;
    }

    @Override
    public boolean delete(String fileName) throws IOException {
       return FileHandlerUtils.deleteFile(fileName);
    }

    private List<String> populateListWithUser(User user){
        List<String> lines = new ArrayList<>();
        lines.add("user_start");
        lines.add("metadata_start");
        lines.add("id:" + user.getId());
        lines.add("firstName:" + user.getFirstName());
        lines.add("lastName:" + user.getLastName());
        lines.add("password:" + user.getPassword());
        lines.add("metadata_end");
        lines.add("balance_start");

        for (Map.Entry<Currency, BigDecimal> currencyBigDecimalEntry : user.getBalance().entrySet()) {
            lines.add(currencyBigDecimalEntry.getKey().getCode() + ":" + currencyBigDecimalEntry.getValue());
        }
        lines.add("balance_end");
        lines.add("user_end");
        return lines;
    }
}
