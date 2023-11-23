package com.example.finalfinalback3.Entity;

import jakarta.persistence.*;

@Entity
public class TodoEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Boolean is_completed;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    public TodoEntity() {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
