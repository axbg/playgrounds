package com.example.oauthjwttest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("!client")
@Configuration
public class OAuthResourceServerSecurityConfig {
    KeycloakJwtRolesConverter authoritiesConverter = new KeycloakJwtRolesConverter();

    @Bean
    public SecurityFilterChain resourceServerChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());

        http.authorizeHttpRequests(requests -> {
                    requests.requestMatchers("/customer**")
                    .hasAuthority(KeycloakJwtRolesConverter.PREFIX_REALM_ROLE + "user")
                    .anyRequest()
                    .permitAll();
        }).oauth2ResourceServer(oauth2 -> {
           oauth2.jwt(jwt -> {
              jwt.jwtAuthenticationConverter(jconv -> new CustomAuthToken(jconv, authoritiesConverter.convert(jconv)));
           });
        });
        return http.build();
    }
}
