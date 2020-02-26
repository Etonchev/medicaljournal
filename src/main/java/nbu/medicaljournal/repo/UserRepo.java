package nbu.medicaljournal.repo;

import nbu.medicaljournal.api.model.User;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.UserEntity;
import nbu.medicaljournal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> find(String id) {
        return userRepository.findById(id);
    }

    public UserEntity save(User user, DoctorEntity doctor, PatientEntity patient) {
        return userRepository.save(new UserEntity(user, doctor, patient));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}