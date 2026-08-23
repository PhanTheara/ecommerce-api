package com.istad.theara.ecommerce_api.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class securityConfig {



    @Bean
    public SecurityFilterChain doFilterChain(HttpSecurity http){

        // 2. Endpoint policy (public/protected endpoint)
        http.authorizeHttpRequests(
                request -> request
                        .requestMatchers(
                                "/swagger-ui.html/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/scalar/**",
                                "/api/v1/auth/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").hasAllAuthorities("category:read")
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasAnyAuthority("STAFF", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasAnyAuthority("STAFF", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/categories/**").hasAnyAuthority("STAFF", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
        );

       // http.httpBasic(Customizer.withDefaults());
        http.oauth2ResourceServer(
                oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
        );

        // 4. Disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> converter = jwt -> {
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = new HashSet<>();
            if (realmAccess != null) {
                roles = realmAccess.get("roles");
            }
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return jwtAuthenticationConverter;
    }
}
