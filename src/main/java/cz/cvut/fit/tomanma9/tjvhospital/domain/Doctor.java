package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Doctor implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_patient")
    )
    private final Set<Patient> patients = new HashSet<>();
    @OneToMany
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
    // return umodifiable (const) collections so we don't lose sync
    public Collection<Patient> getPatients() { return Collections.unmodifiableCollection(patients); }
    public Collection<Appointment> getAppointments() { return Collections.unmodifiableCollection(appointments); }

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