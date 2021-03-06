package nbu.medicaljournal.service;

import nbu.medicaljournal.api.exception.ResourceNotFoundException;
import nbu.medicaljournal.api.model.User;
import nbu.medicaljournal.api.request.NewUserRequest;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.UserEntity;
import nbu.medicaljournal.repo.DoctorRepo;
import nbu.medicaljournal.repo.PatientRepo;
import nbu.medicaljournal.repo.UserRepo;
import nbu.medicaljournal.security.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("No user with the provided username exists!"));

        return new UserPrincipal(user);
    }

    public List<User> getUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserEntity::toUser)
                .collect(Collectors.toList());
    }

    public User getUser(String id) throws ResourceNotFoundException {
        return getUserEntity(id).toUser();
    }

    public User addUser(NewUserRequest userRequest) throws ResourceNotFoundException {
        DoctorEntity doctor = null;
        PatientEntity patient = null;

        if (StringUtils.isNotBlank(userRequest.doctorUin)) {
            doctor = getDoctorEntity(userRequest.doctorUin);
        }
        if (StringUtils.isNotBlank(userRequest.patientEgn)) {
            patient = getPatientEntity(userRequest.patientEgn);
        }

        User user = new User(null, userRequest.username, encoder.encode(userRequest.password), userRequest.type,
                userRequest.doctorUin, userRequest.patientEgn);
        UserEntity userEntity = userRepo.save(user, doctor, patient);

        return userEntity.toUser();
    }

    public void deleteUser(String id) throws ResourceNotFoundException {
        UserEntity user = getUserEntity(id);
        userRepo.delete(id);
    }

    private UserEntity getUserEntity(String id) throws ResourceNotFoundException {
        return userRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No user with the provided id exists!"));
    }

    private PatientEntity getPatientEntity(String id) throws ResourceNotFoundException {
        return patientRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No patient with the provided id exists!"));
    }

    private DoctorEntity getDoctorEntity(String doctorId) throws ResourceNotFoundException {
        return doctorRepo.find(doctorId).orElseThrow(() ->
                new ResourceNotFoundException("No doctor with the provided id exists!"));
    }
}