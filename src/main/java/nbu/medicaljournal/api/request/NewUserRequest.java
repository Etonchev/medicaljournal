package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.UserType;

import javax.validation.constraints.NotBlank;

public class NewUserRequest {
    @NotBlank(message = "Username can not be empty!")
    public final String username;

    @NotBlank(message = "Password can not be empty!")
    public final String password;

    public final UserType type;

    public final String doctorUin;

    public final String patientEgn;

    @JsonCreator
    public NewUserRequest(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("type") UserType type,
            @JsonProperty("doctorUin") String doctorUin,
            @JsonProperty("patientEgn") String patientEgn) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.doctorUin = doctorUin;
        this.patientEgn = patientEgn;
    }
}