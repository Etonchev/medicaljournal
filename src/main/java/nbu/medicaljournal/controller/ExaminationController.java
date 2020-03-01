package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nbu.medicaljournal.api.exception.ResourceNotFoundException;
import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.api.request.EditExaminationRequest;
import nbu.medicaljournal.api.request.NewExaminationRequest;
import nbu.medicaljournal.api.response.ExaminationResponse;
import nbu.medicaljournal.api.spaf.ExaminationQuery;
import nbu.medicaljournal.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/examination")
@Api(tags = "examination")
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @GetMapping
    @ApiOperation(value = "Get examinations", notes = "Get all examinations")
    public List<ExaminationResponse> getExaminations(
            @ApiParam(value = "If present, search by diagnosis will be performed. Like db operation is used for the search.")
            @RequestParam Optional<String> diagnosis,
            @ApiParam(value = "If present, search by doctor UIN will be performed.")
            @RequestParam Optional<String> doctorUin) {
        ExaminationQuery query = new ExaminationQuery(diagnosis, doctorUin);

        List<Examination> examinations =  examinationService.getExaminations(query);

        return examinations.stream()
                .map(e -> new ExaminationResponse(e.id, e.patient.egn, e.patient.firstName, e.patient.lastName, e.date,
                        e.diagnosis, e.doctor, e.prescription,
                        new SickLeave(e.sickLeave.startingDate, e.sickLeave.numberOfDays)))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Add examination", notes = "Add new examination")
    public Examination addExamination(
            @Validated @RequestBody NewExaminationRequest newExaminationRequest) throws ResourceNotFoundException {
        Examination examination = new Examination(newExaminationRequest.date,
                newExaminationRequest.diagnosis, newExaminationRequest.prescription,
                new SickLeave(
                        newExaminationRequest.sickLeave.startingDate,
                        newExaminationRequest.sickLeave.numberOfDays),
                null, null);

        return examinationService.addExamination(examination, newExaminationRequest.patientEGN,
                newExaminationRequest.doctorUIN);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get examination", notes = "Get examination")
    public ExaminationResponse getExamination(
            @PathVariable("id") String id) throws ResourceNotFoundException {
        Examination examination = examinationService.getExamination(id);

        return new ExaminationResponse(examination.id, examination.patient.egn, examination.patient.firstName,
                examination.patient.lastName, examination.date, examination.diagnosis, examination.doctor,
                examination.prescription,
                new SickLeave(examination.sickLeave.startingDate, examination.sickLeave.numberOfDays));
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Edit examination", notes = "Edit examination")
    public Examination editExamination(
            @PathVariable("id") String id,
            @Validated @RequestBody EditExaminationRequest examinationRequest) throws ResourceNotFoundException {
        return examinationService.editExamination(id, examinationRequest.date, examinationRequest.diagnosis,
                examinationRequest.prescription, examinationRequest.sickLeave);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete examination", notes = "Delete examination")
    public void deleteExamination(
            @PathVariable("id") String id) throws ResourceNotFoundException {
        examinationService.deleteExamination(id);
    }
}