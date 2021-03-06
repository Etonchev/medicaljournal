package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.Doctor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class DoctorEntity extends PersonEntity {
    @Id
    @Column(length = 10)
    private String uin;

    @ElementCollection
    @CollectionTable(name = "doctor_speciality", joinColumns = @JoinColumn(name = "uin"))
    @Enumerated(EnumType.STRING)
    @Column(name = "speciality")
    private Set<Speciality> specialities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PatientEntity> patients;

    public DoctorEntity() {
    }

    public DoctorEntity(Doctor doctor) {
        super(doctor.firstName, doctor.lastName);

        if (doctor.uin.length() != 10) {
            throw new IllegalArgumentException("UIN must be 10 characters long!");
        }

        this.uin = doctor.uin;
        this.specialities = doctor.specialities;
        this.patients = new HashSet<>();
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getLastName() {
        return super.getLastName();
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Set<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientEntity> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoctorEntity doctor = (DoctorEntity) o;
        return Objects.equals(uin, doctor.uin) &&
                Objects.equals(specialities, doctor.specialities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uin, specialities);
    }

    public Doctor toDoctor() {
        return new Doctor(uin, getFirstName(), getLastName(), specialities,
                patients.stream().map(PatientEntity::toPatient).collect(Collectors.toSet()));
    }
}