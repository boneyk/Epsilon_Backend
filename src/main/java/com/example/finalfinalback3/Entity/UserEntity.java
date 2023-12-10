package com.example.finalfinalback3.Entity;

import com.example.finalfinalback3.Enum.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    private String login;
    private String password;

    private String password_confirm;
    //@Enumerated(EnumType.STRING)
    //private RoleEnum role;

    public UserEntity(String email, String login, String password, String password_confirm) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.password_confirm = password_confirm;
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        //return role.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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
