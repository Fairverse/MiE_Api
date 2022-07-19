package com.fairverse.mie_api.user1;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table
public class User1 {
    @Id
    @SequenceGenerator(
            name = "user1_sequence",
            sequenceName = "user1_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user1_sequence"
    )
    private Long id;
    private String mail;
    private String username;
    private String password;

    public User1() {
    }

    public User1(Long id, String mail, String username, String password) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public User1(String mail, String username, String password) {
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("mail", mail);
        map.put("username", username);
        return map;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
