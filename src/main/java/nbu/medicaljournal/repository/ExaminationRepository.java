package nbu.medicaljournal.repository;

import nbu.medicaljournal.model.ExaminationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExaminationRepository extends JpaRepository<ExaminationEntity, String>,
        JpaSpecificationExecutor<ExaminationEntity> {
}