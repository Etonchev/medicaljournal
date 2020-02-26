package nbu.medicaljournal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import nbu.medicaljournal.api.model.User;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private DoctorEntity doctor;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    private PatientEntity patient;

    public UserEntity() {
    }

    public UserEntity(User user, DoctorEntity doctor, PatientEntity patient) {
        this.username = user.username;
        this.password = user.password;
        this.type = user.type;
        this.doctor = doctor;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }

        UserEntity that = (UserEntity) o;
        return id.equals(that.id) &&
                username.equals(that.username) &&
                password.equals(that.password) &&
                type == that.type &&
                Objects.equals(doctor, that.doctor) &&
                Objects.equals(patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, type, doctor, patient);
    }

    public User toUser() {
        return new User(id, username, "(censored)", type,
                doctor == null ? null : doctor.getUin(),
                patient == null ? null : patient.getEgn());
    }
}