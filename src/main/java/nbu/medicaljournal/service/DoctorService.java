package nbu.medicaljournal.service;

import nbu.medicaljournal.api.exception.ResourceNotFoundException;
import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.Speciality;
import nbu.medicaljournal.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    public List<Doctor> getDoctors() {
        return doctorRepo.all()
                .stream()
                .map(DoctorEntity::toDoctor)
                .collect(Collectors.toList());
    }

    public Doctor getDoctor(String id) throws ResourceNotFoundException {
        return getDoctorEntity(id).toDoctor();
    }

    public Doctor addDoctor(String uin, String firstName, String lastName, Set<Speciality> specialities) {
        Doctor doctor = new Doctor(uin, firstName, lastName, specialities, new HashSet<>());
        DoctorEntity doctorEntity = doctorRepo.save(new DoctorEntity(doctor));

        return doctorEntity.toDoctor();
    }

    public void deleteDoctor(String id) {
        doctorRepo.delete(id);
    }

    public Doctor addSpeciality(String id, Speciality speciality) throws ResourceNotFoundException {
        DoctorEntity doctor = getDoctorEntity(id);
        Set<Speciality> specialities = doctor.getSpecialities();
        specialities.add(speciality);
        doctor.setSpecialities(specialities);
        doctorRepo.save(doctor);

        return doctor.toDoctor();
    }

    public Set<Patient> getPatients(String id) throws ResourceNotFoundException {
        DoctorEntity doctor = getDoctorEntity(id);

        return doctor.getPatients()
                .stream()
                .map(PatientEntity::toPatient)
                .collect(Collectors.toSet());
    }

    public void deletePatient(String doctorId, String patientId) throws ResourceNotFoundException {
        DoctorEntity doctor = getDoctorEntity(doctorId);
        Set<PatientEntity> patients = doctor.getPatients();
        patients.removeIf(p -> p.getEgn().equals(patientId));
        doctor.setPatients(patients);
        doctorRepo.save(doctor);
    }

    public DoctorEntity editDoctor(String id, String firstName, String lastName, Set<Speciality> specialities)
            throws ResourceNotFoundException {
        DoctorEntity doctor = getDoctorEntity(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setSpecialities(specialities);
        doctorRepo.save(doctor);

        return doctor;
    }

    private DoctorEntity getDoctorEntity(String id) throws ResourceNotFoundException {
        return doctorRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("There is no doctor with the provided id"));
    }
}