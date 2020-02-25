package nbu.medicaljournal.api.model;

import nbu.medicaljournal.model.UserType;

import java.util.Objects;

public class User {
    public final String id;
    public final UserType type;
    public final String username;
    public final String password;

    public User(String id, UserType type, String username, String password) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                type == user.type &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}