package com.fairverse.mie_api.registration;

import com.fairverse.mie_api.gameuser.GameUser;
import com.fairverse.mie_api.gameuser.GameUserRole;
import com.fairverse.mie_api.gameuser.GameUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final GameUserService gameUserService;

    public String register(String gameUserStr) {
        GameUser gameUser = convertStrToGameUser(gameUserStr);
        return gameUserService.register(gameUser);
    }

    private GameUser convertStrToGameUser(String gameUserStr) {
        String[] gameUserSplit = gameUserStr.split("é");
        return new GameUser(gameUserSplit[0], gameUserSplit[1], gameUserSplit[2], GameUserRole.USER);
    }

    public String getGameUser(Map<String, String> body) {
        if (body.containsKey("email") && body.containsKey("password")) {
            return gameUserService.getUser(body);
        } else {
            throw new IllegalStateException("RequestBody missing fields!");
        }
    }

    public List<GameUser> getAllUser() {
        return gameUserService.getUsers();
    }
}
