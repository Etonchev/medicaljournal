package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.Speciality;

import java.util.Objects;
import java.util.Set;

public class Doctor extends Person {
    public final String uin;
    public final Set<Speciality> specialities;
    public final Set<Patient> patients;

    public Doctor(String uin, String firstName, String lastName, Set<Speciality> specialities, Set<Patient> patients) {
        super(firstName, lastName);
        this.uin = uin;
        this.specialities = specialities;
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Doctor)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Doctor doctor = (Doctor) o;
        return Objects.equals(uin, doctor.uin) &&
                Objects.equals(specialities, doctor.specialities) &&
                Objects.equals(patients, doctor.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uin, specialities, patients);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "uin='" + uin + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialities=" + specialities +
                ", patients=" + patients +
                '}';
    }
}