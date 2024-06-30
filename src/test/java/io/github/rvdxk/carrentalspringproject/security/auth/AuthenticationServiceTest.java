package io.github.rvdxk.carrentalspringproject.security.auth;

import io.github.rvdxk.carrentalspringproject.entity.User;
import io.github.rvdxk.carrentalspringproject.repository.UserRepository;
import io.github.rvdxk.carrentalspringproject.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authManager;

    @InjectMocks
    private AuthenticationService authService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        user2 = User.builder()
                .id(1L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .password("password")
                .build();


    }
    @Test
    void registerUser_ShouldRegisterUserWithRoleUserAndGenerateToken() {

        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com", "password1");

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user1);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt_token");

        AuthenticationResponse response = authService.registerUser(request);

        assertNotNull(response);
        assertEquals("jwt_token", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerAdmin_ShouldRegisterAdminWithRoleAdminAndGenerateToken() {
        RegisterRequest request = new RegisterRequest("Jane", "Doe", "jane.doe@example.com", "password2");

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user2);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwt_token");

        AuthenticationResponse response = authService.registerUser(request);

        assertNotNull(response);
        assertEquals("jwt_token", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void login_ShouldAuthenticateUsersAndGenerateNewTokenForThem() {
    AuthenticationRequest request = new AuthenticationRequest("john.doe@example.com", "password1");

    when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user1));
    when(jwtService.generateToken(user1)).thenReturn("jwt_token");

    AuthenticationResponse response = authService.login(request);

    assertNotNull(response);
    assertEquals("jwt_token", response.getToken());
    verify(authManager, times(1)).authenticate(any());
    }
}

