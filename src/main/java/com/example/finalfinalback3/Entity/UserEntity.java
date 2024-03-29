package com.example.finalfinalback3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserEntity{// implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String login;
    private String password;
    private String password_confirm;
    //@Column(columnDefinition = "varchar(255) default 'ROLE_USER'")
    private String role;
    private String token;

    @ManyToMany
    @JoinTable(name="user_favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    private List<TourEntity> favorites;

    @OneToMany
    private List<TripEntity> history;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DocumentEntity> doc;

    @OneToMany
    private List<TourEntity> traceble;

    //TODO Добавить картинку пользователя

    //@Enumerated(EnumType.STRING)
    //private RoleEnum role;

    //for tests
    public UserEntity(Integer id, String email, String login, String password) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public UserEntity(Integer id, String email, String login, String password, String token) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.token = token;
    }

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
