package nbu.medicaljournal.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MedicalCheckup {
    public LocalDate date;
    public String diagnosis;
    public Doctor examiner;
    public Set<PrescriptionDrug> prescription;
    public LocalDate startingSickDayLeave;
    public int totalNumberOfSickDays;
}