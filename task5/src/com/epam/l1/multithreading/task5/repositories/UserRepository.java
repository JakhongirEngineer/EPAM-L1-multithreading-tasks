package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.User;
import com.epam.l1.multithreading.task5.exceptions.UnableToCreateFile;

import java.io.IOException;
import java.util.List;

public interface UserRepository {

    User create(User user, String fileName) throws IOException, UnableToCreateFile;

    List<String> readUserData(String fileName) throws IOException;

    boolean update(User user, String fileName) throws IOException;

    boolean delete(String fileName) throws IOException;

}
