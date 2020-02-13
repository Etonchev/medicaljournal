package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.Speciality;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class NewDoctorRequest {
    @NotBlank(message = "First name can not be empty!")
    public final String firstName;

    @NotBlank(message = "Last name can not be empty!")
    public final String lastName;

    @NotBlank(message = "UIN can not be empty!")
    public final String uin;

    @NotEmpty(message = "Doctors must have at least one speciality!")
    public final Set<Speciality> specialities;

    @JsonCreator
    public NewDoctorRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("uin") String uin,
            @JsonProperty("specialities") Set<Speciality> specialities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uin = uin;
        this.specialities = specialities;
    }
}