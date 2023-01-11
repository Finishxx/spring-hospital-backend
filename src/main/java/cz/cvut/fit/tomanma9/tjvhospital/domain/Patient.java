package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "patient")
public class Patient implements DomainEntity<Long> {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient")
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String emailAddress;
    private String phoneNumber;

    // mapped by is looking for Java attribute
    @ManyToMany(mappedBy = "patients")
    private final Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    private final Set<Appointment> appointments = new HashSet<>();

    public Patient(Long id, String name, LocalDate birthdate, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
    public Patient() {}



    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    // unmodifiable collection to prevent losing sync with database entity
    public Collection<Doctor> getDoctors() { return Collections.unmodifiableCollection(doctors); }
    public Collection<Appointment> getAppointments() { return Collections.unmodifiableCollection(appointments); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
