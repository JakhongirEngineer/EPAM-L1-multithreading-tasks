package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(int id);

    Optional<List<User>> findAll();

    User update(User user);

    User create(User user);

    boolean delete(User user);

    boolean deleteById(int id);
}
