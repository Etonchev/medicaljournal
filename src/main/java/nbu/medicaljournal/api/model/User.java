package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.UserType;

import java.util.Objects;

public class User {
    public final String id;
    public final String username;
    public final String password;
    public final UserType type;
    public final String doctorUin;
    public final String patientEgn;

    public User(String id, String username, String password, UserType type, String doctorUin, String patientEgn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.doctorUin = doctorUin;
        this.patientEgn = patientEgn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                type == user.type &&
                Objects.equals(doctorUin, user.doctorUin) &&
                Objects.equals(patientEgn, user.patientEgn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, type, doctorUin, patientEgn);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", doctorUin='" + doctorUin + '\'' +
                ", patientEgn='" + patientEgn + '\'' +
                '}';
    }
}