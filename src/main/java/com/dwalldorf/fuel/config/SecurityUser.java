package com.dwalldorf.fuel.config;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User implements Serializable {

    /**
     * User id
     */
    private final Long id;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(
                username,
                password,
                true,
                true,
                true,
                true,
                authorities
        );
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}