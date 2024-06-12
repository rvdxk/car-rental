package io.github.rvdxk.carrentalspringproject.service;

import io.github.rvdxk.carrentalspringproject.dto.UserDto;
import io.github.rvdxk.carrentalspringproject.entity.User;

import java.util.List;

public interface UserService {
    void addUser(UserDto userDto);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    User getUserById(Long id);
    List<UserDto> getAllUsers();


}
