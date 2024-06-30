package io.github.rvdxk.carrentalspringproject.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtService jwtService;
    private Key signingKey;

    @BeforeEach
    void setUp() {
        String secretKey = "722b3034632a6835202c5f78385540662b7a21636e394c5b514268593c";

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Test
    void extractUsername_ShouldReturnCorrectUsername() {
        String token = Jwts.builder()
                .setSubject("user")
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();

        String username = jwtService.extractUsername(token);

        assertEquals("user", username);
    }

    @Test
    void extractClaim_ShouldReturnCorrectClaim() {
        String token = Jwts.builder()
                .setSubject("user")
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();

        String subject = jwtService.extractClaim(token, Claims::getSubject);

        assertEquals("user", subject);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        when(userDetails.getUsername()).thenReturn("user");

        String generatedToken = jwtService.generateToken(userDetails);

        assertNotNull(generatedToken);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(generatedToken)
                .getBody();

        assertNotNull(generatedToken);

        assertEquals("user", claims.getSubject());
    }

    @Test
    void isTokenValid_ShouldReturnTrueForValidToken() {

        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtService.generateToken(userDetails);

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenValid_ShouldReturnFalseForInvalidToken() {
        String token = Jwts.builder()
                .setSubject("other_user")
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();


        when(userDetails.getUsername()).thenReturn("user");

        assertFalse(jwtService.isTokenValid(token, userDetails));
    }
}