package nbu.medicaljournal.repo;

import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepo {
    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientEntity> all() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PatientEntity> find(String id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public PatientEntity save(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    @Transactional
    public void delete(String id) {
        patientRepository.deleteById(id);
    }
}