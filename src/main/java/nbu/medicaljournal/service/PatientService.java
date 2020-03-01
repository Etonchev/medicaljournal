package nbu.medicaljournal.service;

import nbu.medicaljournal.api.exception.ResourceNotFoundException;
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

    public Patient getPatient(String id) throws ResourceNotFoundException {
        return getPatientEntity(id).toPatient();
    }

    public Patient addPatient(Patient patient) throws ResourceNotFoundException {
        DoctorEntity personalGP = getDoctorEntity(patient.personalGPUin);
        PatientEntity patientEntity = patientRepo.save(new PatientEntity(patient, personalGP));
        Set<PatientEntity> patients = personalGP.getPatients();
        patients.add(patientEntity);
        personalGP.setPatients(patients);
        doctorRepo.save(personalGP);

        return patientEntity.toPatient();
    }

    public Patient editDoctor(String id, String firstName, String lastName, String newPersonalGPUin)
            throws ResourceNotFoundException {
        PatientEntity patient = getPatientEntity(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        String oldPersonalGPUin = patient.getPersonalGP().getUin();
        if (!oldPersonalGPUin.equals(newPersonalGPUin)) {
            // Remove entry from old personalGP
            DoctorEntity oldPersonalGP = getDoctorEntity(oldPersonalGPUin);
            Set<PatientEntity> oldPersonalGPPatients = oldPersonalGP.getPatients();
            oldPersonalGPPatients.removeIf(p -> p.getEgn().equals(id));
            oldPersonalGP.setPatients(oldPersonalGPPatients);
            doctorRepo.save(oldPersonalGP);

            // Add patient to new personalGP
            DoctorEntity newPersonalGP = getDoctorEntity(newPersonalGPUin);
            Set<PatientEntity> newPersonalGPPatients = newPersonalGP.getPatients();
            newPersonalGPPatients.add(patient);
            newPersonalGP.setPatients(newPersonalGPPatients);
            doctorRepo.save(newPersonalGP);

            patient.setPersonalGP(newPersonalGP);
        }

        return patientRepo.save(patient).toPatient();
    }

    public Doctor getPersonalGP(String id) throws ResourceNotFoundException {
        PatientEntity patientEntity = getPatientEntity(id);

        return patientEntity.getPersonalGP().toDoctor();
    }

    public void deletePatient(String id) throws ResourceNotFoundException {
        PatientEntity patient = getPatientEntity(id);
        DoctorEntity doctor = patient.getPersonalGP();

        Set<PatientEntity> patients = doctor.getPatients();
        patients.removeIf(p -> p.getEgn().equals(id));
        doctor.setPatients(patients);

        doctorRepo.save(doctor);
    }

    public Boolean getHasUninterruptedInsurance(String id) throws ResourceNotFoundException {
        return getPatientEntity(id).hasUninterruptedInsurance();
    }

    private PatientEntity getPatientEntity(String id) throws ResourceNotFoundException {
        return patientRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No patient with the provided id exists!"));
    }

    private DoctorEntity getDoctorEntity(String id) throws ResourceNotFoundException {
        return doctorRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No doctor with the provided id exists!"));
    }
}