package nbu.medicaljournal.api.model;

import java.time.LocalDate;
import java.util.Objects;

public class SickDayLeave {
    public final LocalDate startingSickDayLeave;
    public final int totalNumberOfSickDays;

    public SickDayLeave(LocalDate startingSickDayLeave, int totalNumberOfSickDays) {
        this.startingSickDayLeave = startingSickDayLeave;
        this.totalNumberOfSickDays = totalNumberOfSickDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SickDayLeave)) {
            return false;
        }

        SickDayLeave that = (SickDayLeave) o;
        return totalNumberOfSickDays == that.totalNumberOfSickDays &&
                Objects.equals(startingSickDayLeave, that.startingSickDayLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingSickDayLeave, totalNumberOfSickDays);
    }

    @Override
    public String toString() {
        return "SickDayLeave{" +
                "startingSickDayLeave=" + startingSickDayLeave +
                ", totalNumberOfSickDays=" + totalNumberOfSickDays +
                '}';
    }
}