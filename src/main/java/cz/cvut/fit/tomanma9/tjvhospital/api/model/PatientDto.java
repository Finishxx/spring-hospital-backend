package cz.cvut.fit.tomanma9.tjvhospital.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

// in collections we don't use Objects, but ID-s
public class PatientDto {

    private Long id;
    private String name;
    // https://www.baeldung.com/spring-boot-formatting-json-dates
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthdate;
    private String emailAddress;
    private String phoneNumber;

    private Collection<Long> doctors;

    private Collection<Long> appointments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Long> getDoctors() {
        return doctors;
    }

    public void setDoctors(Collection<Long> doctors) {
        this.doctors = doctors;
    }

    public Collection<Long> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Long> appointments) {
        this.appointments = appointments;
    }
}
