package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.repo.DoctorRepo;
import nbu.medicaljournal.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public List<Patient> getPatients() {
        return patientRepo.all()
                .stream()
                .map(PatientEntity::toPatient)
                .collect(Collectors.toList());
    }

    public Patient addPatient(Patient patient, String personalGPUin) {
        DoctorEntity personalGP = getDoctorEntity(personalGPUin);
        PatientEntity patientEntity = patientRepo.save(new PatientEntity(patient, personalGP));
        Set<PatientEntity> patients = personalGP.getPatients();
        patients.add(patientEntity);
        personalGP.setPatients(patients);
        doctorRepo.save(personalGP);

        return patientEntity.toPatient();
    }

    public Doctor getPersonalGP(String id) {
        PatientEntity patientEntity = getPatientEntity(id);

        return patientEntity.getPersonalGP().toDoctor();
    }

    public void deletePatient(String id) {
        PatientEntity patientEntity = getPatientEntity(id);
        patientRepo.delete(id);
    }

    public Boolean getHasUninterruptedInsurance(String id) {
        return getPatientEntity(id).hasUninterruptedInsurance();
    }

    private PatientEntity getPatientEntity(String id) {
        return patientRepo.find(id).orElseThrow(() ->
                new IllegalArgumentException("No patient with the provided id exists!"));
    }

    private DoctorEntity getDoctorEntity(String id) {
        return doctorRepo.find(id).orElseThrow(() ->
                new IllegalArgumentException("No doctor with the provided id exists!"));
    }
}