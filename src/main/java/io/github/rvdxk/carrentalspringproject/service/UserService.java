package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long userId);

}