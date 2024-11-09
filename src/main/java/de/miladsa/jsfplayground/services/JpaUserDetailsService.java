package de.miladsa.jsfplayground.services;

import de.miladsa.jsfplayground.models.User;
import de.miladsa.jsfplayground.repositories.UserRepository;
import de.miladsa.jsfplayground.security.models.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userData = userRepository.findUserByPhoneNumber(username);
        User user = userData.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new SecurityUser(user);
    }
}
