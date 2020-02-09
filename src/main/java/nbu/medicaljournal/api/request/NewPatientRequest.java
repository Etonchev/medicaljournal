package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NewPatientRequest {
    @NotBlank(message = "First name can not be empty!")
    public final String firstName;

    @NotBlank(message = "Last name can not be empty!")
    public final String lastName;

    @NotBlank(message = "EGN can not be empty!")
    public final String egn;

    public final Boolean hasUninterruptedInsurance;

    @NotBlank(message = "Personal GP UIN can not be empty!")
    public final String personalGPUin;

    @JsonCreator
    public NewPatientRequest(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("egn") String egn,
            @JsonProperty("hasUninterruptedInsurance") Boolean hasUninterruptedInsurance,
            @JsonProperty("personalGPUin") String personalGPUin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.hasUninterruptedInsurance = hasUninterruptedInsurance;
        this.personalGPUin = personalGPUin;
    }
}