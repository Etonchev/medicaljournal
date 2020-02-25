package nbu.medicaljournal.repository;

import nbu.medicaljournal.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity > findByUsername(String username);
}