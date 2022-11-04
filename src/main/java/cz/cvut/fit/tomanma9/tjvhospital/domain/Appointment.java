package cz.cvut.fit.tjv.hospital.domain;

import java.time.LocalDateTime;

public class Appointment implements DomainEntity<Long> {

    private Long id;
    private Pacient pacient;
    private Doctor doctor;

    private LocalDateTime from;
    private LocalDateTime to;



    public Appointment(Long id, Pacient pacient, Doctor doctor, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.pacient = pacient;
        this.doctor = doctor;
        this.from = from;
        this.to = to;
    }


    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Pacient getPacient() { return pacient; }
    public void setPacient(Pacient pacient) { this.pacient = pacient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDateTime getFrom() { return from; }
    public void setFrom(LocalDateTime from) { this.from = from; }

    public LocalDateTime getTo() { return to; }
    public void setTo(LocalDateTime to) { this.to = to; }
}
