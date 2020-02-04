package nbu.medicaljournal.model;

import lombok.Data;

@Data
public class Patient extends Person {
    public String egn;
    public Doctor personalGP;
    public boolean hasUninterruptedInsurance;
}