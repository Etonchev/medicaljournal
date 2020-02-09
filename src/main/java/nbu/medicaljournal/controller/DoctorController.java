package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.api.model.Patient;
import nbu.medicaljournal.api.request.AddSpecialityRequest;
import nbu.medicaljournal.api.request.NewDoctorRequest;
import nbu.medicaljournal.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/doctor")
@Api(tags = "doctor")
public class DoctorController {
    @Autowired
    protected DoctorService doctorService;

    @GetMapping
    @ApiOperation(value = "List doctors", notes = "List all doctors")
    public List<Doctor> listDoctors() {
        return doctorService.getDoctors();
    }

    @PostMapping
    @ApiOperation(value = "Add doctor", notes = "Add a new doctor")
    public Doctor creteDoctor(
            @Validated @RequestBody NewDoctorRequest doctor) {
        Doctor newDoctor = new Doctor(doctor.firstName, doctor.lastName, doctor.uin, doctor.specialities,
                new HashSet<>());

        return doctorService.addDoctor(newDoctor);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete doctor", notes = "Delete a doctor")
    public void deleteDoctor(
            @PathVariable("id") String id) {
        doctorService.deleteDoctor(id);
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Add speciality", notes = "Add new speciality to a doctor")
    public Doctor addSpeciality(
            @PathVariable("id") String id,
            @Validated @RequestBody AddSpecialityRequest addSpecialityRequest) {
        return doctorService.addSpeciality(id, addSpecialityRequest.speciality);
    }

    @GetMapping("{id}/patients")
    @ApiOperation(value = "Get patients", notes = "Get all patients for this doctor")
    public Set<Patient> getPatients(
            @PathVariable("id") String id) {
        return doctorService.getPatients(id);
    }
}