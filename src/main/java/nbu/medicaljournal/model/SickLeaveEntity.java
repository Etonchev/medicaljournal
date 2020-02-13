package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.SickLeave;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class SickLeaveEntity {
    private LocalDate startingSickDayLeave;
    private int totalNumberOfSickDays;

    public SickLeaveEntity() {
    }

    public SickLeaveEntity(LocalDate startingSickDayLeave, int totalNumberOfSickDays) {
        this.startingSickDayLeave = startingSickDayLeave;
        this.totalNumberOfSickDays = totalNumberOfSickDays;
    }

    public LocalDate getStartingSickDayLeave() {
        return startingSickDayLeave;
    }

    public void setStartingSickDayLeave(LocalDate startingSickDayLeave) {
        this.startingSickDayLeave = startingSickDayLeave;
    }

    public int getTotalNumberOfSickDays() {
        return totalNumberOfSickDays;
    }

    public void setTotalNumberOfSickDays(int totalNumberOfSickDays) {
        this.totalNumberOfSickDays = totalNumberOfSickDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SickLeaveEntity)) {
            return false;
        }

        SickLeaveEntity that = (SickLeaveEntity) o;
        return totalNumberOfSickDays == that.totalNumberOfSickDays &&
                Objects.equals(startingSickDayLeave, that.startingSickDayLeave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingSickDayLeave, totalNumberOfSickDays);
    }

    public SickLeave toSickLeave() {
        return new SickLeave(startingSickDayLeave, totalNumberOfSickDays);
    }
}