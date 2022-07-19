package com.fairverse.mie_api.user1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
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

    public Map<String, Object> getUser(Map<String, String> body) {
        if (body.containsKey("mail") && body.containsKey("password")) {
            Optional<User1> user1Optional = user1Repository.findUser1ByMail(body.get("mail"));
            if (user1Optional.isPresent()) {
                User1 user1 = user1Optional.get();
                String hashedPassword = hashPassword(body.get("password"));
                if (user1.getPassword().equals(hashedPassword)) {
                    return user1.toMap();
                } else {
                    throw new IllegalStateException("Parola hatalı!");
                }
            } else {
                throw new IllegalStateException("Bu email adresi ile kullanıcı bulunamadı!");
            }
        } else {
            throw new IllegalStateException("RequestBody missing fields!");
        }
    }

    public void addNewUser1(String user1Str) {
        User1 user1 = convertStrToUser1(user1Str);
        Optional<User1> user1Optional = user1Repository.findUser1ByMail(user1.getMail());
        if (user1Optional.isPresent()) {
            throw new IllegalStateException("Bu mail daha önce alınmış!");
        }
        user1Optional = user1Repository.findUser1ByUsername(user1.getUsername());
        if (user1Optional.isPresent()) {
            throw new IllegalStateException("Bu username daha önce alınmış!");
        }
        user1.setPassword(hashPassword(user1.getPassword()));
        user1Repository.save(user1);
    }

    private User1 convertStrToUser1(String user1Str) {
        String[] user1Split = user1Str.split("é");
        return new User1(user1Split[1], user1Split[0], user1Split[2]);
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
