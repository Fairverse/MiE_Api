package com.fairverse.mie_api.gameuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GameUser implements UserDetails {

    @SequenceGenerator(
            name = "gameuser_sequence",
            sequenceName = "gameuser_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "gameuser_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;

    private int coin = 0;
    @Enumerated(EnumType.STRING)
    private GameUserRole gameUserRole;

    public GameUser(String username, String email, String password, GameUserRole gameUserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gameUserRole = gameUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(gameUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String toStringC() {
        return username + "é" + email + "é" + id + "é" + coin;
    }
}
