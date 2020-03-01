package nbu.medicaljournal.service;

import nbu.medicaljournal.api.exception.ResourceNotFoundException;
import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.api.spaf.ExaminationQuery;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.PrescriptionDrug;
import nbu.medicaljournal.model.SickLeaveEntity;
import nbu.medicaljournal.repo.DoctorRepo;
import nbu.medicaljournal.repo.ExaminationRepo;
import nbu.medicaljournal.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public Examination addExamination(Examination examination, String patientId, String doctorId) throws ResourceNotFoundException {
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

    public Examination editExamination(String id, LocalDate date, String diagnosis, Set<PrescriptionDrug> prescription,
                                       SickLeave sickLeave) throws ResourceNotFoundException {
        ExaminationEntity examination = getExaminationEntity(id);
        examination.setDate(date);
        examination.setDiagnosis(diagnosis);
        examination.setPrescription(prescription);
        if (sickLeave != null && sickLeave.startingDate != null && sickLeave.numberOfDays > 0) {
            SickLeaveEntity sickLeaveEntity = new SickLeaveEntity(sickLeave.startingDate, sickLeave.numberOfDays);
            examination.setSickLeave(sickLeaveEntity);
        } else {
            SickLeaveEntity sickLeaveEntity = new SickLeaveEntity(null, 0);
            examination.setSickLeave(sickLeaveEntity);
        }

        examinationRepo.save(examination);

        return examination.toExamination();
    }

    public Examination getExamination(String id) throws ResourceNotFoundException {
        return getExaminationEntity(id).toExamination();
    }

    public void deleteExamination(String id) throws ResourceNotFoundException {
        ExaminationEntity examinationEntity = getExaminationEntity(id);
        examinationRepo.delete(id);
    }

    private PatientEntity getPatientEntity(String id) throws ResourceNotFoundException {
        return patientRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No patient with the provided id exists!"));
    }

    private DoctorEntity getDoctorEntity(String doctorId) throws ResourceNotFoundException {
        return doctorRepo.find(doctorId).orElseThrow(() ->
                new ResourceNotFoundException("No doctor with the provided id exists!"));
    }

    private ExaminationEntity getExaminationEntity(String id) throws ResourceNotFoundException {
        return examinationRepo.find(id).orElseThrow(() ->
                new ResourceNotFoundException("No examination with the provided id exists!"));
    }
}