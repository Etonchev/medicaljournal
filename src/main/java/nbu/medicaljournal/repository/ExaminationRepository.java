package nbu.medicaljournal.repository;

import nbu.medicaljournal.model.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<ExaminationEntity, String> {

}