package de.miladsa.jsfplayground;

import de.miladsa.jsfplayground.models.User;
import de.miladsa.jsfplayground.repositories.UserRepository;
import jakarta.faces.webapp.FacesServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JsfPlayGroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsfPlayGroundApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> jsfServletRegistration() {
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(List.of("*.xhtml", "*.jsf", "/faces/*"));
        srb.setLoadOnStartup(1);
        srb.addInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        return srb;
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository) {
        return (args -> {
            var user1 = new User();
            user1.setNationalId("5647895214");
            user1.setPhoneNumber("09124568796");
            user1.getRoles().add("USER");
            userRepository.save(user1);
        });
    }
}
