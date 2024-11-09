package de.miladsa.jsfplayground.repositories;

import de.miladsa.jsfplayground.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
