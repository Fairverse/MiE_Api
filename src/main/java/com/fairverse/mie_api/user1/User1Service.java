package com.fairverse.mie_api.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User1Service {

    private final User1Repository user1Repository;

    @Autowired
    public User1Service(User1Repository user1Repository) {
        this.user1Repository = user1Repository;
    }

    public List<User1> getUsers() {
        return user1Repository.findAll();
    }

    public void addNewUser1(User1 user1) {
        System.out.println(user1);
    }
}
