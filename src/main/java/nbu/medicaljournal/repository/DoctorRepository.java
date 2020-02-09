package nbu.medicaljournal.repository;

import nbu.medicaljournal.model.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, String> {

}