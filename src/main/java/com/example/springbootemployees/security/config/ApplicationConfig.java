package com.example.springbootemployees.security.config;

import com.example.springbootemployees.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.EncryptedPrivateKeyInfo;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailService() {
        return username -> this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }
    // data access object which responsible to fetch
    // userDetails and encoded password ans so on and so furth
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        //which userDetailsService will be used in order to fetch information about our user
        // because we have multiple implementation of userDetails one for user stored in database one for in memory profile one for ldap
        authProvider.setUserDetailsService(userDetailService());
        //which password encoder will be being used
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    // Authenticationmanger is used to use authentication in our program
    // it has multiple implementation one of them to authenticate using username and password
 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
