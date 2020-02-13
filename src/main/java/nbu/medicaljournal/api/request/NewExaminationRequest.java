package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.model.PrescriptionDrug;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

public class NewExaminationRequest {
    @NotBlank(message = "EGN can not be empty!")
    public final String patientEGN;

    @Past
    public final LocalDate date;

    @NotBlank(message = "Diagnosis can not be empty!")
    public final String diagnosis;

    @NotBlank(message = "Doctor UIN can not be empty!")
    public final String doctorUIN;

    public final Set<PrescriptionDrug> prescription;

    public final SickLeave sickLeave;

    @JsonCreator
    public NewExaminationRequest(
            @JsonProperty("patientEGN") String patientEGN,
            @JsonProperty("date") LocalDate date,
            @JsonProperty("diagnosis") String diagnosis,
            @JsonProperty("doctorUIN") String doctorUIN,
            @JsonProperty("prescription") Set<PrescriptionDrug> prescription,
            @JsonProperty("sickLeave") SickLeave sickLeave) {
        this.patientEGN = patientEGN;
        this.date = date;
        this.diagnosis = diagnosis;
        this.doctorUIN = doctorUIN;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
    }
}