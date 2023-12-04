package com.example.finalfinalback3.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String login;
    private String password;

    private String password_confirm;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TodoEntity> todolist;

    public List<TodoEntity> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<TodoEntity> todolist) {
        this.todolist = todolist;
    }

    public UserEntity() {
    }

    public UserEntity(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
