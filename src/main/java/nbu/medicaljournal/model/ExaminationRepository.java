package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination, String> {

}