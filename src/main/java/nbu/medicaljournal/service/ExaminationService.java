package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.model.DoctorEntity;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.model.PatientEntity;
import nbu.medicaljournal.model.SickDayLeaveEntity;
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

    public Examination addExamination(Examination examination, String patientEGN, String doctorUIN) {
        DoctorEntity doctor = getDoctorEntity(doctorUIN);
        PatientEntity patient = getPatientEntity(patientEGN);

        ExaminationEntity examinationEntity = new ExaminationEntity(patient, examination.date, examination.diagnosis,
                doctor, examination.prescription,
                new SickDayLeaveEntity(
                        examination.sickDayLeave.startingSickDayLeave,
                        examination.sickDayLeave.totalNumberOfSickDays));
        examinationRepository.save(examinationEntity);

        return examinationEntity.toExamination();
    }

    private PatientEntity getPatientEntity(String egn) {
        Optional<PatientEntity> optionalPatient = patientRepository.findByEgn(egn);
        if (!optionalPatient.isPresent()) {
            throw new IllegalArgumentException("No patient with the provided EGN exists!");
        }

        return optionalPatient.get();
    }

    private DoctorEntity getDoctorEntity(String doctorUIN) {
        Optional<DoctorEntity> optionalPersonalGP = doctorRepository.findByUin(doctorUIN);
        if (!optionalPersonalGP.isPresent()) {
            throw  new IllegalArgumentException("No doctor with the provided UIN exists!");
        }

        return optionalPersonalGP.get();
    }
}