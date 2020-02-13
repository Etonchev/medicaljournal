package nbu.medicaljournal.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.model.PrescriptionDrug;

import java.time.LocalDate;
import java.util.Set;

public class ExaminationResponse {
    public final String id;

    public final String patientEGN;

    public final LocalDate date;

    public final String diagnosis;

    public final String doctorUIN;

    public final Set<PrescriptionDrug> prescription;

    public final SickLeave sickLeave;

    @JsonCreator
    public ExaminationResponse(
            @JsonProperty("id") String id,
            @JsonProperty("patientEGN") String patientEGN,
            @JsonProperty("date") LocalDate date,
            @JsonProperty("diagnosis") String diagnosis,
            @JsonProperty("doctorUIN") String doctorUIN,
            @JsonProperty("prescription") Set<PrescriptionDrug> prescription,
            @JsonProperty("sickLeave") SickLeave sickLeave) {
        this.id = id;
        this.patientEGN = patientEGN;
        this.date = date;
        this.diagnosis = diagnosis;
        this.doctorUIN = doctorUIN;
        this.prescription = prescription;
        this.sickLeave = sickLeave;
    }
}