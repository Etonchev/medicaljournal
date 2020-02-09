package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}