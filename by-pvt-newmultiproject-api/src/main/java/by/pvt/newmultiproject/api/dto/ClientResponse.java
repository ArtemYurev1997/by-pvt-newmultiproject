package by.pvt.newmultiproject.api.dto;

import by.pvt.newmultiproject.api.enums.Roles;

import java.io.Serializable;

public class ClientResponse implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String fullname;
    private Roles role;
    static Long nextId = 1L;

    public ClientResponse(String name, String surname, String login, String fullname, Roles role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.fullname = fullname;
        this.role = role;
    }

    public ClientResponse() {
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ClientResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", fullname='" + fullname + '\'' +
                ", role=" + role +
                '}';
    }
}
