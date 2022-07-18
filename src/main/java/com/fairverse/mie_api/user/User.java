package com.fairverse.mie_api.user;

public class User {
    private Long id;
    private String mail;
    private String name;
    private String surname;
    private String password;

    public User() {
    }

    public User(Long id, String mail, String name, String surname, String password) {
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public User(String mail, String name, String surname, String password) {
        this.mail = mail;
        this.name = name;
        this.surname = surname;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
