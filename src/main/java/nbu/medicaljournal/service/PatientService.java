package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.repository.DoctorRepository;
import nbu.medicaljournal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        DoctorEntity personalGP = getDoctorEntity(personalGPUin);
        PatientEntity patientEntity = patientRepository.save(new PatientEntity(patient, personalGP));
        Set<PatientEntity> patients = personalGP.getPatients();
        patients.add(patientEntity);
        personalGP.setPatients(patients);
        doctorRepository.save(personalGP);

        return patientEntity.toPatient();
    }

    public Doctor getPersonalGP(String id) {
        PatientEntity patientEntity = getPatientEntity(id);

        return patientEntity.getPersonalGP().toDoctor();
    }

    public void deletePatient(String id) {
        PatientEntity patientEntity = getPatientEntity(id);
        patientEntity.setPersonalGP(null);

        patientRepository.deleteById(id);
    }

    public Boolean getHasUninterruptedInsurance(String id) {
        return getPatientEntity(id).hasUninterruptedInsurance();
    }

    private PatientEntity getPatientEntity(String id) {
        Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new IllegalArgumentException("No patient with the provided id exists!");
        }

        return optionalPatient.get();
    }

    private DoctorEntity getDoctorEntity(String personalGPUin) {
        Optional<DoctorEntity> optionalPersonalGP = doctorRepository.findByUin(personalGPUin);
        if (!optionalPersonalGP.isPresent()) {
            throw  new IllegalArgumentException("No doctor with the provided UIN exists!");
        }

        return optionalPersonalGP.get();
    }
}