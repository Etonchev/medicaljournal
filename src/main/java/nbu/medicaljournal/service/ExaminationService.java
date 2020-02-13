package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.SickLeaveEntity;
import nbu.medicaljournal.repository.DoctorRepository;
import nbu.medicaljournal.repository.ExaminationRepository;
import nbu.medicaljournal.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExaminationService {
    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public List<Examination> getExaminations() {
        return examinationRepository.findAll()
                .stream()
                .map(ExaminationEntity::toExamination)
                .collect(Collectors.toList());
    }

    public Examination addExamination(Examination examination, String patientId, String doctorId) {
        DoctorEntity doctor = getDoctorEntity(doctorId);
        PatientEntity patient = getPatientEntity(patientId);

        ExaminationEntity examinationEntity = new ExaminationEntity(examination.date, examination.diagnosis,
                examination.prescription,
                new SickLeaveEntity(
                        examination.sickLeave.startingDate,
                        examination.sickLeave.numberOfDays),
                patient, doctor);
        examinationRepository.save(examinationEntity);

        return examinationEntity.toExamination();
    }

    public Examination getExamination(String id) {
        return getExaminationEntity(id).toExamination();
    }

    public void deleteExamination(String id) {
        examinationRepository.deleteById(id);
    }

    private PatientEntity getPatientEntity(String id) {
        Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new IllegalArgumentException("No patient with the provided id exists!");
        }

        return optionalPatient.get();
    }

    private DoctorEntity getDoctorEntity(String doctorId) {
        Optional<DoctorEntity> optionalPersonalGP = doctorRepository.findById(doctorId);
        if (!optionalPersonalGP.isPresent()) {
            throw  new IllegalArgumentException("No doctor with the provided id exists!");
        }

        return optionalPersonalGP.get();
    }

    private ExaminationEntity getExaminationEntity(String id) {
        Optional<ExaminationEntity> optionalExamination = examinationRepository.findById(id);
        if (!optionalExamination.isPresent()) {
            throw  new IllegalArgumentException("No examination with the provided id exists!");
        }

        return optionalExamination.get();
    }
}