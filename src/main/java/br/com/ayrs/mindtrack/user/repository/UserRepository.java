package br.com.ayrs.mindtrack.user.repository;

import br.com.ayrs.mindtrack.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
