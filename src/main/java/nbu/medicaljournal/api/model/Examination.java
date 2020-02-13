package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.PrescriptionDrug;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Examination {
    public final String id;
    public final LocalDate date;
    public final String diagnosis;
    public final Set<PrescriptionDrug> prescription;
    public final SickLeave sickLeave;
    public final Patient patient;
    public final Doctor doctor;

    public Examination(LocalDate date, String diagnosis, Set<PrescriptionDrug> prescription, SickLeave sickLeave,
                       Patient patient, Doctor doctor) {
        this(null, date, diagnosis, prescription, sickLeave, patient, doctor);
    }

    public Examination(String id, LocalDate date, String diagnosis, Set<PrescriptionDrug> prescription,
                       SickLeave sickLeave, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Examination)) {
            return false;
        }

        Examination that = (Examination) o;
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

    @Override
    public String toString() {
        return "Examination{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription=" + prescription +
                ", sickLeave=" + sickLeave +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}