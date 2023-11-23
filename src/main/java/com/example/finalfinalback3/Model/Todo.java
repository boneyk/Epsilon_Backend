package com.example.finalfinalback3.Model;

import com.example.finalfinalback3.Entity.TodoEntity;

public class Todo {
    private Integer id;
    private String title;
    private String description;
    private Boolean is_completed;

    public static Todo toModel(TodoEntity todo) {
        Todo model = new Todo();

        model.setId(todo.getId());
        model.setTitle(todo.getTitle());
        model.setDescription(todo.getDescription());
        model.setIs_completed(todo.getIs_completed());

        return model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(Boolean is_completed) {
        this.is_completed = is_completed;
    }
}
