package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.Speciality;

import java.util.Objects;
import java.util.Set;

public class Doctor extends Person {
    public final String id;
    public final String uin;
    public final Set<Speciality> specialities;
    public final Set<Patient> patients;

    public Doctor(String firstName, String lastName, String uin, Set<Speciality> specialities,
                  Set<Patient> patients) {
        super(firstName, lastName);
        this.id = null;
        this.uin = uin;
        this.specialities = specialities;
        this.patients = patients;
    }

    public Doctor(String id, String firstName, String lastName, String uin, Set<Speciality> specialities,
                  Set<Patient> patients) {
        super(firstName, lastName);
        this.id = id;
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
        return Objects.equals(id, doctor.id) &&
                Objects.equals(uin, doctor.uin) &&
                Objects.equals(specialities, doctor.specialities) &&
                Objects.equals(patients, doctor.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, uin, specialities, patients);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", uin='" + uin + '\'' +
                ", specialities=" + specialities +
                ", patients=" + patients +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}