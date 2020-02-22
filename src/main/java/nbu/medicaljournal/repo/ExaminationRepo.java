package nbu.medicaljournal.repo;

import nbu.medicaljournal.api.model.Examination;
import nbu.medicaljournal.api.spaf.ExaminationQuery;
import nbu.medicaljournal.model.ExaminationEntity;
import nbu.medicaljournal.repository.ExaminationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ExaminationRepo {
    protected  final Specification<ExaminationEntity> BASE_SPECIFICATION =
            (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();

    @Autowired
    ExaminationRepository examinationRepository;

    @Transactional(readOnly = true)
    public List<ExaminationEntity> all(ExaminationQuery query) {
        Specification<ExaminationEntity> filters = BASE_SPECIFICATION;
        filters = applyQueryFilters(filters, query);

        return examinationRepository.findAll(filters);
    }

    @Transactional(readOnly = true)
    public Optional<ExaminationEntity> find(String id) {
        return examinationRepository.findById(id);
    }

    @Transactional
    public ExaminationEntity save(ExaminationEntity examinationEntity) {
        return examinationRepository.save(examinationEntity);
    }

    @Transactional
    public void delete(String id) {
        examinationRepository.deleteById(id);
    }

    private Specification<ExaminationEntity> applyQueryFilters(
            Specification<ExaminationEntity> specification, ExaminationQuery query) {
        if (query.diagnosis.isPresent() && StringUtils.isNotBlank(query.diagnosis.get())) {
            specification = specification.and(byDiagnosis(query.diagnosis.get()));
        }
        if (query.doctorUin.isPresent() && StringUtils.isNotBlank(query.doctorUin.get())) {
            specification = specification.and(byDoctorUin(query.doctorUin.get()));
        }

        return specification;
    }

    private static Specification<ExaminationEntity> byDiagnosis(String diagnosis) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("diagnosis")), "%" + diagnosis.toLowerCase() + "%"));
    }

    private static Specification<ExaminationEntity> byDoctorUin(String doctorUin) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("doctor").get("uin"), doctorUin));
    }
}