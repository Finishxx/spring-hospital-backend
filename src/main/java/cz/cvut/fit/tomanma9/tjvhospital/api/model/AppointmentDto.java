package cz.cvut.fit.tomanma9.tjvhospital.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;

import java.time.LocalDateTime;

public class AppointmentDto {

    private Long id;
    private Long patient;
    private Long doctor;
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

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
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
