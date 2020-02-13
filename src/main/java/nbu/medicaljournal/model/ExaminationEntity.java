package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.Examination;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class ExaminationEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PatientEntity patient;

    private LocalDate date;

    @NotBlank(message = "Diagnosis can not be empty!")
    @Column(length = 65536)
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DoctorEntity doctor;

    @ElementCollection
    @CollectionTable(name = "examination_prescription", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "prescription")
    private Set<PrescriptionDrug> prescription;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "startingDate", column = @Column(name = "sick_leave_starting_date")),
            @AttributeOverride(name = "numberOfDays", column = @Column(name = "sick_leave_number_of_days"))})
    private SickLeaveEntity sickLeave;

    public ExaminationEntity() {
    }

    public ExaminationEntity(LocalDate date, String diagnosis, Set<PrescriptionDrug> prescription,
                             SickLeaveEntity sickLeave, PatientEntity patient, DoctorEntity doctor) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Set<PrescriptionDrug> getPrescription() {
        return prescription;
    }

    public void setPrescription(Set<PrescriptionDrug> prescription) {
        this.prescription = prescription;
    }

    public SickLeaveEntity getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(SickLeaveEntity sickLeave) {
        this.sickLeave = sickLeave;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity examiner) {
        this.doctor = examiner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExaminationEntity that = (ExaminationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(prescription, that.prescription) &&
                Objects.equals(sickLeave, that.sickLeave) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(doctor, that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, diagnosis, prescription, sickLeave, patient, doctor);
    }

    public Examination toExamination() {
        return new Examination(id, date, diagnosis, prescription, sickLeave.toSickLeave(), patient.toPatient(),
                doctor.toDoctor());
    }
}