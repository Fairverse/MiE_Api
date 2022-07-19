package com.fairverse.mie_api.user1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class User1Config {

    @Bean
    CommandLineRunner commandLineRunner(User1Service user1Service) {
        return args -> {

        };
    }
}
