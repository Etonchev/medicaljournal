package nbu.medicaljournal.service;

import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExaminationService {
    @Autowired
    ExaminationRepository examinationRepository;

    public List<Examination> getExaminations() {
        return examinationRepository.findAll()
                .stream()
                .map(ExaminationEntity::toExamination)
                .collect(Collectors.toList());
    }
}