package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.Doctor;
import nbu.medicaljournal.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}