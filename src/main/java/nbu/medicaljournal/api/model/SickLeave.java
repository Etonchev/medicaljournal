package nbu.medicaljournal.api.model;

import java.time.LocalDate;
import java.util.Objects;

public class SickLeave {
    public final LocalDate startingDate;
    public final int numberOfDays;

    public SickLeave(LocalDate startingDate, int numberOfDays) {
        this.startingDate = startingDate;
        this.numberOfDays = numberOfDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SickLeave)) {
            return false;
        }

        SickLeave that = (SickLeave) o;
        return numberOfDays == that.numberOfDays &&
                Objects.equals(startingDate, that.startingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingDate, numberOfDays);
    }

    @Override
    public String toString() {
        return "SickLeave{" +
                "startingDate=" + startingDate +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}