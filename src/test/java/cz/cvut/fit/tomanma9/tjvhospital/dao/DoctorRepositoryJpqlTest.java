package cz.cvut.fit.tomanma9.tjvhospital.dao;

import cz.cvut.fit.tomanma9.tjvhospital.domain.Appointment;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Doctor;
import cz.cvut.fit.tomanma9.tjvhospital.domain.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;


@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
@ContextConfiguration(initializers = { DoctorRepositoryJpqlTest.PropertiesInitializer.class })
@Sql({ "classpath:create-script.sql" })
@ActiveProfiles("dbtest")
@Tag("Jpql")
class DoctorRepositoryJpqlTest extends AbstractPostgresContainerBaseTest {
    // not necessarily integration test, but might as well use
    // TestContainers for JPQL testing as well and reuse stuff

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    private Doctor doctor1;
    private Doctor doctor2;
    private Doctor doctor3;
    private Patient patient1;
    private Patient patient2;
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;
    private Appointment appointment4;

    @BeforeEach
    public void createDoctors() {
        doctor1 = new Doctor(0L, "Tomáš", "tomas@hospital.cz", "111-111-111");
        doctor2 = new Doctor(1L, "Jirka", "jirka@hospital.cz", "222-222-222L");
        doctor3 = new Doctor(2L, "Danek", "danek@hospital.cz", "333-333-333L");

        patient1 = new Patient(0L, "Marek", LocalDate.of(1999, 9, 9), "marek@gmail.com", "333-333-333");
        patient2 = new Patient(1L, "Honza", LocalDate.of(1990, 5, 11), "honza@gmail.com", "444-444-444");
        appointment1 = new Appointment(0L, patient1, doctor1, LocalDateTime.of(2000, 1, 1, 12, 0), LocalDateTime.of(2000, 1, 1, 12, 30));
        appointment2 = new Appointment(1L, patient2, doctor2, LocalDateTime.of(2000, 1, 1, 12, 30), LocalDateTime.of(2000, 1, 1, 13, 0));
        appointment3 = new Appointment(2L, patient1, doctor2, LocalDateTime.of(2000, 2, 1, 12, 0), LocalDateTime.of(2000, 2, 1, 12, 30));
        appointment4 = new Appointment(3L, patient2, doctor2, LocalDateTime.of(2000, 2, 1, 12, 30), LocalDateTime.of(2000, 2, 1, 13, 0));


        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
        doctorRepository.save(doctor3);

        patientRepository.save(patient1);
        patientRepository.save(patient2);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        appointmentRepository.save(appointment4);
    }

    @AfterEach
    public void cleanup() {
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRepository.deleteAll();
    }

    @Test
    public void zeroFindOne() {
        Assertions.assertEquals(doctorRepository.findAllByAppointmentsExists(0).size(), 1);
    }

    @Test
    public void findTwo() {
        Assertions.assertEquals(doctorRepository.findAllByAppointmentsExists(1).size(), 2);
    }

    @Test
    public void findAll() {
        Assertions.assertEquals(doctorRepository.findAllByAppointmentsExists(100).size(), 3);
    }
}