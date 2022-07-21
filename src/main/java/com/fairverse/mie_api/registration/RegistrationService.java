package com.fairverse.mie_api.registration;

import com.fairverse.mie_api.gameuser.GameUser;
import com.fairverse.mie_api.gameuser.GameUserRole;
import com.fairverse.mie_api.gameuser.GameUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final GameUserService gameUserService;

    public String register(String gameUserStr) {
        GameUser gameUser = convertStrToGameUser(gameUserStr);
        if (!emailValidator.test(gameUser.getEmail())) {
            return "Email geçerli değil!";
        }
        return gameUserService.register(gameUser);
    }

    private GameUser convertStrToGameUser(String gameUserStr) {
        String[] gameUserSplit = gameUserStr.split("é");
        return new GameUser(gameUserSplit[0], gameUserSplit[1], gameUserSplit[2], GameUserRole.USER);
    }
}
