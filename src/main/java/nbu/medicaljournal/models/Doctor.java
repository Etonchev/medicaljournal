package nbu.medicaljournal.models;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Doctor extends Person {
    @Id
    @GeneratedValue
    private String id;

    @NotBlank(message = "UIN can not be empty!")
    private String uin;

    @ElementCollection
    @CollectionTable(name = "doctor_speciality", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "speciality")
    @NotEmpty(message = "Doctors must have at least one speciality!")
    private Set<Speciality> specialities = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients = new HashSet<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String uin, Set<Speciality> specialities, Set<Patient> patients) {
        super(firstName, lastName);

        if (uin.length() != 10) {
            throw new IllegalArgumentException("UIN must be 10 characters long!");
        }

        this.uin = uin;
        this.specialities = specialities;
        this.patients = patients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
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

        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) &&
                Objects.equals(uin, doctor.uin) &&
                Objects.equals(specialities, doctor.specialities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uin, specialities);
    }
}