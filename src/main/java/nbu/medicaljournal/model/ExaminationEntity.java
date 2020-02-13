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
    private DoctorEntity examiner;

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

    public ExaminationEntity(PatientEntity patient, LocalDate date, String diagnosis, DoctorEntity examiner,
                             Set<PrescriptionDrug> prescription, SickLeaveEntity sickLeave) {
        this.patient = patient;
        this.date = date;
        this.diagnosis = diagnosis;
        this.examiner = examiner;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
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

    public DoctorEntity getExaminer() {
        return examiner;
    }

    public void setExaminer(DoctorEntity examiner) {
        this.examiner = examiner;
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
                Objects.equals(patient, that.patient) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(examiner, that.examiner) &&
                Objects.equals(prescription, that.prescription) &&
                Objects.equals(sickLeave, that.sickLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, date, diagnosis, examiner, prescription, sickLeave);
    }

    public Examination toExamination() {
        return new Examination(id, patient.toPatient(), date, diagnosis, examiner.toDoctor(), prescription,
                sickLeave.toSickLeave());
    }
}