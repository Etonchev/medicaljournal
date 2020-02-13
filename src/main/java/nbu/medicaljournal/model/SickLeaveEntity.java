package nbu.medicaljournal.model;

import nbu.medicaljournal.api.model.SickLeave;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class SickLeaveEntity {
    private LocalDate startingDate;
    private int numberOfDays;

    public SickLeaveEntity() {
    }

    public SickLeaveEntity(LocalDate startingDate, int numberOfDays) {
        this.startingDate = startingDate;
        this.numberOfDays = numberOfDays;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
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
        return numberOfDays == that.numberOfDays &&
                Objects.equals(startingDate, that.startingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDate, numberOfDays);
    }

    public SickLeave toSickLeave() {
        return new SickLeave(startingDate, numberOfDays);
    }
}