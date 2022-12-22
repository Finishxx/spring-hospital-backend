package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "appointment")
public class Appointment implements DomainEntity<Long> {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_appointment")
    private Long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    private LocalDateTime from;
    private LocalDateTime to;

    public Appointment(Long id, Patient patient, Doctor doctor, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.from = from;
        this.to = to;
    }

    public Appointment() {}

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPacient() { return patient; }
    public void setPacient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDateTime getFrom() { return from; }
    public void setFrom(LocalDateTime from) { this.from = from; }

    public LocalDateTime getTo() { return to; }
    public void setTo(LocalDateTime to) { this.to = to; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
