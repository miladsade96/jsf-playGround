package de.miladsa.jsfplayground.configurations;

import de.miladsa.jsfplayground.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PhoneNationalIdAuthenticationProvider authenticationProvider(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return new PhoneNationalIdAuthenticationProvider(userRepository, passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, PhoneNationalIdAuthenticationProvider phoneNationalIdAuthenticationProvider) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authenticationProvider(phoneNationalIdAuthenticationProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/javax.faces.resource/**").permitAll()
                        .requestMatchers("/login.xhtml").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login.xhtml")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home.xhtml", true)
                        .failureUrl("/login.xhtml?error=true")
                )
                // Remove STATELESS session management for JSF
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/login.xhtml")
                        .maximumSessions(1)
                        .expiredUrl("/login.xhtml?expired=true")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login.xhtml")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
                // Add exception handling
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendRedirect(request.getContextPath() + "/login.xhtml"))
                );

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
