package com.example.finalfinalback3.Model;

import com.example.finalfinalback3.Entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    private String login;
    private List<Todo> todolist;
    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setTodolist(entity.getTodolist()
                                .stream()
                                .map(Todo::toModel)
                                .collect(Collectors.toList()));
        return model;

    }

    public User() {
    }

    public List<Todo> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<Todo> todolist) {
        this.todolist = todolist;
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
}
