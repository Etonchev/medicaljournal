package nbu.medicaljournal.repo;

import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepo {
    @Autowired
    DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<DoctorEntity> all() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DoctorEntity> find(String id) {
        return doctorRepository.findById(id);
    }

    @Transactional
    public void delete(String id) {
        doctorRepository.deleteById(id);
    }

    @Transactional
    public DoctorEntity save(DoctorEntity doctorEntity) {
        return doctorRepository.save(doctorEntity);
    }
}