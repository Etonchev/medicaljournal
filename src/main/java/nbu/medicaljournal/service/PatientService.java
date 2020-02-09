package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll().stream().map(PatientEntity::toPatient).collect(Collectors.toList());
    }
}