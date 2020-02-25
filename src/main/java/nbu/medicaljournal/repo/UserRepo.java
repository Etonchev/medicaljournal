package nbu.medicaljournal.repo;

import nbu.medicaljournal.model.UserEntity;
import nbu.medicaljournal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserRepo {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserEntity> find(String username) {
        return userRepository.findByUsername(username);
    }
}