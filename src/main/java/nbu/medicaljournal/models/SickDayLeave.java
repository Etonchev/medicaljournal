package nbu.medicaljournal.models;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class SickDayLeave {
    public LocalDate startingSickDayLeave;
    public int totalNumberOfSickDays;
}