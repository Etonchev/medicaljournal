package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.PrescriptionDrug;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Examination {
    public final Patient patient;
    public final LocalDate date;
    public final String diagnosis;
    public final Doctor examiner;
    public final Set<PrescriptionDrug> prescription;
    public final SickDayLeave sickDayLeave;

    public Examination(Patient patient, LocalDate date, String diagnosis, Doctor examiner,
                       Set<PrescriptionDrug> prescription, SickDayLeave sickDayLeave) {
        this.patient = patient;
        this.date = date;
        this.diagnosis = diagnosis;
        this.examiner = examiner;
        this.prescription = prescription;
        this.sickDayLeave = sickDayLeave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Examination)) return false;
        Examination that = (Examination) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(examiner, that.examiner) &&
                Objects.equals(prescription, that.prescription) &&
                Objects.equals(sickDayLeave, that.sickDayLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, date, diagnosis, examiner, prescription, sickDayLeave);
    }

    @Override
    public String toString() {
        return "Examination{" +
                "patient=" + patient +
                ", date=" + date +
                ", diagnosis='" + diagnosis + '\'' +
                ", examiner=" + examiner +
                ", prescription=" + prescription +
                ", sickDayLeave=" + sickDayLeave +
                '}';
    }
}