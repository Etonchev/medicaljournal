package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.model.SickLeave;
import nbu.medicaljournal.api.request.NewExaminationRequest;
import nbu.medicaljournal.api.response.ExaminationResponse;
import nbu.medicaljournal.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/examination")
@Api(tags = "examination")
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @GetMapping
    @ApiOperation(value = "Get examinations", notes = "Get all examinations")
    public List<ExaminationResponse> getExaminations() {
        List<Examination> examinations =  examinationService.getExaminations();

        return examinations.stream()
                .map(e -> new ExaminationResponse(e.id, e.patient.egn, e.date, e.diagnosis, e.examiner.uin, e.prescription,
                        new SickLeave(e.sickLeave.startingDate, e.sickLeave.numberOfDays)))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Add examination", notes = "Add new examination")
    public Examination addExamination(
            @Validated @RequestBody NewExaminationRequest newExaminationRequest) {
        Examination examination = new Examination(null, newExaminationRequest.date,
                newExaminationRequest.diagnosis, null, newExaminationRequest.prescription,
                new SickLeave(
                        newExaminationRequest.sickLeave.startingDate,
                        newExaminationRequest.sickLeave.numberOfDays));

        return examinationService.addExamination(examination, newExaminationRequest.patientEGN,
                newExaminationRequest.doctorUIN);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get examination", notes = "Get examination")
    public ExaminationResponse getExamination(
            @PathVariable("id") String id) {
        Examination examination = examinationService.getExamination(id);

        return new ExaminationResponse(examination.id, examination.patient.egn, examination.date, examination.diagnosis,
                examination.examiner.uin, examination.prescription,
                new SickLeave(examination.sickLeave.startingDate, examination.sickLeave.numberOfDays));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete examination", notes = "Delete examination")
    public void deleteExamination(
            @PathVariable("id") String id) {
        examinationService.deleteExamination(id);
    }
}