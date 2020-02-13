package nbu.medicaljournal.api.model;

import java.util.Objects;

public class Patient extends Person {
    public final String egn;
    public final boolean hasUninterruptedInsurance;

    public Patient(String egn, String firstName, String lastName, boolean hasUninterruptedInsurance) {
        super(firstName, lastName);
        this.egn = egn;
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Patient patient = (Patient) o;
        return Objects.equals(egn, patient.egn) &&
                hasUninterruptedInsurance == patient.hasUninterruptedInsurance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), egn, hasUninterruptedInsurance);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "egn='" + egn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasUninterruptedInsurance=" + hasUninterruptedInsurance +
                '}';
    }
}