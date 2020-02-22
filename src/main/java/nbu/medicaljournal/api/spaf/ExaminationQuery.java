package nbu.medicaljournal.api.spaf;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Objects;
import java.util.Optional;

public class ExaminationQuery {
    public final Optional<String> diagnosis;
    public final Optional<String> doctorUin;

    public ExaminationQuery() {
        this(empty(), empty());
    }

    public ExaminationQuery(
            Optional<String> diagnosis,
            Optional<String> doctorUin) {
        this.diagnosis = diagnosis;
        this.doctorUin = doctorUin;
    }

    public ExaminationQuery withDiagnosis(String diagnosis) {
        return new ExaminationQuery(of(diagnosis), doctorUin);
    }

    public ExaminationQuery withDoctorUin(String doctorUin) {
        return new ExaminationQuery(diagnosis, of(doctorUin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExaminationQuery)) {
            return false;
        }

        ExaminationQuery that = (ExaminationQuery) o;
        return Objects.equals(diagnosis, that.diagnosis) &&
                Objects.equals(doctorUin, that.doctorUin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diagnosis, doctorUin);
    }
}