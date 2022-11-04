package cz.cvut.fit.tomanma9.tjvhospital.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Doctor implements DomainEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String emailAddress;
    @Column
    private String phoneNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_patient")
    )
    private final Set<Patient> patients = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "doctor")
    private final Set<Appointment> appointments = new HashSet<>();



    public Doctor(Long id, String name, String emailAddress, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public Doctor() {

    }

    @Override
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
