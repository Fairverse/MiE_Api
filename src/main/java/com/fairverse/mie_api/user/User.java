package com.fairverse.mie_api.user;

public class User {
    private Long id;
    private String mail;
    private String username;
    private String password;

    public User() {
    }

    public User(Long id, String mail, String username, String password) {
        this.id = id;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public User(String mail, String username, String password) {
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
}
