package com.fairverse.mie_api.user1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class User1Config {

    @Bean
    CommandLineRunner commandLineRunner(User1Repository user1Repository) {
        return args -> {
            User1 hakkican = new User1("hkcblc@gmail.com", "Mr. Bülüç", "123456");
            User1 reyhan = new User1("reyhan@gmail.com", "MissReyyo", "123456789");

            user1Repository.saveAll(
                    List.of(hakkican, reyhan)
            );
        };
    }
}
