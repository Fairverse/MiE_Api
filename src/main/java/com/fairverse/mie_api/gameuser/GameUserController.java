package com.fairverse.mie_api.gameuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/gameuser")
public class GameUserController {

    private final GameUserService gameUserService;

    @Autowired
    public GameUserController(GameUserService gameUserService) {
        this.gameUserService = gameUserService;
    }

    public List<GameUser> getUsers() {
        return gameUserService.getUsers();
    }
}
