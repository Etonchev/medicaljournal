package nbu.medicaljournal.api.model;

import java.util.Objects;

public class Patient extends Person {
    public final String egn;
    public final Doctor personalGP;
    public final boolean hasUninterruptedInsurance;

    public Patient(String firstName, String lastName, String egn, Doctor personalGP,
                   boolean hasUninterruptedInsurance) {
        super(firstName, lastName);

        this.egn = egn;
        this.personalGP = personalGP;
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return hasUninterruptedInsurance == patient.hasUninterruptedInsurance &&
                Objects.equals(egn, patient.egn) &&
                Objects.equals(personalGP, patient.personalGP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), egn, personalGP, hasUninterruptedInsurance);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "egn='" + egn + '\'' +
                ", personalGP=" + personalGP +
                ", hasUninterruptedInsurance=" + hasUninterruptedInsurance +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}