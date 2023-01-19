package cz.cvut.fit.tomanma9.tjvhospital.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class InnerAppointmentForDoctorDto {
    private Long appointment_id;
    private String patient_name;
    private Long patient_id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime time_from;

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDateTime getTime_from() {
        return time_from;
    }

    public void setTime_from(LocalDateTime time_from) {
        this.time_from = time_from;
    }
}
