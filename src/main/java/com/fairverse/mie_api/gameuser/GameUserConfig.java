package com.fairverse.mie_api.gameuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameUserConfig {

    @Bean
    CommandLineRunner commandLineRunner(GameUserService gameUserService) {
        return args -> {

        };
    }
}
