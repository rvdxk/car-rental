package io.github.rvdxk.carrentalspringproject.auth;

import io.github.rvdxk.carrentalspringproject.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authorize")
public class AuthenticationController {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private final AuthenticationService service;

    @PostMapping("/register/user")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
