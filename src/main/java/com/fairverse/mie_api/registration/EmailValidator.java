package com.fairverse.mie_api.registration;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Configuration
public class EmailValidator  implements Predicate<String> {

    @Override
    public boolean test(String email) {
        return Pattern.compile("^(.+)@(.+)$").matcher(email).find();
    }
}
