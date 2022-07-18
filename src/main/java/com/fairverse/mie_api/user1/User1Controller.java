package com.fairverse.mie_api.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user1")
public class User1Controller {

    private final User1Service user1Service;

    @Autowired
    public User1Controller(User1Service user1Service) {
        this.user1Service = user1Service;
    }

    @GetMapping
    public List<User1> getUsers() {
        return user1Service.getUsers();
    }

    @PostMapping
    public void registerNewUser1(@RequestBody User1 user1) {
        user1Service.addNewUser1(user1);
    }
}
