package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Patient implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime birthdate;
    private String emailAddress;
    private String phoneNumber;
    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors;
    @OneToMany
    @JoinColumn(name = "patient")
    private final Set<Appointment> appointments = new HashSet<>();

    public Patient(Long id, String name, LocalDateTime birthdate, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public Patient() {

    }


    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDateTime birthdate) { this.birthdate = birthdate; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
