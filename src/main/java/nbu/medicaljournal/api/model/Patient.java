package nbu.medicaljournal.api.model;

import java.util.Objects;

public class Patient extends Person {
    public final String id;
    public final String egn;
    public final boolean hasUninterruptedInsurance;

    public Patient(String firstName, String lastName, String egn, boolean hasUninterruptedInsurance) {
        super(firstName, lastName);
        this.id = null;
        this.egn = egn;
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
    }

    public Patient(String id, String firstName, String lastName, String egn, boolean hasUninterruptedInsurance) {
        super(firstName, lastName);
        this.id = id;
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
        return hasUninterruptedInsurance == patient.hasUninterruptedInsurance &&
                Objects.equals(id, patient.id) &&
                Objects.equals(egn, patient.egn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, egn, hasUninterruptedInsurance);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", egn='" + egn + '\'' +
                ", hasUninterruptedInsurance=" + hasUninterruptedInsurance +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}