package nbu.medicaljournal.model;

import lombok.Data;

import java.util.Set;

@Data
public class Doctor extends Person {
    public String uin;
    public Set<MedicalSpeciality> medicalSpecialities;
}