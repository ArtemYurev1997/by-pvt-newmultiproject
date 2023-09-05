package by.pvt.newmultiproject.core.domain;

import by.pvt.newmultiproject.api.enums.Roles;

import java.io.Serializable;

public class Client implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Roles role;
    static Long nextId = 1L;

    public Client(String name, String surname, String login, String password, Roles role) {
        id = Long.valueOf(0);
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Client(Long id, String name, String surname, String login, String password, Roles role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        id = nextId;
        nextId++;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
