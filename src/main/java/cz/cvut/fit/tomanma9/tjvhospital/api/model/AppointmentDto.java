package cz.cvut.fit.tomanma9.tjvhospital.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;

import java.time.LocalDateTime;

public class AppointmentDto {

    private Long id;
    private InnerPatientDto patient;
    private InnerDoctorDto doctor;
    // https://www.baeldung.com/spring-boot-formatting-json-dates
    // format from https://stackoverflow.com/questions/4032967/json-date-to-java-date
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime from;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InnerPatientDto getPatient() {
        return patient;
    }

    public void setPatient(InnerPatientDto patient) {
        this.patient = patient;
    }

    public InnerDoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(InnerDoctorDto doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

}
