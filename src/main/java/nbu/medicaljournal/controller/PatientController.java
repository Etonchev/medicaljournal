package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.api.request.NewPatientRequest;
import nbu.medicaljournal.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@Api(tags = "patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    @ApiOperation(value = "List patients", notes = "List all patients")
    public List<Patient> listPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @ApiOperation(value = "Add patient", notes = "Add a new patient")
    public Patient addPatient(
            @Validated @RequestBody NewPatientRequest newPatientRequest) {
        Patient patient = new Patient(newPatientRequest.firstName, newPatientRequest.lastName, newPatientRequest.egn,
                newPatientRequest.hasUninterruptedInsurance);

        return patientService.addPatient(patient, newPatientRequest.personalGPUin);
    }
}