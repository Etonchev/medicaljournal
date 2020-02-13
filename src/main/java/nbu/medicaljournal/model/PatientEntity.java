package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.Patient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class PatientEntity extends PersonEntity {
    @Id
    @Column(length = 10)
    private String egn;

    private boolean hasUninterruptedInsurance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DoctorEntity personalGP;

    public PatientEntity() {
    }

    public PatientEntity(Patient patient, DoctorEntity doctor) {
        super(patient.firstName, patient.lastName);

        if (patient.egn.length() != 10) {
            throw new IllegalArgumentException("EGN must be 10 characters long!");
        }

        this.egn = patient.egn;
        this.hasUninterruptedInsurance = patient.hasUninterruptedInsurance;
        this.personalGP = doctor;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public boolean hasUninterruptedInsurance() {
        return hasUninterruptedInsurance;
    }

    public void setHasUninterruptedInsurance(boolean hasUninterruptedInsurance) {
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
    }

    public DoctorEntity getPersonalGP() {
        return personalGP;
    }

    public void setPersonalGP(DoctorEntity personalGP) {
        this.personalGP = personalGP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientEntity)) {
            return false;
        }

        PatientEntity that = (PatientEntity) o;
        return hasUninterruptedInsurance == that.hasUninterruptedInsurance &&
                egn.equals(that.egn) &&
                personalGP.equals(that.personalGP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(egn, hasUninterruptedInsurance, personalGP);
    }

    public Patient toPatient() {
        return new Patient(egn, getFirstName(), getLastName(), hasUninterruptedInsurance);
    }
}