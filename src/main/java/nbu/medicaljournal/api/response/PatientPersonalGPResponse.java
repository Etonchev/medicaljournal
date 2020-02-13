package nbu.medicaljournal.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.Speciality;

import java.util.Set;

public class PatientPersonalGPResponse {
    public final String uin;

    public final String firstName;

    public final String lastName;

    public final Set<Speciality> specialities;

    @JsonCreator
    public PatientPersonalGPResponse(
            @JsonProperty("uin") String uin,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("specialities") Set<Speciality> specialities) {
        this.uin = uin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialities = specialities;
    }
}