package nbu.medicaljournal.api.model;

import java.util.Objects;

public class Patient extends Person {
    public final String egn;
    public final boolean hasUninterruptedInsurance;
    public final String personalGPUin;

    public Patient(String egn, String firstName, String lastName, boolean hasUninterruptedInsurance,
                   String personalGPUin) {
        super(firstName, lastName);
        this.egn = egn;
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
        this.personalGPUin = personalGPUin;
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
                hasUninterruptedInsurance == patient.hasUninterruptedInsurance &&
                Objects.equals(personalGPUin, patient.personalGPUin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), egn, hasUninterruptedInsurance, personalGPUin);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "egn='" + egn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasUninterruptedInsurance=" + hasUninterruptedInsurance +
                ", personalGPUin=" + personalGPUin +
                '}';
    }
}