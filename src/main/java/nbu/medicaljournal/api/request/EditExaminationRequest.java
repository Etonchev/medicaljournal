package nbu.medicaljournal.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.model.PrescriptionDrug;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

public class EditExaminationRequest {
    public final LocalDate date;

    @NotBlank(message = "Diagnosis can not be empty!")
    public final String diagnosis;

    public final Set<PrescriptionDrug> prescription;

    public final SickLeave sickLeave;

    @JsonCreator
    public EditExaminationRequest(
            @JsonProperty("date") LocalDate date,
            @JsonProperty("diagnosis") String diagnosis,
            @JsonProperty("prescription") Set<PrescriptionDrug> prescription,
            @JsonProperty("sickLeave") SickLeave sickLeave) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
    }
}