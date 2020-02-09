package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.repository.DoctorRepository;
import nbu.medicaljournal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll().stream().map(PatientEntity::toPatient).collect(Collectors.toList());
    }

    public Patient addPatient(Patient patient, String personalGPUin) {
        Optional<DoctorEntity> optionalPersonalGP = doctorRepository.findByUin(personalGPUin);
        if (!optionalPersonalGP.isPresent()) {
            throw  new IllegalArgumentException("No doctor with the provided UIN exists!");
        }
        DoctorEntity personalGP = optionalPersonalGP.get();
        PatientEntity patientEntity = patientRepository.save(new PatientEntity(patient, personalGP));

        return patientEntity.toPatient();
    }
}