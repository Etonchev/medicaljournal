package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.spaf.ExaminationQuery;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.SickLeaveEntity;
import nbu.medicaljournal.repo.DoctorRepo;
import nbu.medicaljournal.repo.ExaminationRepo;
import nbu.medicaljournal.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExaminationService {
    @Autowired
    ExaminationRepo examinationRepo;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public List<Examination> getExaminations(ExaminationQuery query) {
        return examinationRepo.all(query)
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
        examinationRepo.save(examinationEntity);

        return examinationEntity.toExamination();
    }

    public Examination getExamination(String id) {
        return getExaminationEntity(id).toExamination();
    }

    public void deleteExamination(String id) {
        examinationRepo.delete(id);
    }

    private PatientEntity getPatientEntity(String id) {
        return patientRepo.find(id).orElseThrow(() ->
                new IllegalArgumentException("No patient with the provided id exists!"));
    }

    private DoctorEntity getDoctorEntity(String doctorId) {
        return doctorRepo.find(doctorId).orElseThrow(() ->
                new IllegalArgumentException("No doctor with the provided id exists!"));
    }

    private ExaminationEntity getExaminationEntity(String id) {
        return examinationRepo.find(id).orElseThrow(() ->
                new IllegalArgumentException("No examination with the provided id exists!"));
    }
}