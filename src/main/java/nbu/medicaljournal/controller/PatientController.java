package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.exception.ResourceNotFoundException;
import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.api.request.EditPatientRequest;
import nbu.medicaljournal.api.request.NewPatientRequest;
import nbu.medicaljournal.api.response.PatientPersonalGPResponse;
import nbu.medicaljournal.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("{id}")
    @ApiOperation(value = "Get patient", notes = "Get a patient")
    public Patient getPatient(
            @PathVariable("id") String id) throws ResourceNotFoundException {
        return patientService.getPatient(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Add patient", notes = "Add a new patient")
    public Patient addPatient(
            @Validated @RequestBody NewPatientRequest newPatientRequest) throws ResourceNotFoundException {
        Patient patient = new Patient(newPatientRequest.egn, newPatientRequest.firstName, newPatientRequest.lastName,
                newPatientRequest.hasUninterruptedInsurance, newPatientRequest.personalGPUin);

        return patientService.addPatient(patient);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Edit patient", notes = "Edit a patient")
    public Patient editPatient(
            @PathVariable("id") String id,
            @Validated @RequestBody EditPatientRequest editPatientRequest) throws ResourceNotFoundException {
        return patientService.editDoctor(id, editPatientRequest.firstName, editPatientRequest.lastName,
                editPatientRequest.personalGPUin);
    }

    @GetMapping("{id}/personalGP")
    @ApiOperation(value = "Get patient personalGP", notes = "Get the personal GP for the patient")
    public PatientPersonalGPResponse getPatientPersonalGP(
            @PathVariable("id") String id) throws ResourceNotFoundException {
        Doctor doctor = patientService.getPersonalGP(id);

        return new PatientPersonalGPResponse(doctor.uin, doctor.firstName, doctor.lastName, doctor.specialities);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete patient", notes = "Delete a patient")
    public void deletePatient(
            @PathVariable("id") String id) throws ResourceNotFoundException {
        patientService.deletePatient(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("{id}/insurance")
    @ApiOperation(value = "Check patient insurance", notes = "Check if the patient has uninterrupted insurance")
    public Boolean checkInsurance(
            @PathVariable String id) throws ResourceNotFoundException {
        return patientService.getHasUninterruptedInsurance(id);
    }
}