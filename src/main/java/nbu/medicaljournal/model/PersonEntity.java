package nbu.medicaljournal.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class PersonEntity {
    @NotBlank(message = "First name can not be empty!")
    private String firstName;
    @NotBlank(message = "Last name can not be empty!")
    private String lastName;

    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}