package de.miladsa.jsfplayground.security.models;

import de.miladsa.jsfplayground.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {
    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return user.getNationalId();
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }
}
