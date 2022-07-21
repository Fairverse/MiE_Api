package com.fairverse.mie_api.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PutMapping
    public String register(@RequestBody String gameUserStr) {
        return registrationService.register(gameUserStr);
    }

    @GetMapping
    @ResponseBody
    public String getGameUser(@RequestParam Map<String, String> body) {
        return registrationService.getGameUser(body);
    }
}
