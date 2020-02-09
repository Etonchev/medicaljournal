package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.Speciality;

public class AddSpecialityRequest {
    public final Speciality speciality;

    @JsonCreator
    public AddSpecialityRequest(
            @JsonProperty("speciality") Speciality speciality) {
        this.speciality = speciality;
    }
}