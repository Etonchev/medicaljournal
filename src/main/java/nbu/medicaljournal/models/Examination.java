package nbu.medicaljournal.models;

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
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
public class Examination {
    @Id
    @GeneratedValue
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Patient patient;

    @NotEmpty(message = "Date of the examination can not be empty!")
    private LocalDate date;

    @NotBlank(message = "Diagnosis can not be empty!")
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Doctor examiner;

    @ElementCollection
    @CollectionTable(name = "examination_prescription", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "prescription")
    private Set<PrescriptionDrug> prescription;

    @Embedded
    private SickDayLeave sickDayLeave;

    public Examination() {
    }

    public Examination(Patient patient, LocalDate date, String diagnosis, Doctor examiner,
                       Set<PrescriptionDrug> prescription, SickDayLeave sickDayLeave) {
        this.patient = patient;
        this.date = date;
        this.diagnosis = diagnosis;
        this.examiner = examiner;
        this.prescription = prescription;
        this.sickDayLeave = sickDayLeave;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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

    public Doctor getExaminer() {
        return examiner;
    }

    public void setExaminer(Doctor examiner) {
        this.examiner = examiner;
    }

    public Set<PrescriptionDrug> getPrescription() {
        return prescription;
    }

    public void setPrescription(Set<PrescriptionDrug> prescription) {
        this.prescription = prescription;
    }

    public SickDayLeave getSickDayLeave() {
        return sickDayLeave;
    }

    public void setSickDayLeave(SickDayLeave sickDayLeave) {
        this.sickDayLeave = sickDayLeave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Examination that = (Examination) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(examiner, that.examiner) &&
                Objects.equals(prescription, that.prescription) &&
                Objects.equals(sickDayLeave, that.sickDayLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, date, diagnosis, examiner, prescription, sickDayLeave);
    }
}