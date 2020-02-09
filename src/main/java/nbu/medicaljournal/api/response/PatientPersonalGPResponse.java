package nbu.medicaljournal.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.Speciality;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class PatientPersonalGPResponse {
    public final String firstName;

    public final String lastName;

    public final String uin;

    public final Set<Speciality> specialities;

    @JsonCreator
    public PatientPersonalGPResponse(
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