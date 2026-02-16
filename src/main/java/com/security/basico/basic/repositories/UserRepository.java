package com.security.basico.basic.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.security.basico.basic.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
