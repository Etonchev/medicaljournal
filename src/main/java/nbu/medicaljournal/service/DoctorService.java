package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.Speciality;
import nbu.medicaljournal.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll().stream().map(DoctorEntity::toDoctor).collect(Collectors.toList());
    }

    public Doctor addDoctor(Doctor doctor) {
        DoctorEntity doctorEntity = doctorRepository.save(new DoctorEntity(doctor));
        return doctorEntity.toDoctor();
    }

    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    public Doctor addSpeciality(String id, Speciality speciality) {
        DoctorEntity doctor = getDoctorEntity(id);
        Set<Speciality> specialities = doctor.getSpecialities();
        specialities.add(speciality);
        doctor.setSpecialities(specialities);
        doctorRepository.save(doctor);

        return doctor.toDoctor();
    }

    public Set<Patient> getPatients(String id) {
        DoctorEntity doctor = getDoctorEntity(id);

        return doctor.toDoctor().patients;
    }

    public void deletePatient(String doctorId, String patientId) {
        DoctorEntity doctor = getDoctorEntity(doctorId);
        Set<PatientEntity> patients = doctor.getPatients();
        patients.removeIf(p -> p.getId().equals(patientId));
        doctor.setPatients(patients);
        doctorRepository.save(doctor);
    }

    private DoctorEntity getDoctorEntity(String id) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorRepository.findById(id);
        if (!optionalDoctorEntity.isPresent()) {
            throw new IllegalArgumentException("There is no doctor with the provided id");
        }

        return optionalDoctorEntity.get();
    }
}