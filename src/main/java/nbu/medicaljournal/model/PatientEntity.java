package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.Patient;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class PatientEntity extends PersonEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = "EGN can not be empty!")
    @Column(length = 10)
    private String egn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DoctorEntity personalGP;

    private boolean hasUninterruptedInsurance;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public DoctorEntity getPersonalGP() {
        return personalGP;
    }

    public void setPersonalGP(DoctorEntity personalGP) {
        this.personalGP = personalGP;
    }

    public boolean isHasUninterruptedInsurance() {
        return hasUninterruptedInsurance;
    }

    public void setHasUninterruptedInsurance(boolean hasUninterruptedInsurance) {
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PatientEntity patient = (PatientEntity) o;
        return hasUninterruptedInsurance == patient.hasUninterruptedInsurance &&
                Objects.equals(id, patient.id) &&
                Objects.equals(egn, patient.egn) &&
                Objects.equals(personalGP, patient.personalGP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, egn, personalGP, hasUninterruptedInsurance);
    }

    public Patient toPatient() {
        return new Patient(id, getFirstName(), getLastName(), egn, hasUninterruptedInsurance);
    }
}