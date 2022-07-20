package com.fairverse.mie_api.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/user1")
public class User1Controller {

    private final User1Service user1Service;

    @Autowired
    public User1Controller(User1Service user1Service) {
        this.user1Service = user1Service;
    }

    public List<User1> getUsers() {
        return user1Service.getUsers();
    }

    @GetMapping
    @ResponseBody
    public String getUser(@RequestParam Map<String, String> body) {
        return user1Service.getUser(body);
    }

    @PutMapping
    public void registerNewUser1(@RequestBody String user1Str) {
        user1Service.addNewUser1(user1Str);
    }
}
