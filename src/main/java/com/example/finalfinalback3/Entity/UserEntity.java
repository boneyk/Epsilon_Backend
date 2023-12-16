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
@Entity
public class UserEntity{// implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String login;
    private String password;
    private String password_confirm;
    //TODO Добавить картинку пользователя
    //@Enumerated(EnumType.STRING)
    //private RoleEnum role;


   /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        //return role.getAuthorities();
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
*/
}
