package com.example.springbootemployees.security.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    // this bean will be scanned at the startup for configuring http security of our application
    // to make the filter chain used and make binding of our implementation in config package.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                 http
                     .csrf(csrf -> csrf.disable())
                     .authorizeHttpRequests( auth -> auth

                             .requestMatchers(
                                     "/api/v1/auth/**",
                                     "/v2/api-docs",
                                     "/v3/api-docs",
                                     "/v3/api-docs/**",
                                     "/swagger-resources",
                                     "/swagger-resources/**",
                                     "/webjars/**",
                                     "/configuration/security",
                                     "/configuration/ui",
                                     "/swagger-ui.html",
                                     "/swagger-ui/**"
                                     ).permitAll()
                             .anyRequest().authenticated()
                     )
                     .sessionManagement(sess -> sess
                             .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                     .authenticationProvider(authenticationProvider)
                     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
