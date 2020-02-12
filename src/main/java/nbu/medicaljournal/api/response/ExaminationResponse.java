package nbu.medicaljournal.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.model.PrescriptionDrug;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

public class ExaminationResponse {
    public final String patientEGN;

    public final LocalDate date;

    public final String diagnosis;

    public final String doctorUIN;

    public final Set<PrescriptionDrug> prescription;

    public final LocalDate startingSickDayLeave;

    public final Integer totalNumberOfSickDays;

    @JsonCreator
    public ExaminationResponse(
            @JsonProperty("patientEGN") String patientEGN,
            @JsonProperty("date") LocalDate date,
            @JsonProperty("diagnosis") String diagnosis,
            @JsonProperty("doctorUIN") String doctorUIN,
            @JsonProperty("prescription") Set<PrescriptionDrug> prescription,
            @JsonProperty("startingSickDayLeave") LocalDate startingSickDayLeave,
            @JsonProperty("totalNumberOfSickDays") Integer totalNumberOfSickDays) {
        this.patientEGN = patientEGN;
        this.date = date;
        this.diagnosis = diagnosis;
        this.doctorUIN = doctorUIN;
        this.prescription = prescription;
        this.startingSickDayLeave = startingSickDayLeave;
        this.totalNumberOfSickDays = totalNumberOfSickDays;
    }
}