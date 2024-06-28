package io.github.rvdxk.carrentalspringproject.service.impl;

import io.github.rvdxk.carrentalspringproject.entity.Role;
import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.exception.ResourceNotFoundException;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = User.builder()
                .id(1L)
                .firstName("admin1")
                .lastName("admin1")
                .email("admin1@admin1.com")
                .password("admin1")
                .role(Role.ADMIN)
                .build();

        user2 = User.builder()
                .id(1L)
                .firstName("admin2")
                .lastName("admin2")
                .email("admin2@admin2.com")
                .password("admin2")
                .role(Role.ADMIN)
                .build();


    }

    @Test
    void findUserById_ShouldReturnUserFoundById() {

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        User result = userService.findUserById(user1.getId());

        assertNotNull(result);

        assertEquals(user1.getId(), result.getId());
        assertEquals(user1.getFirstName(), result.getFirstName());
        assertEquals(user1.getLastName(), result.getLastName());
        assertEquals(user1.getEmail(), result.getEmail());

    }

    @Test
    void findUserById_ShouldReturnResourceNotFoundExceptionMessage() {

        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findUserById(user1.getId());
        });
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {

        List<User> userList = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }

    @Test
    void deleteUser_ShouldDeleteUserWithGivenId() {

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        userService.deleteUser(user1.getId());

        verify(userRepository, times(1)).deleteById(user1.getId());
    }
}