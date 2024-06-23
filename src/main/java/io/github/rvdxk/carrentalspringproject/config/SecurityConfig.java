package io.github.rvdxk.carrentalspringproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request

                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/authorize/**").permitAll()
                        .requestMatchers("/users/feedbacks").permitAll()

                        .requestMatchers(HttpMethod.GET, ("/cars")).hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, ("/cars/{id}/car-location")).hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, ("/cars/{id}")).hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, ("/cars/{id}/parameters")).hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/users/{id}/customers").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/customers/{id}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/customers/{id}").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/rental/info/{id}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/rental/info").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers("/users/{userId}/feedbacks/**").hasAnyAuthority("USER", "ADMIN")

                        .requestMatchers("/**").hasAnyAuthority("ADMIN")

                        .anyRequest()
                        .authenticated())

                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }

}
