package de.miladsa.jsfplayground.configurations;

import de.miladsa.jsfplayground.models.User;
import de.miladsa.jsfplayground.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneNationalIdAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PhoneNationalIdAuthenticationProvider(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());
        PhoneNationalIdAuthenticationToken token = (PhoneNationalIdAuthenticationToken) authentication;

        String phoneNumber = token.getPrincipal().toString();
        String nationalId = token.getCredentials().toString();

        User user = userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new BadCredentialsException("Invalid phone number or national ID"));

        if (!passwordEncoder.matches(nationalId, user.getNationalId())) {
            throw new BadCredentialsException("Invalid phone number or national ID");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new PhoneNationalIdAuthenticationToken(authorities, phoneNumber, nationalId);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneNationalIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
