package com.istad.theara.ecommerce_api.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class inMemoryUserDetailConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager configurationUserDetail(PasswordEncoder passwordEncoder){
        InMemoryUserDetailsManager manager = new  InMemoryUserDetailsManager();

        UserDetails customerDetail = User.withUsername("customer").password(passwordEncoder.encode("123456")).roles("CUSTOMER").build();
       manager.createUser(customerDetail);
        return manager;
    }

}
