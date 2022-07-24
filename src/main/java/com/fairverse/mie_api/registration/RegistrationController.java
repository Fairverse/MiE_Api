package com.fairverse.mie_api.registration;

import com.fairverse.mie_api.gameuser.GameUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(path = "getAllUsers")
    public List<GameUser> getAllUsers() {
        return registrationService.getAllUser();
    }
}
