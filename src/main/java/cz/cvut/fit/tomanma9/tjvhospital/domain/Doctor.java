package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "doctor")
public class Doctor implements DomainEntity<Long> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Long id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    // it was opposite the whole time? XDD
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "doctor_patient", // When name is last it does not work... ORDER MATTERS? EXCUSE ME???
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_patient")
    )
    private final Set<Patient> patients = new HashSet<>();
    @OneToMany(mappedBy = "doctor")
    private final Set<Appointment> appointments = new HashSet<>();

    public Doctor(Long id, String name, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public Doctor() {}

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void addPatient(Patient patient) { patients.add(patient); }

    public Collection<Patient> getPatients() { return patients; }
    public Collection<Appointment> getAppointments() { return appointments; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(getId(), doctor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
