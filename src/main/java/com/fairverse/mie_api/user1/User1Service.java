package com.fairverse.mie_api.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

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
        Optional<User1> user1Optional = user1Repository.findUser1ByMail(user1.getMail());
        if (user1Optional.isPresent()) {
            throw new IllegalStateException("Bu mail daha önce alınmış!");
        }

        user1.setPassword(hashPassword(user1.getPassword()));
        user1Repository.save(user1);
    }

    private String hashPassword(String password) {
        try {
            byte[] encodedhash = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}