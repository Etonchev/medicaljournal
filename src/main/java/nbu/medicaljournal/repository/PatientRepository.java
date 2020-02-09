package nbu.medicaljournal.repository;

import nbu.medicaljournal.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, String> {

}