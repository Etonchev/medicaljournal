package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditPatientRequest {
    @NotBlank(message = "First name can not be empty!")
    public final String firstName;

    @NotBlank(message = "Last name can not be empty!")
    public final String lastName;

    @NotBlank(message = "Personal GP UIN can not be empty!")
    public final String personalGPUin;

    @JsonCreator
    public EditPatientRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("personalGPUin") String personalGPUin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalGPUin = personalGPUin;
    }
}