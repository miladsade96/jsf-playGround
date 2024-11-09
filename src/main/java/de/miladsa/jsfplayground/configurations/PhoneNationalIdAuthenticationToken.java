package de.miladsa.jsfplayground.configurations;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class PhoneNationalIdAuthenticationToken extends AbstractAuthenticationToken {
    private final String phoneNumber;
    private final String nationalId;

    // Constructor for unauthenticated token
    public PhoneNationalIdAuthenticationToken(String phoneNumber, String nationalId) {
        super(Collections.emptyList());
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        setAuthenticated(false);
    }

    // Constructor for authenticated token
    public PhoneNationalIdAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                              String phoneNumber, String nationalId) {
        super(authorities);
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return nationalId;
    }

    @Override
    public Object getPrincipal() {
        return phoneNumber;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes GrantedAuthorities instead");
        }
        super.setAuthenticated(false);
    }
}
