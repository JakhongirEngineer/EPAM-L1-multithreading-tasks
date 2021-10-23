package com.epam.l1.multithreading.task5.repositories;

import com.epam.l1.multithreading.task5.entities.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
