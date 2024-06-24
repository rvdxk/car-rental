package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import io.github.rvdxk.carrentalspringproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));

        userRepository.deleteById(userId);
    }
}

