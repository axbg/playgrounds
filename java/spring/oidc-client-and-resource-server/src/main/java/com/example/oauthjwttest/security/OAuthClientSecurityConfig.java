package com.example.oauthjwttest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("client")
@Configuration
public class OAuthClientSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests) -> {
            requests
                    .requestMatchers( "/")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
        }).oauth2Login(Customizer.withDefaults());

        return http.build();
    }
}
