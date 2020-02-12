package nbu.medicaljournal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.model.SickDayLeave;
import nbu.medicaljournal.api.request.NewExaminationRequest;
import nbu.medicaljournal.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/examination")
@Api(tags = "examination")
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    @GetMapping
    @ApiOperation(value = "Get examinations", notes = "Get all examinations")
    public List<Examination> getExaminations() {
        return examinationService.getExaminations();
    }

    @PostMapping
    @ApiOperation(value = "Add examination", notes = "Add new examination")
    public Examination addExamination(
            @Validated @RequestBody NewExaminationRequest newExaminationRequest) {
        Examination examination = new Examination(null, newExaminationRequest.date,
                newExaminationRequest.diagnosis, null, newExaminationRequest.prescription,
                new SickDayLeave(
                        newExaminationRequest.startingSickDayLeave,
                        newExaminationRequest.totalNumberOfSickDays));

        return examinationService.addExamination(examination, newExaminationRequest.patientEGN,
                newExaminationRequest.doctorUIN);
    }
}